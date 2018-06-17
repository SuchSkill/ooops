package pl.edu.pjatk.s14310.mas.ooops.forms;

import lombok.Data;

@Data
public class ParcelForm {
    private String deliveryAddress;
    private int individualId;
    private int weight;
    private boolean wrapping;
    private boolean urgent;
}
