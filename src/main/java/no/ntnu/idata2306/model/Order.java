package no.ntnu.idata2306.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents an order for a product.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
@Entity
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private int id;

  @Column(name = "price", nullable = false, updatable = false)
  private double salePrice;

  @Column(name = "Date", nullable = false, updatable = false)
  private LocalDateTime date;

  @ManyToOne()
  @JoinColumns({
          @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
  })
  private Product product;

  @ManyToOne()
  @JoinColumns({
          @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  })
  private User customer;

  /** Empty constructor. */
  public Order() {
  }

  /**
   * Creates a new instance of Order.
   *
   * @param date date of sale
   * @param product product
   * @param customer costumer
   */
  public Order(LocalDateTime date, Product product, User customer) {
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
