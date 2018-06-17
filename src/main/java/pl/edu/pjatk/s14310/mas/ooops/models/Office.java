package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Office implements Comparable<Office> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String address;
    @NotNull
    @ElementCollection
    private List<String> phones = new ArrayList<>();
    @OneToOne
    private Area area;
    private int size;
    @OneToMany(targetEntity=Courier.class, fetch=FetchType.EAGER)
    private Set<Courier> couriers;

    public void setPhones(String phone) {
        phones.add(phone);
    }
    @Override
    public int compareTo(Office o) {
        return address.compareTo(o.getAddress());
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
    }
}
