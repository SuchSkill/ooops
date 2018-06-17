package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


@Entity
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String name;
    @OneToMany(targetEntity=Courier.class, fetch=FetchType.EAGER)
    private List<Courier> couriers;
    @OneToMany(targetEntity=Region.class)
    private List<Region> regions;
    @Setter
    @Getter
    @OneToMany(targetEntity=Office.class, fetch=FetchType.EAGER)
    @OrderBy("address")
    private SortedSet<Office> offices;
    @Setter
    @Getter
    @OneToOne
    private Office headquarter;

    private int limit = 4;


    public Area(@NotNull String name,@NotNull List<Courier> couriers,String rName, String rDesc,@NotNull SortedSet<Office> offices,@NotNull Office headquarter) {
        if (!offices.contains(headquarter)) {
            throw new IllegalArgumentException("headquarter have to be in list of offices");
        }
        checkLimit(couriers);
        this.name = name;
        this.couriers = couriers;
        Region region = new Region(rName, rDesc);
        this.regions = new ArrayList<>();
        regions.add(region);
        this.offices = offices;
        this.headquarter = headquarter;
    }

    private void checkLimit(@NotNull List<Courier> couriers) {
        if (couriers.size() >= limit) {
            throw new IllegalArgumentException("Area can't have more then " + limit + " couriers");
        }
    }

    public void addCourier(Courier courier) {
        checkLimit(couriers);
        couriers.add(courier);
    }
    public void addRegion(String rName, String rDesc) {
        Region region = new Region(rName, rDesc);
        regions.add(region);
    }

    public List<Region> getRegion() {
        return new ArrayList<>(regions);
    }

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class Region {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        private String description;

        public Region(String name, String description) {
            this.name = name;
            this.description = description;
        }

    }

}
