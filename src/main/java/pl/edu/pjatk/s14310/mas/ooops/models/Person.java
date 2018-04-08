package pl.edu.pjatk.s14310.mas.ooops.models;

import java.util.List;
import java.util.Set;

public interface Person {
    public String getAddress();
    public void setAddress(String address);

    public Set<String> getPhones();
    public void addPhone(String phone);

}
