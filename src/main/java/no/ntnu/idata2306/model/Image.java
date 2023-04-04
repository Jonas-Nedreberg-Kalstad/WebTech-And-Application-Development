package no.ntnu.idata2306.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

/**
 * Represents an image which is to be displayed on the wbsite.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
@Schema(name = "images", description = "Images for website")
@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private int id;
  @Column(name = "image", nullable = false)
  private String image;
  @Column(name = "description")
  private String description;
  @OneToOne(mappedBy = "image")
  private Product product;

  /** Empty constructor. */
  public Image() {
  }

  /**
   * Creates a new instance of Image.
   *
   * @param image image
   * @param description description
   */
  public Image(String image, String description) {
    this.image = image;
    this.description = description;
  }

  /** Returns id */
  public int getId() {
    return id;
  }

  /** Returns image */
  public String getImage() {
    return image;
  }

  /** Returns description. */
  public String getDescription() {
    return description;
  }

  /** Returns product */
  public Product getProduct() {
    return product;
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
   * Sets the value of the image field to given value.
   *
   * @param image image
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * Sets the value of the description field to given value.
   *
   * @param description description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the value of the product field to given value.
   *
   * @param product product
   */
  public void setProduct(Product product) {
    this.product = product;
  }
}
