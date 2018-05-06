package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String deliveryAddress;
    private int weight;
    @NotNull
    private LocalDate creationDate;
    private LocalDate estimatedDeliveryDate;
    private LocalDate deliveredDate;
    private boolean createdUrgent;
    @ManyToOne
    @NotNull
    private Individual givenBy;
    private static int hoursToUrgent = 24;
    private List<DeliveryList> deliveryLists;

    private String signature;
    private int wrappingPrice;



    @NotNull
    public Parcel(String deliveryAddress, int weight, boolean isUrgent, Individual givenBy, String signature) {
        this.deliveryAddress = deliveryAddress;
        this.weight = weight;
        this.creationDate = LocalDate.now();
        this.estimatedDeliveryDate = creationDate.plusDays(3);
        this.createdUrgent = isUrgent;
        this.givenBy = givenBy;
        this.deliveryLists = new ArrayList<>();
        this.signature = signature;
        wrappingPrice = signature == null ? weight : 0;
    }


    public void addDeliveryList(DeliveryList dl){
        deliveryLists.add(dl);
    }
    public int getPrice(){
        int deliveryPrice = isUrgent() ? weight * 2 : weight;
        return deliveryPrice+wrappingPrice;
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
