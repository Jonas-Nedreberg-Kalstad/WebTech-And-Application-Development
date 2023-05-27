package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.dto.EmailDto;
import no.ntnu.idata2306.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @Autowired
  private EmailService emailService;

  @PostMapping("/send-email")
  public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) {
    try {
      emailService.sendEmail(emailDto);
      return ResponseEntity.ok("Email sent successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
    }
  }
}
