package pl.edu.pjatk.s14310.mas.ooops.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
public interface Customer {
    public int getId();
}
