package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Company implements Customer {
    @Id
    private int id;
    @Column(unique = true)
    private String regNo;

    @Override
    public int calculateDiscount() {
        return 5;
    }
}
