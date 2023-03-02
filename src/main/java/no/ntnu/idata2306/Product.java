package no.ntnu.idata2306;

public class Product {

    // TODO - implement database and create id field which represent product id.
private String productName;
private double price;
private String description;
//Image stored as String which represents the filename of the image.
private String image;

    public Product(String productName, double price, String description, String image) {

        this.productName = productName;
        this.price = price;
        this.description = description;
        this.image = image;
    }



    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
