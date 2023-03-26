package no.ntnu.idata2306.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Business logic for product.
 *
 * @author Edvin Astad
 * @version 22.03.2023
 */
@Service
public class ProductService {
  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * returns all products stored in product repository.
   *
   * @return all products stored in product repository
   */
  public List<Product> getAll() {
    List<Product> products = new LinkedList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  /**
   * returns product with given id if it exists in product repository.
   *
   * @param id id of product
   * @return product with given id
   */
  public Optional<Product> getProduct(int id) {
    // TODO - decide if method should return null, Optional#empty(),
    //  or throw an exception if there is not product with given id.
    //  As of now it returns Optional#empty().
    return productRepository.findById(id);
  }

}
