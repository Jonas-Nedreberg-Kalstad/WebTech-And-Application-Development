package no.ntnu.idata2306.services;

import no.ntnu.idata2306.model.Orders;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.model.User;
import no.ntnu.idata2306.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  /**
   * returns all products stored in product repository.
   *
   * @return all products stored in product repository
   */
  public List<Orders> getAll() {
    List<Orders> products = new LinkedList<>();
    orderRepository.findAll().forEach(products::add);
    return products;
  }

  /**
   * Creates a new instance of OrderService.
   *
   * @param orderRepository order database.
   */
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  /**
   * Saves an order to database.
   *
   * @param order which is to be saved.
   */
  public void createOrder(Orders order) {
    this.orderRepository.save(order);
  }

  public Collection<Orders> findOrdersByUser(User user) {
    List<Orders> ordersByUser = new ArrayList<>();
    orderRepository.findAll()
            .forEach(order -> {
              if (order.getCustomer() == user) {
              ordersByUser.add(order);
            }
            });
    return ordersByUser;
  }

  /**
   * Retrieves the order with the given ID from the database, if it exists.
   *
   * @param id The ID of the order.
   * @return An Optional containing the order with the given ID, or empty if no order is found.
   */
  public Optional<Orders> getOrder(int id) {
    return orderRepository.findById(id);
  }

  /**
   * Updates an existing order by saving the changes to the database.
   *
   * @param optionalOrder The order to be updated.
   */
  public void updateOrder(Optional<Orders> optionalOrder) {
    optionalOrder.ifPresent(orderRepository::save);
  }

  /**
   * Deletes a product from the database based on the provided ID.
   * Should never be used as
   *
   * @param id The ID of the order to be deleted.
   */
  public void deleteOrder(int id) {
    orderRepository.deleteById(id);
  }

}
