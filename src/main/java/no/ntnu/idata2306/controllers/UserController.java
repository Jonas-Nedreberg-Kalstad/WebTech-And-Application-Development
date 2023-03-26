package no.ntnu.idata2306.controllers;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.idata2306.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for all endpoints related to Users.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@CrossOrigin
@RestController
public class UserController {

  public UserController(UserService userService) {
    this.userService = userService;
  }

  private UserService userService;

  private static final Logger logger =
          LoggerFactory.getLogger(UserController.class.getSimpleName());

}
