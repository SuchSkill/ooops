package pl.edu.pjatk.s14310.mas.ooops.forms;

import lombok.Data;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerForm {
    private String address;
    private String phone;
}
