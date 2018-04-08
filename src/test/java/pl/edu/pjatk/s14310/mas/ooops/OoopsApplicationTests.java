package pl.edu.pjatk.s14310.mas.ooops;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjatk.s14310.mas.ooops.models.Individual;
import pl.edu.pjatk.s14310.mas.ooops.models.Parcel;
import pl.edu.pjatk.s14310.mas.ooops.repositories.CustomerRepository;
import pl.edu.pjatk.s14310.mas.ooops.repositories.ParcelRepository;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OoopsApplicationTests {
    private Individual individual;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Before
    public void init(){
        individual = new Individual();
        individual.setAddress("Warsaw ul. Walicow 13");
        individual.addPhone("666777888");
        individual.addPhone("666777889");
        customerRepository.save(individual);
    }

    @Test
    public void contextLoads() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTwiceHoursToUrgentShouldThrowError(){
        Parcel.setHoursToUrgent(Parcel.getHoursToUrgent()*2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setHalfHoursToUrgentShouldThrowError(){
        Parcel.setHoursToUrgent(Parcel.getHoursToUrgent()/2);
    }

    @Test
    public void showOptionalAttributeOfParcel(){
        Parcel parcel = new Parcel("ul. Koszykowa 86 02-008 Warszawa", 500, false, individual);
        assertThat(parcel.getDeliveredDate(), equalTo(null));
    }

    @Test
    public void showPersistance(){
        Parcel parcel = new Parcel("ul. Koszykowa 86 02-008 Warszawa", 500, false, individual);
        parcelRepository.save(parcel);
        for (Parcel parcel1 : parcelRepository.findAll()) {
            assertThat(parcel1.getDeliveredDate(), equalTo(parcel.getDeliveredDate()));
            assertThat(parcel1.getWeight(), equalTo(parcel.getWeight()));
            assertThat(parcel1.isUrgent(), equalTo(parcel.isUrgent()));
            assertThat(parcel1.getGivenBy().getAddress(), equalTo(parcel.getGivenBy().getAddress()));
        }
    }
    @Test
    public void showDerivedAttribute(){
        Parcel parcel = new Parcel("ul. Koszykowa 86 02-008 Warszawa", 500, false, individual);
        assertNotNull(parcel.getPrice());
    }
    @Test
    public void showMultiValueAttribute() {
        Parcel parcel = new Parcel("ul. Koszykowa 86 02-008 Warszawa", 500, false, individual);
        assertThat(parcel.getGivenBy().getPhones().size(), equalTo(2));
    }
}
