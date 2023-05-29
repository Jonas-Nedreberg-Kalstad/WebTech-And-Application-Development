package no.ntnu.idata2306;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Responsible for running the application.
 *
 * @author Edvin Astad
 * @version 03.03.2023
 */
@SpringBootApplication
@EnableJpaRepositories

public class ProfessionalWebsiteBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProfessionalWebsiteBackendApplication.class, args);
  }
}
