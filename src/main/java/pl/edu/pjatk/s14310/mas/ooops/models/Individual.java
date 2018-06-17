package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity
public class Individual implements Customer, Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private int id;
    @Setter
    private String address;
    @ElementCollection(targetClass=String.class)
    private Set<String> phones = new HashSet<>();
    @OneToMany
    private List<Parcel> parcels = new ArrayList<>();


    @Override
    public void addPhone(String phone) {
        phones.add(phone);
    }

    @Override
    public int calculateDiscount() {
        return 0;
    }
    public void addParcel(Parcel p){
        parcels.add(p);
    }
}

