package no.ntnu.idata2306.controllers;

import jakarta.servlet.http.HttpServletResponse;
import no.ntnu.idata2306.dto.SignUpDto;
import no.ntnu.idata2306.model.Orders;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.model.User;
import no.ntnu.idata2306.services.EmailService;
import no.ntnu.idata2306.services.OrderService;
import no.ntnu.idata2306.services.ProductService;
import no.ntnu.idata2306.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controller class responsible for handling page-related requests.
 */
@Controller
public class PageController {

  private final ProductService productService;
  private final UserService userService;

  private final OrderService orderService;

  private final EmailService emailService;

  /**
   * Creates a new instance of the PageController class.
   *
   * @param productService The ProductService instance to be used.
   * @param userService    The UserService instance to be used.
   */
  public PageController(ProductService productService, UserService userService, OrderService orderService, EmailService emailService) {
    this.productService = productService;
    this.userService = userService;
    this.orderService = orderService;
    this.emailService = emailService;
  }

  /**
   * Retrieves the signup page.
   *
   * @return The signup template name.
   */
  @GetMapping("/signup")
  public String getSignUp() {
    return "signup";
  }

  /**
   * This method processes data received from the signup form (HTTP POST) and handles the signup process.
   *
   * @param signupData          The SignUpDto object containing the form data.
   * @param model               The model to be used for rendering the view.
   * @param httpServletResponse The HttpServletResponse object for redirecting to the login page.
   * @return The ResponseEntity containing the response status and body.
   */
  @PostMapping("/signup")
  public ResponseEntity<String> signupProcess(@ModelAttribute SignUpDto signupData, Model model, HttpServletResponse httpServletResponse) {
    model.addAttribute("signupData", signupData);
    ResponseEntity<String> response;
    try {
      userService.createUserForSignUp(signupData);
      httpServletResponse.sendRedirect("/login");
      response = new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error redirecting to login page");

    }
    return response;
  }

  /**
   * Retrieves the login page.
   *
   * @return The login page template name.
   */
  @GetMapping("/login")
  public String getLogin() {
    return "login";
  }

  /**
   * Retrieves the product page for the given product ID or page-not-found.
   *
   * @param id    The ID of the product.
   * @param model The model to be used for rendering the view.
   * @return The product page template name or page-not-found based on if product exists or not.
   */
  @GetMapping("/products/{id}")
  public String getProduct(@PathVariable int id, Model model) {
    Optional<Product> optionalProduct = productService.getProduct(id);
    if (optionalProduct.isEmpty()) {
      return "page-not-found";
    }

    Product product = optionalProduct.get();
    model.addAttribute("product", product);
    model.addAttribute("user", userService.getSessionUser());
    return "product";
  }

  /**
   * Processes the purchase form data (HTTP POST) and handles the purchase process.
   * Sends an email containing purchase receipt to the user after purchase.
   *
   * @param id The ID of the product being purchased.
   * @param httpServletResponse The HttpServletResponse object for redirecting to the home page.
   * @return The ResponseEntity containing the response status and body.
   */
  @PostMapping("/products/{id}")
  public ResponseEntity<String> purchaseProcess(@PathVariable int id, HttpServletResponse httpServletResponse) {
    ResponseEntity<String> response;
    try {
      User loggedInUser = userService.getSessionUser();
      Optional<Product> optionalProduct = productService.getProduct(id);

      if (loggedInUser == null || optionalProduct.isEmpty()) {
        response = new ResponseEntity<>("User or product not found", HttpStatus.NOT_FOUND);
      } else {
        Product product = optionalProduct.get();
        Orders order = new Orders(LocalDateTime.now(), product, loggedInUser);
        orderService.createOrder(order);
        emailService.sendEmail(loggedInUser, product, order);
        httpServletResponse.sendRedirect("/");
        response = new ResponseEntity<>(HttpStatus.OK);
      }
    } catch (IOException e) {
      response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
    }
    return response;
  }

  /**
   * Retrieves the product page containing all products.
   *
   * @param model The model to be used for rendering the view.
   * @return The product site template name.
   */
  @GetMapping("/products")
  public String getProductPage(Model model) {
    model.addAttribute("products", productService.getAll());
    model.addAttribute("user", userService.getSessionUser());
    return "productsite";
  }

  /**
   * Retrieves the about us page.
   *
   * @param model The model to be used for rendering the view.
   * @return The about us template name.
   */
  @GetMapping("/about")
  public String getAboutUsPage(Model model) {
    model.addAttribute("user", userService.getSessionUser());
    return "aboutus";
  }
  /**
   * Retrieves the view-profile page.
   *
   * @param model The model to be used for rendering the view.
   * @return The view-profile template name.
   */
  @GetMapping("/view-profile")
  public String getViewProfilePage(Model model) {
    model.addAttribute("user", userService.getSessionUser());
    return "view-profile";
  }

  /**
   * Retrieves the contact us page.
   *
   * @param model The model to be used for rendering the view.
   * @return The contact us template name.
   */
  @GetMapping("/contact")
  public String getContactPage(Model model) {
    model.addAttribute("user", userService.getSessionUser());
    return "contactus";
  }

  /**
   * Retrieves the access-denied page.
   *
   * @return The access-denied template name.
   */
  @GetMapping("/access-denied")
  public String accessDenied() {
    return "access-denied";
  }

  /**
   * Retrieves the home page.
   *
   * @param model The model to be used for rendering the view.
   * @return The index template name.
   */
  @GetMapping("/")
  public String getHomePage(Model model) {
    model.addAttribute("products", productService.getFirstNProductsFromDatabase(3));
    model.addAttribute("user", userService.getSessionUser());
    return "index";
  }

}
