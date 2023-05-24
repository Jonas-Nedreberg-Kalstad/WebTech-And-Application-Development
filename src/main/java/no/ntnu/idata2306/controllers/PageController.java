package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.dto.SignUpDto;
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

/**
 *
 */
@Controller
public class PageController {

  ProductService productService;

  UserService userService;

  /**
   *
   * @param productService
   * @param userService
   */
  public PageController(ProductService productService, UserService userService){
    this.productService = productService;
    this.userService = userService;

  }

  @GetMapping("/signup")
  public String getSignUp(Model model){
    model.addAttribute("signupData", new SignUpDto());
    return "signup";
  }

  /**
   * This method processes data received from the sign-up form (HTTP POST)
   *
   * @return Name of the template for the result page
   */
  @PostMapping("/signup")
  public ResponseEntity<String> signupProcess(@ModelAttribute SignUpDto signupData, Model model) {
    model.addAttribute("signupData", signupData);
    ResponseEntity<String> response;
    try {
      userService.createUser(signupData);
      response = new ResponseEntity<>("Signup successful", HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  @GetMapping("/login")
  public String getLogin(){
    return "login";
  }

  @GetMapping("/products/{id}")
  public String getProduct(@PathVariable int id, Model model){
    model.addAttribute("product", productService.getProduct(id));
    return "product";
  }

  @GetMapping("/products")
  public String getProductPage(Model model){
    model.addAttribute("products", productService.getAll());
    return "productsite";
  }

  @GetMapping("/aboutUs")
  public String getAboutUsPage(){
    return "aboutUs";
  }

  @GetMapping("/")
  public String getHomePage(Model model){
    model.addAttribute("products", productService.getFirstNProductsFromDatabase(3));
    return "index";
  }

}
