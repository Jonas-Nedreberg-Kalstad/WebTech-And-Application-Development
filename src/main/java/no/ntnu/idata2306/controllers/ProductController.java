package no.ntnu.idata2306.controllers;

import java.util.List;

import no.ntnu.idata2306.data.Product;
import no.ntnu.idata2306.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

/**
 * REST API controller for all endpoints related to products.
 */

@RestController
public class ProductController {
    private ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    /**
     * HTTP GET endpoint for getting all products.
     *
     * @return List of all available products.
     */
    @GetMapping("/products")
    public ProductRepository getProducts() {
        return products;
    }
}
