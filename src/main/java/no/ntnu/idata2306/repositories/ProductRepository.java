package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for products
 * @author Edvin Astad
 * @version 17.03.2023
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
