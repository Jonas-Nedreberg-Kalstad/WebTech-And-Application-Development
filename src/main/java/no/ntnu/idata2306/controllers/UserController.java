package no.ntnu.idata2306.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Endpoints for managing users")
public class UserController {

  private final UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private static final String USER_NOT_FOUND = "User not found with ID: {}";

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
  @GetMapping("/{id}")
  @Operation(summary = "Get a user by ID", description = "Retrieves a user based on the provided ID")
  @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
  @ApiResponse(responseCode = "404", description = "User not found")
  public ModelAndView getUser(@PathVariable int id) {
    Optional<User> user = userService.getUserById(id);
    ModelAndView modelAndView;
    if (user.isEmpty()) {
      logger.warn(USER_NOT_FOUND, id);
      modelAndView = new ModelAndView("page-not-found");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
    } else {
      logger.info("User found with ID: {}", id);
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
  @GetMapping
  @Operation(summary = "Get all users", description = "Retrieves a list of all users")
  public List<User> getAll() {
    return userService.getAll();
  }

  /**
   * Creates a new user.
   *
   * @param user The User object to be created.
   * @return ResponseEntity containing the created User and HTTP status code 201 (CREATED).
   */
  @PostMapping
  @Operation(summary = "Create a new user", description = "Creates a new user")
  @ApiResponse(responseCode = "201", description = "User created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
  @ApiResponse(responseCode = "400", description = "Bad request")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      User createdUser = userService.createUser(user);
      logger.info("User created with ID: {}", createdUser.getId());
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } catch (Exception e) {
      logger.error("Error creating user", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /**
   * Updates an existing user.
   *
   * @param id   The ID of the user to be updated.
   * @param user The updated User object.
   * @return ResponseEntity containing the updated User if successful, or HTTP status code 404 (NOT_FOUND) if the user is not found.
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update an existing user", description = "Updates an existing user based on the provided ID")
  @ApiResponse(responseCode = "200", description = "User updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
  @ApiResponse(responseCode = "404", description = "User not found")
  public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
    Optional<User> existingUser = userService.getUserById(id);
    if (existingUser.isPresent()) {
      User updatedUser = userService.updateUser(id, user);
      logger.info("User updated with ID: {}", id);
      return ResponseEntity.ok(updatedUser);
    } else {
      logger.warn(USER_NOT_FOUND, id);
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Deletes a user.
   *
   * @param id The ID of the user to be deleted.
   * @return ResponseEntity with HTTP status code 204 (NO_CONTENT) if successful, or HTTP status code 404 (NOT_FOUND) if the user is not found.
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a user", description = "Deletes a user based on the provided ID")
  @ApiResponse(responseCode = "204", description = "User deleted")
  @ApiResponse(responseCode = "404", description = "User not found")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    Optional<User> existingUser = userService.getUserById(id);
    if (existingUser.isPresent()) {
      userService.deleteUser(id);
      logger.info("User deleted with ID: {}", id);
      return ResponseEntity.noContent().build();
    } else {
      logger.warn(USER_NOT_FOUND, id);
      return ResponseEntity.notFound().build();
    }
  }
}
