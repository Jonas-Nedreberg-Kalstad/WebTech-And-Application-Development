package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.security.JwtUtil;
import no.ntnu.idata2306.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for authentication requests.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
@RestController
public class AuthenticationController {

  private AuthenticationManager authenticationManager;

  private UserService userService;

  private JwtUtil jwtUtil;
}
