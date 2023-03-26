package no.ntnu.idata2306.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
  @Column(name = "id", nullable = false)
  private int id;
  @Column(name = "name", nullable = false)
  private String productName;
  @Column(name = "price", nullable = false)
  private double price;
  @Column(name = "description", nullable = false)
  private String description;
  //Image stored as String which represents the filename of the image.
  @Column(name = "image", nullable = false)
  private String image;

  /**
   * Creates a new instance of Product.
   *
   * @param productName product name
   * @param price       price
   * @param description description
   * @param image       hyperlink to image
   */
  public Product(String productName, double price, String description, String image) {
    this.productName = productName;
    this.price = price;
    this.description = description;
    this.image = image;
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
  public String getImage() {
    return image;
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
  public void setImage(String image) {
    this.image = image;
  }
}
