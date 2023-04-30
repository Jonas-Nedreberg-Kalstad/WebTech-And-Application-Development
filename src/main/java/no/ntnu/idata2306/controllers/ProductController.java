package no.ntnu.idata2306.controllers;

import java.util.List;
import java.util.Optional;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for all endpoints related to products.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */

@CrossOrigin
@RestController
public class ProductController {
  private final ProductService productService;

  /**
   * Creates a new instance of ProductController.
   *
   * @param productService productService
   */
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Get all products stored in database.
   *
   * @return List of all available products.
   */
  @GetMapping("/api/products")
  public List<Product> getAll() {
    return productService.getAll();
  }

  /**
   * get a product from database matching given id if it exists.
   *
   * @param id potential id of a product
   * @return product with given id
   */
  @GetMapping("/api/products/{id}")
  public ResponseEntity<?> getProduct(@PathVariable int id) {
    Product product = productService.getProduct(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }
}
