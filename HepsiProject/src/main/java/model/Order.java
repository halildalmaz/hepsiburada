package model;

import org.apache.log4j.Logger;

public class Order {
    private static final Logger logger = Logger.getLogger(Order.class);
    private String productCode;
    private int quantity;
    private double orderPrice;

    public Order() {
    }

    public Order(String productCode, int quantity, double orderPrice) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.orderPrice = orderPrice;

        logger.info("Order created; product " + this.productCode + ", quantity " + this.quantity);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
