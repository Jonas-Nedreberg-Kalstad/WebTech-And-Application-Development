package no.ntnu.idata2306;

import no.ntnu.idata2306.model.*;
import no.ntnu.idata2306.repositories.OrderRepository;
import no.ntnu.idata2306.repositories.ProductRepository;
import no.ntnu.idata2306.repositories.RoleRepository;
import no.ntnu.idata2306.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Responsible for populating database with dummy data for testing.
 *
 * @author Edvin Astad
 * @version 24.03.2023
 */
@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private final UserRepository userRepository;

  private final ProductRepository productRepository;

  private final RoleRepository roleRepository;

  private final OrderRepository orderRepository;

  private final Logger logger = LoggerFactory.getLogger("DummyInit");

  /**
   * Creates a new instance of DummyDataInitializer.
   *
   * @param userRepository    userRepository
   * @param productRepository productRepository
   * @param roleRepository    roleRepository
   * @param orderRepository   orderRepository
   */
  public DummyDataInitializer(UserRepository userRepository,
                              ProductRepository productRepository,
                              RoleRepository roleRepository,
                              OrderRepository orderRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.roleRepository = roleRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {




    logger.info("Importing test data...");



    if (userRepository.count() == 0 && productRepository.count() == 0 && orderRepository.count() == 0) {

      Image image1 = new Image("ItThings.jpeg", "Image of our some fancy IT things "
              + "(it has nothing to do with our product).");


      Role user = new Role("user");
      Role admin = new Role("admin");

      roleRepository.save(user);
      roleRepository.save(admin);

      User jon = new User(
              "Jon",
              "Smith",
              "Jons@ntnu.no",
              BCrypt.hashpw("IDATA2024isbased", BCrypt.gensalt()));

      jon.addRole(admin);
      jon.addRole(user);
      userRepository.save(jon);

      User jenny = new User(
              "Jenny",
              "Dow",
              "Jend@ntnu.no",
              BCrypt.hashpw("FuckIDATA2024", BCrypt.gensalt()));

      userRepository.save(jenny);

      Product consultation = new Product(
              "Consultation",
              100000,
              "Consultation services"
      );

      productRepository.save(consultation);


      Product itSolution = new Product(
              "IT solution",
              150000,
              "It solution",
              image1

      );

      productRepository.save(itSolution);

      Orders order = new Orders(LocalDateTime.now(), itSolution, jenny);
      orderRepository.save(order);

      logger.info("DONE importing test data");
    } else {
      logger.info("Database already populated.");
    }
  }
}

