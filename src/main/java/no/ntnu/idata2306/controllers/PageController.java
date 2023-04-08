package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.dto.SignUpDto;
import no.ntnu.idata2306.services.ProductService;
import no.ntnu.idata2306.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

  @GetMapping("/api/signup")
  public String getSignUp(Model model){
    model.addAttribute("signupData", new SignUpDto());
    return "signup";
  }

  @GetMapping("/api/login")
  public String getLogin(){
    return "login";
  }

}
