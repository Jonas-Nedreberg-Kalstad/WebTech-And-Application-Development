package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.dto.SignUpDto;
import no.ntnu.idata2306.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
