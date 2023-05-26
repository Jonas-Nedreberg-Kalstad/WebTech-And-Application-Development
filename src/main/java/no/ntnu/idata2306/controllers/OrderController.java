package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.model.Orders;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class OrderController {

  private final OrderService orderService;

  /**
   * Creates a new instance of OrderController.
   *
   * @param orderService orderService
   */
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * Get all orders stored in database.
   *
   * @return List of all orders.
   */
  @GetMapping("/api/orders")
  public List<Orders> getAll() {
    return orderService.getAll();
  }

  /**
   * Get product with given id.
   *
   * @param id product id
   * @return product with given id
   */
  @GetMapping("/api/orders/{id}")
  public ResponseEntity<Optional<Orders>> getOrderById(@PathVariable int id) {
    ResponseEntity<Optional<Orders>> response;
    Optional<Orders> order = orderService.getOrder(id);
    if (order.isEmpty()) {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      response = new ResponseEntity<>(order, HttpStatus.OK);
    }

    return response;
  }

  /**
   * Creates a new order.
   *
   * @param order The order object to be created.
   * @return ResponseEntity containing the created order and HTTP status code 201 (CREATED).
   */
  @PostMapping("/api/orders")
  public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
    orderService.createOrder(order);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  /**
   * Updates an existing product.
   *
   * @param id The ID of the order to be updated.
   * @param updatedOrder The updated order object.
   * @return ResponseEntity containing the updated order (Optional) and HTTP status code 200 (OK) if successful,
   * or HTTP status code 404 (NOT_FOUND) if the order with the given ID doesn't exist.
   */
  @PutMapping("/api/orders/{id}")
  public ResponseEntity<Optional<Orders>> updateOrder(@PathVariable int id, @RequestBody Optional<Orders> updatedOrder) {
    Optional<Orders> existingProduct = orderService.getOrder(id);
    if (existingProduct.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      orderService.updateOrder(updatedOrder);
      return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
  }

  /**
   * Deletes an order.
   *
   * @param id The ID of the order to be deleted.
   * @return ResponseEntity with HTTP status code 204 (NO_CONTENT) if successful,
   * or HTTP status code 404 (NOT_FOUND) if the order with the given ID doesn't exist.
   */
  @DeleteMapping("/api/orders/{id}")
  public ResponseEntity<Optional<Orders>> deleteOrder(@PathVariable int id) {
    Optional<Orders> existingProduct = orderService.getOrder(id);
    if (existingProduct.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      orderService.deleteOrder(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }
}
