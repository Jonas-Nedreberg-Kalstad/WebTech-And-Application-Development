package no.ntnu.idata2306.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

  private final UserDetailsService userDetailsService;
  private final JwtRequestFilter jwtRequestFilter;

  /**
   * Creates a new instance of SecurityConfiguration.
   *
   * @param userDetailsService userDetailsService
   * @param jwtRequestFilter jwtRequestFilter
   */
  public SecurityConfiguration(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
    this.userDetailsService = userDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  /**
   * This method will be called automatically by the framework to find out what authentication to use.
   * Here we tell that we want to load users from a database
   *
   * @param auth Authentication builder
   * @throws Exception e
   */
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  /**
   * Returns AuthenticationConfiguration.
   *
   * @return AuthenticationConfiguration
   * @throws Exception e
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }


  /**
   * This method is called to decide what encryption to use for password checking.
   *
   * @return The password encryptor
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * This method will be called automatically by the framework to find out what authentication to use.
   *
   * @param http HttpSecurity setting builder
   * @throws Exception e
   */
  @Bean
  public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
    // Allow JWT authentication
    http.cors().and().csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/signup").permitAll()
            .requestMatchers("/api/products").permitAll()
            .requestMatchers("/").permitAll()
            .requestMatchers("/images/**").permitAll()
            .requestMatchers("/style.css").permitAll()
            .requestMatchers("/api/products/{id}").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  /** Returns userDetailsService. */
  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

  /** returns jwtRequestFilter */
  public JwtRequestFilter getJwtRequestFilter() {
    return jwtRequestFilter;
  }
}

