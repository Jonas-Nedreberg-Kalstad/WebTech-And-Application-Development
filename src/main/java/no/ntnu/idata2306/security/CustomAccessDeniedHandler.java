package no.ntnu.idata2306.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * CustomAccessDeniedHandler is an implementation of the AccessDeniedHandler interface.
 * It handles access denied exceptions by redirecting the user to a designated access-denied page.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  /**
   * Handles the access denied exception by setting the response status to 403 Forbidden
   * and redirecting the user to the access-denied page.
   *
   * @param request The HttpServletRequest representing the current request.
   * @param response The HttpServletResponse representing the response to be sent.
   * @param accessDeniedException The AccessDeniedException that occurred.
   * @throws IOException If an I/O error occurs during the redirect.
   */
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.sendRedirect("/access-denied");
  }
}
