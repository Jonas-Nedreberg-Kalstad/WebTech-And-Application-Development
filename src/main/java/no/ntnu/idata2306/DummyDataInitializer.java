package no.ntnu.idata2306;

import no.ntnu.idata2306.model.*;
import no.ntnu.idata2306.repositories.OrderRepository;
import no.ntnu.idata2306.repositories.ProductRepository;
import no.ntnu.idata2306.repositories.RoleRepository;
import no.ntnu.idata2306.repositories.UserRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

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

      Image image1 = new Image("Online Scheduling Software.jpg",
              "Image of our some fancy IT things "
              + "(it has nothing to do with our product).");

      Image image2 = new Image("Proflex BPA Solutions.jpg",
              "Image of our some fancy IT things "
              + "(it has nothing to do with our product).");

      Image image3 = new Image("ProFlex Tax Solutions.jpg",
              "Image of our some fancy IT things "
              + "(it has nothing to do with our product).");

      Image image4 = new Image("ProFlex Accounting Solutions.jpg",
              "Image of our some fancy IT things "
              + "(it has nothing to do with our product).");

      Image image5 = new Image("Legal Solutions.jpg",
              "Image of our some fancy IT things "
              + "(it has nothing to do with our product).");

      Image image6 = new Image("ProFlex Financial Advisor Pro.jpg",
              "Image of our some fancy IT things "
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

      jenny.addRole(user);

      userRepository.save(jenny);

      Product product1 = new Product(
              "Online Scheduling Software",
              2000,
              "Introducing Proflex Solutions’ Online Scheduling Software! Our software is designed \n" +
                      "to help you effectively manage and streamline the scheduling process. Our software helps you \n" +
                      "automate the scheduling process, allowing you to save time and reduce errors. With our \n" +
                      "software, you can quickly and easily create, update and manage appointments and resources \n" +
                      "with ease. Our software also includes powerful calendar management and reporting capabilities, \n" +
                      "allowing you to track and analyze your team’s performance. Our online scheduling software is \n" +
                      "the perfect solution for businesses of any size looking to streamline their scheduling process and \n" +
                      "make their teams more productive. Try it out today and see why it's the best scheduling \n" +
                      "software on the market!",
              image1
      );

      Product product2 = new Product(
              "Proflex BPA Solutions",
              5000,
              "ProFlex BPA Solutions is the perfect solution for businesses looking to automate \n" +
                      "their processes. Our software is designed to streamline and optimize operations, saving you \n" +
                      "time and money. With our intuitive user interface, you can easily design and implement \n" +
                      "automated workflows that will help you get the most out of your business. Our software is \n" +
                      "designed to be secure, reliable, and efficient, allowing you to focus on the growth of your \n" +
                      "business without worrying about the security of your data. With ProFlex BPA Solutions, you can \n" +
                      "take control of your business processes and maximize your success.",
              image2

      );

      Product product3 = new Product(
              "ProFlex Tax Solutions",
              200,
              "ProFlex Tax Solutions is the perfect software for businesses looking to streamline \n" +
                      "their tax preparation and filing process. Our intuitive user interface makes it easy to enter your \n" +
                      "data quickly and accurately, and our automated workflow will help you get the most out of your \n" +
                      "tax filing. Our software is secure, reliable, and efficient, allowing you to focus on your business \n" +
                      "without worrying about the security of your data. With ProFlex Tax Solutions, you can take \n" +
                      "control of your tax preparation process and maximize your success.",
              image3

      );

      Product product4 = new Product(
              "ProFlex Accounting Solutions",
              500,
              "ProFlex Accounting Solutions is the perfect solution for businesses looking to \n" +
                      "streamline and optimize their accounting processes. Our software is designed to save the work \n" +
                      "of several people, automatically report to the authorities, and provide the easiest salary service \n" +
                      "in the market. Our intuitive user interface makes it easy to quickly and accurately enter your \n" +
                      "data, and our automated workflow will help you get the most out of your accounting. Our \n" +
                      "software is secure, reliable, and efficient, allowing you to focus on your business without \n" +
                      "worrying about the security of your data. With ProFlex Accounting Solutions, you can take \n" +
                      "control of your financial processes and maximize your success.",
              image4

      );

      Product product5 = new Product(
              "Legal Solutions",
              9999,
              "Introducing ProFlex Legal Solutions, the perfect solution for legal help for small \n" +
                      "businesses. ProFlex Legal Solutions provides an easy-to-use software platform that makes it easy \n" +
                      "to communicate with lawyers, send in requests to clients and follow-up on open cases. With \n" +
                      "ProFlex Legal Solutions, you can rest assured that you have the best lawyer on your side. The \n" +
                      "software is designed to be intuitive and user friendly, so you don’t have to worry about any \n" +
                      "complicated legal jargon. You can easily access a library of resources to help you understand the \n" +
                      "legal process and make sure you’re making the right decisions. You can also quickly review and \n" +
                      "respond to legal documents and receive real-time updates on any open cases. ProFlex Legal \n" +
                      "Solutions is the perfect choice for small businesses looking for a reliable and cost-effective way \n" +
                      "to manage their legal needs. With ProFlex Legal Solutions, you’ll have the best lawyer in your \n" +
                      "company at your fingertips.",
              image5

      );

      Product product6 = new Product(
              "ProFlex Financial Advisor Pro",
              6667,
              "ProFlex Financial Advisor Pro is a comprehensive financial planning software that \n" +
                      "enables businesses to plan their finances, create projections, and access the latest market data \n" +
                      "from the top 10 stock exchanges and government reports. It allows users to easily manage their \n" +
                      "finances with an intuitive user interface and comprehensive financial planning tools. With \n" +
                      "Financial Advisor Pro, you can create financial models, manage debt and investments, and \n" +
                      "assess the risks associated with various financial decisions. It also includes customizable reports \n" +
                      "and analytics to help you better understand your financial performance and plan for the future. \n" +
                      "Financial Advisor Pro is the perfect tool for businesses looking to manage their finances more \n" +
                      "effectively and make informed decisions about their investments.",
              image6

      );

      productRepository.save(product1);
      productRepository.save(product2);
      productRepository.save(product3);
      productRepository.save(product4);
      productRepository.save(product5);
      productRepository.save(product6);


      Orders order = new Orders(LocalDateTime.now(), product2, jenny);
      orderRepository.save(order);

      logger.info("DONE importing test data");
    } else {
      logger.info("Database already populated.");
    }
  }
}

