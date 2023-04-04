package no.ntnu.idata2306.dto;

/**
 * Represent data provided by user during a login request.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
public class AuthenticationRequest {
  private String email;
  private String password;

  /** Empty constructor */
  public AuthenticationRequest() {
  }

  /**
   * Creates a new instance of AuthenticationRequest.
   *
   * @param email email provided by user
   * @param password password provided by user
   */
  public AuthenticationRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /** returns email */
  public String getEmail() {
    return email;
  }

  /**
   * sets the value of the email field to given value.
   *
   * @param email email
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /** Returns password. */
  public String getPassword() {
    return password;
  }

  /**
   * sets the value of the password field to given value.
   *
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
