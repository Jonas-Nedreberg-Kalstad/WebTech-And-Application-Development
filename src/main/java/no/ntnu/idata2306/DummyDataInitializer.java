//package no.ntnu.idata2306;
//
//import no.ntnu.idata2306.model.Image;
//import no.ntnu.idata2306.model.Product;
//import no.ntnu.idata2306.model.Role;
//import no.ntnu.idata2306.model.User;
//import no.ntnu.idata2306.repositories.ProductRepository;
//import no.ntnu.idata2306.repositories.RoleRepository;
//import no.ntnu.idata2306.repositories.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
///**
// * Responsible for populating database with dummy data for testing.
// *
// * @author Edvin Astad
// * @version 24.03.2023
// */
//@Component
//public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
//
//  private final UserRepository userRepository;
//
//  private final ProductRepository productRepository;
//
//  private final RoleRepository roleRepository;
//
//  private final Logger logger = LoggerFactory.getLogger("DummyInit");
//
//  /**
//   * Creates a new instance of DummyDataInitializer.
//   *
//   * @param userRepository    userRepository
//   * @param productRepository productRepository
//   * @param roleRepository    roleRepository
//   */
//  public DummyDataInitializer(UserRepository userRepository,
//                              ProductRepository productRepository,
//                              RoleRepository roleRepository
//  ) {
//    this.userRepository = userRepository;
//    this.productRepository = productRepository;
//    this.roleRepository = roleRepository;
//  }
//
//  @Override
//  public void onApplicationEvent(ApplicationReadyEvent event) {
//
//    Image image1 = new Image("ItThings.jpeg", "Image of our some fancy IT things "
//            + "(it has nothing to do with our product).");
//
//
//
//
//
//    logger.info("Importing test data...");
//
//
//
//    if (userRepository.count() == 0 && productRepository.count() == 0) {
//
//      Role user = new Role("user");
//      Role admin = new Role("admin");
//
//      roleRepository.save(user);
//      roleRepository.save(admin);
//
//      User jon = new User(
//              "Jons@ntnu.no",
//              "Jon",
//              "Smith",
//              "IDATA2024isbased"
//      );
//
//      jon.addRole(admin);
//      jon.addRole(user);
//      userRepository.save(jon);
//
//      User jenny = new User(
//              "Jend@ntnu.no",
//              "Jenny",
//              "Dow",
//              "FuckIDATA2024");
//
//      userRepository.save(jenny);
//
//      Product consultation = new Product(
//              "Consultation",
//              100000,
//              "Consultation services"
//      );
//
//      productRepository.save(consultation);
//
//
//      Product itSolution = new Product(
//              "IT solution",
//              150000,
//              "It solution"
//
//      );
//
//      //itSolution.setImage(image1);
//      productRepository.save(itSolution);
//
//
//      logger.info("DONE importing test data");
//    } else {
//      logger.info("Database already populated.");
//    }
//
//
//  }
//}
