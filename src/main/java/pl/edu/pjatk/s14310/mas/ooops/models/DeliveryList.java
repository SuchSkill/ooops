package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class DeliveryList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate day;
    //    @OneToMany
    @ElementCollection
    private Map<Integer, Parcel> parcelMap = new HashMap<>();
    private String CarPlateNumber;
    private int alcoholTestResults;
    @OneToOne(targetEntity=Courier.class, fetch=FetchType.EAGER)
    private Courier courier;


    public DeliveryList(List<Parcel> parcels, Courier courier, int alcoholTestResults) {
        for (Parcel parcel : parcels) {
            parcelMap.put(parcel.getId(), parcel);
            parcel.addDeliveryList(this);
        }
        this.courier = courier;
        this.alcoholTestResults = alcoholTestResults;
        day = LocalDate.now();
    }
}
