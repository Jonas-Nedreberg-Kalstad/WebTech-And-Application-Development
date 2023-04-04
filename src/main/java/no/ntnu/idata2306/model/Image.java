package no.ntnu.idata2306.model;

import jakarta.persistence.*;

/**
 * Represents an image which is to be displayed on the website.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
@Embeddable
public class Image {
  private String imageLink;
  private String imageDescription;

  /** Empty constructor. */
  public Image() {
  }

  /**
   * Creates a new instance of Image.
   *
   * @param imageLink image
   * @param description description
   */
  public Image(String imageLink, String description) {
    this.imageLink = imageLink;
    this.imageDescription = description;
  }


  /** Returns imageLink. */
  public String getImageLink() {
    return imageLink;
  }

  /** Returns imageDescription. */
  public String getImageDescription() {
    return imageDescription;
  }


  /**
   * Sets the value of the image field to given value.
   *
   * @param imageLink image
   */
  public void setImageLink(String imageLink) {
    this.imageLink = imageLink;
  }

  /**
   * Sets the value of the imageDescription field to given value.
   *
   * @param imageDescription description
   */
  public void setImageDescription(String imageDescription) {
    this.imageDescription = imageDescription;
  }
}
