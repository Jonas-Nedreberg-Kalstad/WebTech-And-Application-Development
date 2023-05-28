package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.model.User;
import no.ntnu.idata2306.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.Optional;

/**
 * REST API controller for all endpoints related to Users.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@CrossOrigin
@RestController
public class UserController {

  private final UserService userService;

  private static final Logger logger =
          LoggerFactory.getLogger(UserController.class.getSimpleName());

  /**
   * Creates a new instance of UserController.
   *
   * @param userService userService
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Get a user from database matching given id if it exists.
   *
   * @param id potential id of a user
   * @return a ModelAndView containing user in JSON format or page-not-found
   */
  @GetMapping("/api/users/{id}")
  public ModelAndView getUser(@PathVariable int id) {
    Optional<User> user = userService.getUserById(id);
    ModelAndView modelAndView;
    if (user.isEmpty()) {
      modelAndView = new ModelAndView("page-not-found");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
    } else {
      // The user is displayed in JSON format
      modelAndView = new ModelAndView();
      modelAndView.addObject("user", user.get());
      modelAndView.setView(new MappingJackson2JsonView());
    }
    return modelAndView;
  }

  /**
   * Get all users stored in database.
   *
   * @return List of all available users.
   */
  @GetMapping("/api/users")
  public List<User> getAll() {
    return userService.getAll();
  }

  /**
   * Creates a new user.
   *
   * @param user The User object to be created.
   * @return ResponseEntity containing the created User and HTTP status code 201 (CREATED).
   */
  @PostMapping("/api/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  /**
   * Updates an existing user.
   *
   * @param id   The ID of the user to be updated.
   * @param user The updated User object.
   * @return ResponseEntity containing the updated User if successful, or HTTP status code 404 (NOT_FOUND) if the user is not found.
   */
  @PutMapping("/api/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
    Optional<User> existingUser = userService.getUserById(id);
    if (existingUser.isPresent()) {
      User updatedUser = userService.updateUser(id, user);
      return ResponseEntity.ok(updatedUser);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Deletes a user.
   *
   * @param id The ID of the user to be deleted.
   * @return ResponseEntity with HTTP status code 204 (NO_CONTENT) if successful, or HTTP status code 404 (NOT_FOUND) if the user is not found.
   */
  @DeleteMapping("/api/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    Optional<User> existingUser = userService.getUserById(id);
    if (existingUser.isPresent()) {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
