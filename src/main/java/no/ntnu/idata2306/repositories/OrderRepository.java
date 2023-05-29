package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Orders;
import org.springframework.data.repository.CrudRepository;


/**
 * repository for orders.
 */
public interface OrderRepository extends CrudRepository<Orders, Integer> {

}
