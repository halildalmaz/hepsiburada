package model;

import org.apache.log4j.Logger;

public class Product {
    private static final Logger logger = Logger.getLogger(Product.class);
    private String productCode;
    private double price;
    private double firstPrice;
    private int stock;

    public Product() {
    }

    public Product(String productCode, int firstPrice, int stock) {
        this.productCode = productCode;
        this.price = firstPrice;
        this.stock = stock;
        this.firstPrice = firstPrice;

        logger.info("Product created; code " + this.productCode + ", price " +
                this.price + ", stock " + this.stock);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFirstPrice() {
        return firstPrice;
    }
    public void setFirstPrice(double firstPrice) {
        this.firstPrice = firstPrice;
    }
}
