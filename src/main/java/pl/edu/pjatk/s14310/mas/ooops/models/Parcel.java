package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
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
    private static int basicHoursToUrgent = 24;
    @OneToMany(targetEntity = DeliveryList.class)
    private List<DeliveryList> deliveryLists;
    private State state;
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


    public void addDeliveryList(DeliveryList dl) {
        deliveryLists.add(dl);
    }

    public int getPrice() {
        int deliveryPrice = isUrgent() ? weight * 2 : weight;
        return deliveryPrice + wrappingPrice;
    }

    public static int getPrice(int weightEstimation, boolean isUrgent) {
        return isUrgent ? weightEstimation * 2 : weightEstimation;
    }

    public static int getPrice(int weightEstimation) {
        return weightEstimation;
    }

    public static void setBasicHoursToUrgent(int newOurs) {
        double constrain = basicHoursToUrgent * 0.20;
        if (Math.abs(newOurs - basicHoursToUrgent) > constrain) {
            throw new IllegalArgumentException("new hours cant differ more then 20% from current " + basicHoursToUrgent);
        }
        basicHoursToUrgent = newOurs;
    }

    public static int getBasicHoursToUrgent() {
        return basicHoursToUrgent;

    }

    public boolean isUrgent() {
        return createdUrgent || LocalDateTime.now().plusHours(basicHoursToUrgent).isAfter(ChronoLocalDateTime.from(estimatedDeliveryDate.atStartOfDay()));
    }

    public long getHoursToUrgent() {
        if(!createdUrgent || LocalDateTime.now().plusHours(basicHoursToUrgent).isBefore(ChronoLocalDateTime.from(estimatedDeliveryDate.atStartOfDay()))){
            Duration between = Duration.between(LocalDateTime.now(), estimatedDeliveryDate.atStartOfDay());
            return between.toHours();
        }
        else{
            return 0;
        }

    }

    public List<Parcel> getOverdueParcels(){
        return new ArrayList<>();
    }

}

