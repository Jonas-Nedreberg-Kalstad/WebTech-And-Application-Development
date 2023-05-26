package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for products.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

  /**
   * Finding all products using pagination
   *
   * @param pageable pagination configuration using limit and offset.
   * @return return all orders with given paging
   */
  Page<Product> findAll(Pageable pageable);
}
