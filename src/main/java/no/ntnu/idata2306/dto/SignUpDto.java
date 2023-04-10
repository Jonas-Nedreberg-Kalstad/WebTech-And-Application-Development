package no.ntnu.idata2306.dto;

/**
 * Represents data from user needed to sign up.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
public class SignUpDto {

  private String firstName;
  private String lastName;
  private String email;
  private String password;



  /**
   * Creates a new instance of SignUpDto.
   */
  public SignUpDto() {
  }

  /**
   * returns email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email
   * @param email email to be set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * returns firstName.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets firstName
   * @param firstName firstName to be set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * returns lastName.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets lastName
   * @param lastName lastName to be set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * returns password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password
   * @param password password to be set
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
