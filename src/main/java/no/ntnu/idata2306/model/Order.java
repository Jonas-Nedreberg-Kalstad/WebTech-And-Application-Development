package no.ntnu.idata2306.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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
  private Date date;

  @ManyToOne
  private Product product;

  @ManyToOne()
  private User costumer;

  /** Empty constructor. */
  public Order() {
  }

  public Order(double salePrice, Date date, Product product, User costumer) {
    this.salePrice = salePrice;
    this.date = date;
    this.product = product;
    this.costumer = costumer;
  }
}
