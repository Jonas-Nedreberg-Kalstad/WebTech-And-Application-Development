package no.ntnu.idata2306.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfiguration {

  private final UserDetailsService userDetailsService;
  private final String ADMIN = "admin";

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
    // Setting up the authorization requests, starting from the most restrictive to the least restrictive
    http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/products/{id}").hasAuthority(ADMIN)
            .requestMatchers("/api/products").hasAnyAuthority("user", ADMIN)
            .requestMatchers("/api/users").hasAnyAuthority(ADMIN)
            .requestMatchers("/api/users/{id}").hasAnyAuthority(ADMIN)
            .requestMatchers("/login").anonymous()
            .requestMatchers("/signup").anonymous()
            .requestMatchers("/products").permitAll()
            .requestMatchers("/products/{id}").permitAll()
            .requestMatchers("/").permitAll()
            .requestMatchers("/about").permitAll()
            .requestMatchers("/contact").permitAll()
            .requestMatchers("/images/**").permitAll()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/js/**").permitAll()
            .requestMatchers("/api/orders/{id}").hasAnyAuthority(ADMIN)
            .requestMatchers("/api/orders").hasAnyAuthority(ADMIN)
            .requestMatchers("/access-denied").permitAll()
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
            .and().formLogin().loginPage("/login").usernameParameter("email")
            .and().logout().logoutSuccessUrl("/");
    return http.build();
  }

  /**
   * Returns a custom AccessDeniedHandler
   *
   * @return returns custom AccessDeniedHandler
   */
  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  /**
   * Returns userDetailsService.
   */
  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

}

