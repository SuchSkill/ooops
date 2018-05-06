package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Courier implements Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Set<String> phones;
    @Setter
    private String address;
    private List<Area> areas;

    public Courier(Set<String> phones, String address, @NotNull List<Area> areas) {
        if (areas.size()==0) {
            throw new IllegalArgumentException("Should be assign to at least one area");
        }
        this.phones = phones;
        this.address = address;
        this.areas = areas;
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
