package no.ntnu.idata2306.dto;

/**
 * Represents data from user needed to sign up.
 *
 * @param email     email
 * @param firstName first name
 * @param lastName  last name
 * @param password  password
 * @author Edvin Astad
 * @version 04.04.2023
 */
public record SignUpDto(String email, String firstName, String lastName, String password) {
  /**
   * Creates a new instance of SignUpDto.
   */
  public SignUpDto {
  }

  /**
   * returns email.
   */
  @Override
  public String email() {
    return email;
  }

  /**
   * returns firstName.
   */
  @Override
  public String firstName() {
    return firstName;
  }

  /**
   * returns lastName.
   */
  @Override
  public String lastName() {
    return lastName;
  }

  /**
   * returns password.
   */
  @Override
  public String password() {
    return password;
  }
}
