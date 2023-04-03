package no.ntnu.idata2306;

import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.model.User;
import no.ntnu.idata2306.repositories.ProductRepository;
import no.ntnu.idata2306.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Responsible for populating database with dummy data for testing.
 *
 * @author Edvin Astad
 * @version 24.03.2023
 */
@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private UserRepository userRepository;

  private ProductRepository productRepository;

  private final Logger logger = LoggerFactory.getLogger("DummyInit");

  /**
   * Creates a new instance of DummyDataInitializer.
   *
   * @param userRepository    userRepository
   * @param productRepository productRepository
   */
  public DummyDataInitializer(UserRepository userRepository, ProductRepository productRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {

    logger.info("Importing test data...");

    if (userRepository.count() == 0 && productRepository.count() == 0) {
      User jon = new User(
              "Jons@ntnu.no",
              "Jon",
              "Smith",
              "IDATA2024isbased");

      userRepository.save(jon);

      User jenny = new User(
              "Jend@ntnu.no",
              "Jenny",
              "Dow",
              "FuckIDATA2024");

      userRepository.save(jenny);

      Product consultation = new Product(
              "Consultation",
              100000,
              "Consultation services",
              "Consultants.jpeg"
      );

      productRepository.save(consultation);

      Product itSolution = new Product(
              "IT solution",
              150000,
              "It solution",
              "ItThings.jpeg"
      );

      productRepository.save(itSolution);


      logger.info("DONE importing test data");
    } else {
      logger.info("Database already populated.");
    }
  }
}
