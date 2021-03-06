package pl.edu.pjatk.s14310.mas.ooops.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.s14310.mas.ooops.models.Courier;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Integer> {
}
