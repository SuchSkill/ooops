package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
public class DeliveryList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate day;
    //    @OneToMany
    @ElementCollection
    private Map<Integer, Parcel> parcelInfoMap = new HashMap<>();

    public DeliveryList(List<Parcel> parcels) {
        for (Parcel parcel : parcels) {
            parcelInfoMap.put(parcel.getId(), parcel);
        }
        day = LocalDate.now();
    }
}
