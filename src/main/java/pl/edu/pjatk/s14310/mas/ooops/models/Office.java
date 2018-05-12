package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class Office implements Comparable<Office> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String address;
    @NotNull
    private List<String> phones = new ArrayList<>();
    private Area area;
    private int size;
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
