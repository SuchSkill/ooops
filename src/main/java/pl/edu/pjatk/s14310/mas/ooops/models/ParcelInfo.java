package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class ParcelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ElementCollection(targetClass=DeliveryList.class)
    private Set<DeliveryList> deliveryListSet = new HashSet<>();
    private String signature;

    public ParcelInfo(DeliveryList dl){
        deliveryListSet.add(dl);
    }

    public void addDeliveryList(DeliveryList dl) {
        deliveryListSet.add(dl);
    }


}
