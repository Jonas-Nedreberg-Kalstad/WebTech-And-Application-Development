package no.ntnu.idata2306.dto;

/**
 * Data that we will send as a response to the user when the authentication is successful.
 *
 * @param jwt jwt
 * @author Edvin Astad
 * @version 04.04.2023
 */
public record AuthenticationResponse(String jwt) {

  /**
   * Returns jwt.
   */
  @Override
  public String jwt() {
    return jwt;
  }
}
