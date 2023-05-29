package no.ntnu.idata2306.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents an order for a product.
 *
 * @author Edvin Astad, Jonas N.Kalstad
 * @version 04.04.2023
 */
@Entity
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  @Schema(description = "ID of the order")
  private int id;

  @Column(name = "price", nullable = false, updatable = false)
  @Schema(description = "Price of the order")
  private double salePrice;

  @Column(name = "Date", nullable = false, updatable = false)
  @Schema(description = "Date of the order")
  private LocalDateTime date;

  @JsonManagedReference
  @ManyToOne()
  @JoinColumns({
          @JoinColumn(name = "product_id", referencedColumnName = "id")
  })
  @Schema(description = "Product bought in the order")
  private Product product;

  @JsonManagedReference
  @ManyToOne()
  @JoinColumns({
          @JoinColumn(name = "user_id", referencedColumnName = "id")
  })
  @Schema(description = "Customer that ordered")
  private User customer;

  /** Empty constructor. */
  public Orders() {
  }

  /**
   * Creates a new instance of Orders.
   *
   * @param date date of sale
   * @param product product
   * @param customer costumer
   */
  public Orders(LocalDateTime date, Product product, User customer) {
    this.salePrice = product.getPrice();
    this.date = date;
    this.product = product;
    this.customer = customer;
  }

  /** Returns id. */
  public int getId() {
    return id;
  }

  /** Returns salePrice. */
  public double getSalePrice() {
    return salePrice;
  }

  /** Returns date. */
  public LocalDateTime getDate() {
    return date;
  }

  /** Returns product. */
  public Product getProduct() {
    return product;
  }

  /** Returns user. */
  public User getCustomer() {
    return customer;
  }

  /**
   * Sets the value of the id field to given value.
   *
   * @param id id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the value of the salePrice field to given value.
   *
   * @param salePrice salePrice
   */
  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }

  /**
   * Sets the value of the date field to given value.
   *
   * @param date date of purchase
   */
  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  /**
   * Sets the value of the product field to given value.
   *
   * @param product product
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Sets the value of the costumer field to given value.
   *
   * @param costumer costumer
   */
  public void setCustomer(User costumer) {
    this.customer = costumer;
  }
}
