package pl.edu.pjatk.s14310.mas.ooops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import pl.edu.pjatk.s14310.mas.ooops.models.Individual;
import pl.edu.pjatk.s14310.mas.ooops.models.Parcel;
import pl.edu.pjatk.s14310.mas.ooops.repositories.CustomerRepository;
import pl.edu.pjatk.s14310.mas.ooops.repositories.ParcelRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("pl.edu.pjatk.s14310.mas.ooops.repositories")
@EntityScan("pl.edu.pjatk.s14310.mas.ooops.*")
@ComponentScan(basePackages = { "pl.edu.pjatk.s14310.mas.ooops.*" })
public class OoopsApplication {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    private static List<Individual> individuals = new ArrayList<Individual>();

    static {
        Individual e = new Individual();
        e.setAddress("Address 1");
        e.addPhone("123456789");
        individuals.add(e);

        Individual e1 = new Individual();
        e1.setAddress("Address 2");
        e1.addPhone("987654321");

        individuals.add(e1);

    }

    public static void main(String[] args) {
        SpringApplication.run(OoopsApplication.class, args);
    }

    @PostConstruct
    private void dataInit(){
        for (Individual individual : individuals) {
            customerRepository.save(individual);
        }
        Parcel parcel = new Parcel("DelAddr1", 123, false, individuals.get(0), "true");
        Parcel parcel2 = new Parcel("DelAddrdfknljhvfnkvnjlkvarvrjnkldjnkerlva234", 984, true, individuals.get(0), "true");
        Parcel parcel3 = new Parcel("Delsdfsdfsadf", 15, true, individuals.get(1), "true");

        parcelRepository.save(parcel);
        parcelRepository.save(parcel2);
        parcelRepository.save(parcel3);
    }
}
