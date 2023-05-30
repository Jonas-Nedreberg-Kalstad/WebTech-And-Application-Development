package no.ntnu.idata2306.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.idata2306.model.Orders;
import no.ntnu.idata2306.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.Optional;

/**
 * REST API controller for all endpoints related to orders.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "Endpoints for managing orders")
public class OrderController {

  private final OrderService orderService;

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  private static final String ORDER_NOT_FOUND = "Order not found with ID: {}";


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
  @GetMapping
  @Operation(summary = "Get all orders", description = "Retrieves a list of all orders.")
  public List<Orders> getAll() {
    return orderService.getAll();
  }

  /**
   * Get an order from database matching given id if it exists.
   *
   * @param id potential id of an order
   * @return a ModelAndView containing order in JSON format or page-not-found
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get order by ID", description = "Retrieves an order based on the provided ID.")
  @ApiResponse(responseCode = "200", description = "Order found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
  @ApiResponse(responseCode = "404", description = "Order not found")
  public ModelAndView getOrder(@PathVariable int id) {
    Optional<Orders> order = orderService.getOrder(id);
    ModelAndView modelAndView;
    if (order.isEmpty()) {
      logger.warn(ORDER_NOT_FOUND, id);
      modelAndView = new ModelAndView("page-not-found");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
    } else {
      logger.info("Order found with ID: {}", id);
      // The order is displayed in JSON format
      modelAndView = new ModelAndView();
      modelAndView.addObject("order", order.get());
      modelAndView.setView(new MappingJackson2JsonView());
    }
    return modelAndView;
  }

  /**
   * Creates a new order.
   *
   * @param order The order object to be created.
   * @return ResponseEntity containing the created order and HTTP status code 201 (CREATED).
   */
  @PostMapping
  @Operation(summary = "Create a new order", description = "Creates a new order.")
  @ApiResponse(responseCode = "201", description = "Order created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
  @ApiResponse(responseCode = "400", description = "Bad request")
  public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
    try {
      Orders createdOrder = orderService.createOrder(order);
      logger.info("Order created with ID: {}", createdOrder.getId());
      return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    } catch (Exception e) {
      logger.error("Error creating order", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /**
   * Updates an existing order.
   *
   * @param id           The ID of the order to be updated.
   * @param updatedOrder The updated order object.
   * @return ResponseEntity containing the updated order (Optional) and HTTP status code 200 (OK) if successful,
   * or HTTP status code 404 (NOT_FOUND) if the order with the given ID doesn't exist.
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update an existing order", description = "Updates an existing order based on the provided ID.")
  @ApiResponse(responseCode = "200", description = "Order updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
  @ApiResponse(responseCode = "404", description = "Order not found")
  public ResponseEntity<Optional<Orders>> updateOrder(@PathVariable int id, @RequestBody Optional<Orders> updatedOrder) {
    Optional<Orders> existingOrder = orderService.getOrder(id);
    if (existingOrder.isEmpty()) {
      logger.warn(ORDER_NOT_FOUND, id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      orderService.updateOrder(updatedOrder);
      logger.info("Order updated with ID: {}", id);
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
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an order", description = "Deletes an order based on the provided ID.")
  @ApiResponse(responseCode = "204", description = "Order deleted")
  @ApiResponse(responseCode = "404", description = "Order not found")
  public ResponseEntity<Optional<Orders>> deleteOrder(@PathVariable int id) {
    Optional<Orders> existingOrder = orderService.getOrder(id);
    if (existingOrder.isEmpty()) {
      logger.warn(ORDER_NOT_FOUND, id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      orderService.deleteOrder(id);
      logger.info("Order deleted with ID: {}", id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }
}
