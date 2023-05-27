package no.ntnu.idata2306.dto;

public class EmailDto {
  private String from;
  private String to;
  private String subject;
  private String content;
  private String orderDate;
  private String userEmail;
  private String orderNumber;
  private String productName;
  private double price;

  public EmailDto() {
  }

  public EmailDto(String from, String to, String subject, String content, String orderDate, String userEmail, String orderNumber, String productName, double price) {
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.content = content;
    this.orderDate = orderDate;
    this.userEmail = userEmail;
    this.orderNumber = orderNumber;
    this.productName = productName;
    this.price = price;
  }

  // Getters and Setters for the new fields

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
