package pl.edu.pjatk.s14310.mas.ooops.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s14310.mas.ooops.models.DeliveryList;
import pl.edu.pjatk.s14310.mas.ooops.models.Individual;

public interface DeliveryListRepository extends CrudRepository<DeliveryList, Integer> {
}
