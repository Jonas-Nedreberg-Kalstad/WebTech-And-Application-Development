package no.ntnu.idata2306.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
   * Retrieves the product with the given ID from the database, if it exists.
   *
   * @param id The ID of the product.
   * @return An Optional containing the product with the given ID, or empty if no product is found.
   */
  public Optional<Product> getProduct(int id) {
    return productRepository.findById(id);
  }

  /**
   * Get the first n products from the database.
   *
   * @param n The number of books to select
   * @return An iterable over the books, empty when no books found.
   */
  public Iterable<Product> getFirstNProductsFromDatabase(int n) {
    return productRepository.findAll(PageRequest.of(0, n));
  }

  /**
   * Creates a new product by saving it to the database.
   *
   * @param product The product to be created.
   */
  public void createProduct(Product product) {
    productRepository.save(product);
  }

  /**
   * Updates an existing product by saving the changes to the database.
   *
   * @param optionalProduct The product to be updated.
   */
  public void updateProduct(Optional<Product> optionalProduct) {
    optionalProduct.ifPresent(product -> productRepository.save(product));
  }

  /**
   * Deletes a product from the database based on the provided ID.
   *
   * @param id The ID of the product to be deleted.
   */
  public void deleteProduct(int id) {
    productRepository.deleteById(id);
  }

}
