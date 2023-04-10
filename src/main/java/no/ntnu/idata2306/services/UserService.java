package no.ntnu.idata2306.services;

import java.util.Optional;

import no.ntnu.idata2306.dto.SignUpDto;
import no.ntnu.idata2306.model.User;
import no.ntnu.idata2306.repositories.UserRepository;
import no.ntnu.idata2306.security.AccessUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Business logic for User.
 *
 * @author Edvin Astad
 * @version 22.03.2023
 */
@Service
public class UserService implements UserDetailsService {

  private static final int MIN_PASSWORD_LENGTH = 4;
  private final UserRepository userRepository;

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
   * @param userInfo information provided by SignUpDto instance
   */
  public void createUser(SignUpDto userInfo) {
    if (!validEmail(userInfo.getEmail())) {
      throw new IllegalArgumentException("Invalid email format.");
    }

    if (!validPassword(userInfo.getPassword())) {
      throw new IllegalArgumentException("Invalid password.");
    }

    if (userInfo.getFirstName().trim().equals("") || userInfo.getLastName().trim().equals("")) {
      throw new IllegalArgumentException("Name fields must be filled out.");
    }

    try {
      loadUserByUsername(userInfo.getEmail());
      throw new IllegalArgumentException("Email already registered.");
    } catch (NullPointerException e) {
      userRepository.save(new User(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getEmail(), createHash(userInfo.getPassword())));
    }
  }

  /**
   * Returns a user registered with given email if registered.
   *
   * @param email email
   * @return user with given email
   * @throws NullPointerException if there is no user registered with given email
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws NullPointerException {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return new AccessUserDetails(user.get());
    } else {
      throw new NullPointerException("User with email: " + email + " not found.");
    }
  }

  /**
   * Create a secure hash of a password
   *
   * @param password Plaintext password
   * @return BCrypt hash, with random salt
   */
  private String createHash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  /**
   * Checks if a submitted email follows the email format.
   *
   * <b>Currently checks if:
   * -email contains an @-symbol, and only one @-symbol
   * -email does not contain empty spaces
   * -there is at least one character before the @-symbol
   * -there is at least one full stop after @-symbol
   * -there are no instances of two full stops with no text in between
   * -there are at least one character before and after the first and last full stop after the @-symbol</b>
   *
   * @param potentialEmail submitted email
   * @return true if submitted email is following a valid format, false otherwise.
   */
  private static boolean validEmail(String potentialEmail) {
    boolean valid = false;
    //Confirms the String contains a @-symbol.
    boolean containsAt = potentialEmail.contains("@");
    //Checks for spaces which are not allowed in an email address.
    boolean containsSpace = potentialEmail.contains(" ");
    if (containsAt && !containsSpace) {
      String[] splitString = potentialEmail.split("@");
      //If there is exactly one @-symbol int the String the split will produce an array of length 2.
      boolean oneAt = splitString.length == 2;
      if (oneAt) {
        String s1 = splitString[0];
        String s2 = splitString[1];
        //The first part of en emil address can not be empty.
        boolean s1Valid = s1.length() > 0;
        //The second part of an email address must be at least 3 characters long and contain a full stop.
        boolean s2Valid = s2.contains(".");
        if (s2Valid) {
          String[] s2Sections = s2.split(".");
          int i = 0;
          //Checks if each section is empty.
          while (s2Valid && i < s2Sections.length) {
            String currentSection = s2Sections[0];
            if (currentSection.equals("")) {
              s2Valid = false;
            }
            i++;
          }
        }
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

  /**
   * Returns the user of the curren session.
   *
   * @return user of current session, null if there is no user in session.
   */
  public User getSessionUser() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    String email = authentication.getName();
    return userRepository.findByEmail(email).orElse(null);
  }
}
