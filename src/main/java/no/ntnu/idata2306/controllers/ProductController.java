package no.ntnu.idata2306.controllers;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.idata2306.model.Orders;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Endpoints for managing products")
public class ProductController {
  private final ProductService productService;

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  private static final String PRODUCT_NOT_FOUND = "Product not found with ID: {}";


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
  @GetMapping
  @Operation(summary = "Get all products", description = "Retrieves a list of all available products")
  public List<Product> getAll() {
    return productService.getAll();
  }

  /**
   * Get a product from database matching given id if it exists.
   *
   * @param id potential id of a product
   * @return a ModelAndView containing product in JSON format or page-not-found
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get a product by ID", description = "Retrieves a product based on the provided ID")
  @ApiResponse(responseCode = "200", description = "Product found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
  @ApiResponse(responseCode = "404", description = "Product not found")
  public ModelAndView getProduct(@PathVariable int id) {
    Optional<Product> product = productService.getProduct(id);
    ModelAndView modelAndView;
    if (product.isEmpty()) {
      logger.warn(PRODUCT_NOT_FOUND, id);
      modelAndView = new ModelAndView("page-not-found");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
    } else {
      logger.info("Product found with ID: {}", id);
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
  @PostMapping
  @Operation(summary = "Create a new product", description = "Creates a new product")
  @ApiResponse(responseCode = "201", description = "Product created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
  @ApiResponse(responseCode = "400", description = "Bad request")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    try {
      Product createdProduct = productService.createProduct(product);
      logger.info("Product created with ID: {}", createdProduct.getId());
      return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    } catch (Exception e) {
      logger.error("Error creating product", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /**
   * Updates an existing product.
   *
   * @param id The ID of the product to be updated.
   * @param updatedProduct The updated Product object.
   * @return ResponseEntity containing the updated Product (Optional) and HTTP status code 200 (OK) if successful,
   * or HTTP status code 404 (NOT_FOUND) if the product with the given ID doesn't exist.
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update an existing product", description = "Updates an existing product based on the provided ID")
  @ApiResponse(responseCode = "200", description = "Product updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
  @ApiResponse(responseCode = "404", description = "Product not found")
  public ResponseEntity<Optional<Product>> updateProduct(@PathVariable int id, @RequestBody Optional<Product> updatedProduct) {
    Optional<Product> existingProduct = productService.getProduct(id);
    if (existingProduct.isEmpty()) {
      logger.warn(PRODUCT_NOT_FOUND, id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      productService.updateProduct(updatedProduct);
      logger.info("Product updated with ID: {}", id);
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
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a product", description = "Deletes a product based on the provided ID")
  @ApiResponse(responseCode = "204", description = "Product deleted")
  @ApiResponse(responseCode = "404", description = "Product not found")
  public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable int id) {
    Optional<Product> existingProduct = productService.getProduct(id);
    if (existingProduct.isEmpty()) {
      logger.warn(PRODUCT_NOT_FOUND, id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      productService.deleteProduct(id);
      logger.info("Product deleted with ID: {}", id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

}
