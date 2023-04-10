package no.ntnu.idata2306.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;


  /**
   * Creates a new instance of JwtRequestFilter.
   *
   * @param userDetailsService userDetailsService
   * @param jwtUtil jwtUtil
   */
  public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;
    try {
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
        username = jwtUtil.extractUser(jwt);
      }

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
          upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(upat);
        }
      }
    } catch (JwtException ex) {
      logger.info("Error while parsing JWT token: " + ex.getMessage());
    }
    filterChain.doFilter(request, response);
  }

  /** Returns userDetailsService. */
  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

  /** Returns JwtUtil. */
  public JwtUtil getJwtUtil() {
    return jwtUtil;
  }


}

