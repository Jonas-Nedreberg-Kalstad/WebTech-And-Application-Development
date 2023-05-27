package no.ntnu.idata2306.services;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import no.ntnu.idata2306.dto.EmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailService {

  @Value("${sendgrid.api.key}")
  private String sendGridApiKey;

  @Value("${sendgrid.template.id}")
  private String templateId;

  public void sendEmail(EmailDto emailDto) throws IOException {
    Email from = new Email(emailDto.getFrom());
    String subject = emailDto.getSubject();
    Email to = new Email(emailDto.getTo());

    Personalization personalization = new Personalization();
    personalization.addTo(to);

    personalization.addDynamicTemplateData("orderDate", emailDto.getOrderDate());
    personalization.addDynamicTemplateData("userEmail", emailDto.getUserEmail());
    personalization.addDynamicTemplateData("orderNumber", emailDto.getOrderNumber());
    personalization.addDynamicTemplateData("productName", emailDto.getProductName());
    personalization.addDynamicTemplateData("price", String.valueOf(emailDto.getPrice()));

    Mail mail = new Mail();
    mail.setFrom(from);
    mail.setSubject(subject);
    mail.setTemplateId(templateId);
    mail.addPersonalization(personalization);

    SendGrid sg = new SendGrid(sendGridApiKey);
    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
  }
}
