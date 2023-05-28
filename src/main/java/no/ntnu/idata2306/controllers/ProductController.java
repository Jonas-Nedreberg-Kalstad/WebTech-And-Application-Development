package no.ntnu.idata2306.controllers;

import java.util.List;
import java.util.Optional;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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
   * @return a ModelAndView containing product in JSON format or page-not-found
   */
  @GetMapping("/api/products/{id}")
  public ModelAndView getProduct(@PathVariable int id) {
    Optional<Product> product = productService.getProduct(id);
    ModelAndView modelAndView;
    if (product.isEmpty()) {
      modelAndView = new ModelAndView("page-not-found");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
    } else {
      // The product is displayed in JSON format
      modelAndView = new ModelAndView();
      modelAndView.addObject("product", product.get());
      modelAndView.setView(new MappingJackson2JsonView());
    }
    return modelAndView;
  }

  /**
   * Creates a new product.
   *
   * @param product The Product object to be created.
   * @return ResponseEntity containing the created Product and HTTP status code 201 (CREATED).
   */
  @PostMapping("/api/products")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    productService.createProduct(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  /**
   * Updates an existing product.
   *
   * @param id The ID of the product to be updated.
   * @param updatedProduct The updated Product object.
   * @return ResponseEntity containing the updated Product (Optional) and HTTP status code 200 (OK) if successful,
   * or HTTP status code 404 (NOT_FOUND) if the product with the given ID doesn't exist.
   */
  @PutMapping("/api/products/{id}")
  public ResponseEntity<Optional<Product>> updateProduct(@PathVariable int id, @RequestBody Optional<Product> updatedProduct) {
    Optional<Product> existingProduct = productService.getProduct(id);
    if (existingProduct.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      productService.updateProduct(updatedProduct);
      return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
  }


  /**
   * Deletes a product.
   *
   * @param id The ID of the product to be deleted.
   * @return ResponseEntity with HTTP status code 204 (NO_CONTENT) if successful,
   * or HTTP status code 404 (NOT_FOUND) if the product with the given ID doesn't exist.
   */
  @DeleteMapping("/api/products/{id}")
  public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable int id) {
    Optional<Product> existingProduct = productService.getProduct(id);
    if (existingProduct.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      productService.deleteProduct(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

}
