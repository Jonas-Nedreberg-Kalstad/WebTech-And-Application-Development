package no.ntnu.idata2306.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  private final UserDetailsService userDetailsService;

  /**
   * Creates a new instance of SecurityConfiguration.
   *
   * @param userDetailsService userDetailsService
   */
  public SecurityConfiguration(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * This method will be called automatically by the framework to find out what authentication to use.
   * Here we tell that we want to load users from a database
   *
   * @param auth Authentication builder
   * @throws Exception e
   */
  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
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
    http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/signup").permitAll()
            .requestMatchers("/api/products").hasAuthority("user")
            .requestMatchers("/").permitAll()
            .requestMatchers("/images/**").permitAll()
            .requestMatchers("/style.css").permitAll()
            .requestMatchers("/api/products/{id}").hasAuthority("admin")
            .and().formLogin().loginPage("/login").usernameParameter("email")
            .and().logout().logoutSuccessUrl("/");
    return http.build();
  }

  /** Returns userDetailsService. */
  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

}

