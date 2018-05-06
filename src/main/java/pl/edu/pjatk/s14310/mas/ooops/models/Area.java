package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


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
    private List<Courier> couriers;
    private List<Region> regions;

    private int limit = 4;

    public Area(@NotNull String name,@NotNull List<Courier> couriers, String rName, String rDesc) {
        checkLimit(couriers);
        this.name = name;
        this.couriers = couriers;
        Region region = new Region(rName, rDesc);
        this.regions = new ArrayList<>();
        regions.add(region);
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

    @Data
    @AllArgsConstructor
    private class Region{
        private String name;
        private String description;
    }

}
