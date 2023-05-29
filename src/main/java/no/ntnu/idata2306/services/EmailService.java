package no.ntnu.idata2306.services;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import no.ntnu.idata2306.model.Orders;
import no.ntnu.idata2306.model.Product;
import no.ntnu.idata2306.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service class responsible for sending emails.
 */
@Service
public class EmailService {

  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);


  /**
   * Sends an email to the specified user with the receipt for the given order.
   *
   * @param user    The User representing the recipient of the email.
   * @param product The Product representing the purchased product.
   * @param order   The Orders representing the order details.
   * @throws IOException if there is an error in sending the email.
   */
  @Operation(summary = "Send email", description = "Sends an email to the specified user with the receipt for the given order.")
  @ApiResponse(responseCode = "200", description = "Email sent successfully")
  @ApiResponse(responseCode = "500", description = "Internal server error")
  public void sendEmail(User user, Product product, Orders order) throws IOException {
    Email from = new Email("omarmmo@stud.ntnu.no");
    Email to = new Email(user.getEmail());

    Mail mail = new Mail();
    mail.setFrom(from);

    mail.setTemplateId(System.getenv("SENDGRID_TEMPLATE_ID"));

    Personalization personalization = new Personalization();
    personalization.addTo(to);

    // Formatting time
    LocalDateTime orderDate = order.getDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    String formattedOrderDate = orderDate.format(formatter);

    // Setting the dynamic fields
    personalization.addDynamicTemplateData("orderDate", formattedOrderDate);
    personalization.addDynamicTemplateData("orderNumber", order.getId());
    personalization.addDynamicTemplateData("productName", product.getProductName());
    personalization.addDynamicTemplateData("price", product.getPrice());

    mail.addPersonalization(personalization);

    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      Response response = sg.api(request);
      logger.info("Email sent successfully. Status Code: {}", response.getStatusCode());
      logger.info("Response Body: {}", response.getBody());
      logger.info("Response Headers: {}", response.getHeaders());
    } catch (IOException e) {
      logger.error("Error sending email", e);
      throw e;
    }
  }
}
