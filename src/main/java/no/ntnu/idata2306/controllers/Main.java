package no.ntnu.idata2306.controllers;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

public class Main {

  /**
   * Temporary test
   */
  public static void main(String[] args) throws Exception {
    Email from = new Email("jonasned@stud.ntnu.no");
    String subject = "Hello from SendGrid!";
    Email to = new Email("ProFlexReceipt@gmail.com");

    // Create a new Mail object with template ID
    Mail mail = new Mail();
    mail.setFrom(from);
    mail.setSubject(subject);

    mail.setTemplateId("d-2c5a560d3b0d4627bd5a56cf4a2a1913");

    Personalization personalization = new Personalization();
    personalization.addTo(to);

    personalization.addDynamicTemplateData("orderDate", "27.05.2023");
    personalization.addDynamicTemplateData("orderNumber", "12345");
    personalization.addDynamicTemplateData("productName", "Online Scheduling Software");
    personalization.addDynamicTemplateData("price", "2000");

    mail.addPersonalization(personalization);

    String apiKey = "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";

    SendGrid sg = new SendGrid(apiKey);

    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (Exception ex) {
      throw ex;
    }
  }
}



