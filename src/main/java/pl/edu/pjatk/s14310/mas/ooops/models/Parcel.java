package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String deliveryAddress;
    private int weight;
    private LocalDate creationDate;
    private LocalDate estimatedDeliveryDate;
    private LocalDate deliveredDate;
    private boolean createdUrgent;
    @ManyToOne
    private Individual givenBy;
    private static int hoursToUrgent = 24;
//    private Customer deliveredTo;

    public Parcel(String deliveryAddress, int weight, boolean isUrgent, Individual givenBy) {
        this.deliveryAddress = deliveryAddress;
        this.weight = weight;
        this.creationDate = LocalDate.now();
        this.estimatedDeliveryDate = creationDate.plusDays(3);
        this.createdUrgent = isUrgent;
        this.givenBy = givenBy;
//        this.deliveredTo = null;
    }

    public int getPrice(){
        return isUrgent()? weight*2:weight;
    }
    public static int getPrice(int weightEstimation, boolean isUrgent){
        return isUrgent? weightEstimation*2:weightEstimation;
    }
    public static int getPrice(int weightEstimation){
        return weightEstimation;
    }

    public static void setHoursToUrgent(int newOurs){
        double constrain = hoursToUrgent * 0.20;
        if (Math.abs(newOurs - hoursToUrgent) > constrain){
            throw new IllegalArgumentException("new hours cant differ more then 20% from current " + hoursToUrgent);
        }
        hoursToUrgent = newOurs;
    }

    public static int getHoursToUrgent(){
        return hoursToUrgent;

    }
    public boolean isUrgent(){
        return createdUrgent || LocalDateTime.now().plusHours(hoursToUrgent).isAfter(ChronoLocalDateTime.from(estimatedDeliveryDate.atStartOfDay()));
    }
}