package no.ntnu.idata2306.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * represents a product available on the website.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@Schema(description = "Product offered on website", title = "Product")
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private int id;
  @Column(name = "name", nullable = false)
  private String productName;
  @Column(name = "price", nullable = false)
  private double price;
  @Column(name = "description", nullable = false)
  private String description;
  @Embedded
  private Image image;
  @OneToMany(mappedBy = "product")
  private Set<Orders> orders = new LinkedHashSet<>();

  /**
   * Creates a new instance of Product.
   *
   * @param productName product name
   * @param price       price
   * @param description description
   * @param image       hyperlink to image
   */
  public Product(String productName, double price, String description, Image image) {
    this.productName = productName;
    this.price = price;
    this.description = description;
    this.image = image;
  }

  /**
   * Creates a new instance of Product.
   *
   * @param productName product name
   * @param price       price
   * @param description description
   */
  public Product(String productName, double price, String description) {
    this.productName = productName;
    this.price = price;
    this.description = description;
  }

  /**
   * empty constructor.
   */
  public Product() {
  }

  /**
   * returns id.
   */
  public int getId() {
    return id;
  }

  /**
   * returns productName.
   */
  public String getProductName() {
    return productName;
  }

  /**
   * returns price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * returns description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * returns image.
   */
  public Image getImage() {
    return image;
  }

  /** returns order */
  public Set<Order> getOrders() {
    return orders;
  }

  /**
   * sets the value of id field to given value.
   *
   * @param id id of product
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * sets value of productName field to given value.
   *
   * @param productName name of product
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * sets value of price field to given value.
   *
   * @param price price of product
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * sets the value of the description field to given value.
   *
   * @param description description of product
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * sets the value of the image field to given value.
   *
   * @param image image associated with product
   */
  public void setImage(Image image) {
    this.image = image;
  }

  /**
   * sets the value of the image orders to given value.
   *
   * @param orders orders
   */
  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }
}
