package pl.edu.pjatk.s14310.mas.ooops.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.s14310.mas.ooops.models.Customer;
import pl.edu.pjatk.s14310.mas.ooops.models.Individual;
import pl.edu.pjatk.s14310.mas.ooops.models.Parcel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ParcelRepository extends CrudRepository<Parcel, Integer> {
    List<Parcel> findByDeliveredDate(LocalDate deliveryDate);

    List<Parcel> findByEstimatedDeliveryDate(LocalDate estimatedDeliveryDate);

    List<Parcel> findByGivenBy(Individual individual);

}
