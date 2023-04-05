package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.dto.AuthenticationRequest;
import no.ntnu.idata2306.dto.AuthenticationResponse;
import no.ntnu.idata2306.dto.SignUpDto;
import no.ntnu.idata2306.security.JwtUtil;
import no.ntnu.idata2306.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for authentication requests.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
@RestController
@CrossOrigin
public class AuthenticationController {


  private AuthenticationManager authenticationManager;
  private UserService userService;
  private JwtUtil jwtUtil;

  public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  /**
   * HTTP POST request to /authenticate
   *
   * @param authenticationRequest The request JSON object containing username and password
   * @return OK + JWT token; Or UNAUTHORIZED
   */
  @PostMapping("/api/authenticate")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(),
              authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
    final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  /**
   * This method processes data received from the sign-up form (HTTP POST)
   *
   * @return Name of the template for the result page
   */
  @PostMapping("/api/signup")
  public ResponseEntity<String> signupProcess(@RequestBody SignUpDto signupData) {
    ResponseEntity<String> response;
    try {
      userService.createUser(signupData);
      response = new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

}
