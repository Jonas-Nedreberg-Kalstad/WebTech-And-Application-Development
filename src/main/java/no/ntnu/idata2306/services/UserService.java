package no.ntnu.idata2306.services;

import java.util.Optional;
import no.ntnu.idata2306.model.User;
import no.ntnu.idata2306.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Business logic for User.
 *
 * @author Edvin Astad
 * @version 22.03.2023
 */
@Service
public class UserService {

  private static final int MIN_PASSWORD_LENGTH = 8;

  private UserRepository userRepository;

  /**
   * creates a new instance of userService.
   *
   * @param userRepository userRepository
   */
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Creates a new user if params are valid and email is not yet registered.
   *
   * @param email email
   * @param firstname fist name
   * @param lastName last name
   * @param password password in plain text
   * @return true if user is created, false otherwise.
   */
  public boolean createNewUser(String email, String firstname, String lastName, String password) {
    if (!validEmail(email)) {
      throw new IllegalArgumentException("Invalid email format.");
    }

    if (!validPassword(password)) {
      throw new IllegalArgumentException("Invalid password.");
    }

    if (firstname.trim().equals("") || lastName.trim().equals("")) {
      throw new IllegalArgumentException("Name fields must be filled out.");
    }

    boolean userCreated = false;
    try {
      findUserByEmail(email);
    } catch (NullPointerException e) {
      userRepository.save(new User(email, firstname, lastName, password));
      userCreated = true;
    }

    return userCreated;
  }

  /**
   * Returns a user registered with given email if registered.
   *
   * @param email email
   * @return user with given email
   * @throws NullPointerException if there is no user registered with given email
   */
  private User findUserByEmail(String email) throws NullPointerException {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new NullPointerException("User with email: " + email + " not registered.");
    }
  }

  /**
   * Checks if a submitted email follows the email format.
   *
   * @param potentialEmail submitted email
   * @return true if submitted email is following a valid format, false otherwise.
   */
  private static boolean validEmail(String potentialEmail) {
    boolean valid = false;
    boolean containsAt = potentialEmail.contains("@");
    boolean containsSpace = potentialEmail.contains(" ");
    if (containsAt && !containsSpace) {
      String[] splitString = potentialEmail.split("@");
      boolean oneAt = splitString.length == 2;
      if (oneAt) {
        String s1 = splitString[0];
        String s2 = splitString[1];
        boolean s1Valid = s1.length() > 0;
        boolean s2Valid = s2.length() > 2 && s2.contains(".");
        valid = s1Valid && s2Valid;
      }
    } else {
      valid = false;
    }
    return valid;
  }

  /**
   * Checks if a submitted password is valid.
   *
   * @param password submitted password
   * @return true if password is valid, false otherwise
   */
  private static boolean validPassword(String password) {
    return !password.contains(" ") && password.length() > MIN_PASSWORD_LENGTH;
  }
}
