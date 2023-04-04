package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
