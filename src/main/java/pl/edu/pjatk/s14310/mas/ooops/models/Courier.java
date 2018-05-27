package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Courier implements Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ElementCollection
    @Digits(integer = 10, fraction = 0)
    private Set<String> phones;
    @Setter
    @Pattern(regexp = "[a-zA-Z0-9 ]")
    private String address;
    @OneToMany(targetEntity=Area.class, fetch=FetchType.EAGER)
    private List<Area> areas;
    @OneToOne
    private Office office;

    public Courier(Set<String> phones, String address, @NotNull List<Area> areas) {
        if (areas.size()==0) {
            throw new IllegalArgumentException("Should be assign to at least one area");
        }
        this.phones = phones;
        this.address = address;
        this.areas = areas;
    }

    public Courier(Set<String> phones, String address,@NotNull Office office) {
        this.phones = phones;
        this.address = address;
        this.office = office;
        office.addCourier(this);
    }

    @Override
    public void addPhone(String phone) {
        phones.add(phone);
    }

    public void addArea(Area area) {
        areas.add(area);
    }

    public List<Area> getAreas() {
        return new ArrayList<>(areas);
    }
}
