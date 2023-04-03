package no.ntnu.idata2306.model;

import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * represent a user for the website.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@Schema(description = "Registered user on website", title = "User")
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private int id;
  @Column(name = "email", nullable = false, unique = true, updatable = false)
  private String email;
  @Column(name = "first name", nullable = false, unique = false)
  private String firstName;
  @Column(name = "last name", nullable = false, unique = false)
  private String lastName;
  @Column(name = "password", nullable = false, unique = false)
  private String password;
  @Column(name = "salt", nullable = false, unique = false, updatable = false)
  private String salt;

  /**
   * Constructor with parameters.
   *
   * @param email     users email.
   * @param firstName first and middle name(s) name of user.
   * @param lastName  surname of user.
   * @param password  users password.
   */
  public User(String email, String firstName, String lastName, String password) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salt = gensalt();
    this.password = hashpw(password, salt);
  }

  /**
   * Empty constructor.
   */
  public User() {
  }

  /**
   * returns id.
   */
  public int getId() {
    return id;
  }

  /**
   * returns email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * returns first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * returns surname.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * returns hashed password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * returns salt.
   */
  public String getSalt() {
    return salt;
  }

  /**
   * sets the value of id field to given value.
   *
   * @param id id of user
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * sets the value of email field to given value.
   *
   * @param email email of user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * sets the value of firstName field to given value.
   *
   * @param firstName first name of user.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * sets the value of lastName field to given value.
   *
   * @param lastName surname of user.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * sets password to new password provided by user.
   *
   * @param password hash which will correspond with user password + salt.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * sets the value of the salt field to given value.
   *
   * <b>Should never be used as salt should be final, but is required by spring</b>
   *
   * @param salt salt
   */
  public void setSalt(String salt) {
    this.salt = salt;
  }

}
