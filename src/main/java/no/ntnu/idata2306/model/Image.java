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
@Embeddable
public class Image {

  private String imageString;
  private String imageDescription;

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
    this.imageString = image;
    this.imageDescription = description;
  }


  /** Returns image. */
  public String getImage() {
    return imageString;
  }

  /** Returns description. */
  public String getImageDescription() {
    return imageDescription;
  }


  /**
   * Sets the value of the image field to given value.
   *
   * @param image image
   */
  public void setImage(String image) {
    this.imageString = image;
  }

  /**
   * Sets the value of the description field to given value.
   *
   * @param imageDescription description
   */
  public void setImageDescription(String imageDescription) {
    this.imageDescription = imageDescription;
  }


}
