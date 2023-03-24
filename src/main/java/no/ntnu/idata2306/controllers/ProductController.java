package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST API controller for all endpoints related to products.
 * @author Edvin Astad
 * @version 17.03.2023
 */

@CrossOrigin
@RestController
public class ProductController {
    private ProductService productService;

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
     *
     * @param id potential id of a product
     * @return product with given id
     */
    @GetMapping("/api/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        Optional<Product> product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @PutMapping("/api/products/{id}")
//    public ResponseEntity<String> editProduct(@PathVariable int id) {
//
//    }

}
