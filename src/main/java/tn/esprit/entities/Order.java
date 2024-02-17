package tn.esprit.entities;

import java.sql.Date;

public class Order {
    private int orderId;
    private Date orderDate;
    private String paymentMethod;
    private String receptionMethod;
    private int cartRef;

    // Constructors, getters, and setters

    public Order(int orderId, Date orderDate, String paymentMethod, String receptionMethod, int cartRef) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.receptionMethod = receptionMethod;
        this.cartRef = cartRef;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceptionMethod() {
        return receptionMethod;
    }

    public void setReceptionMethod(String receptionMethod) {
        this.receptionMethod = receptionMethod;
    }

    public int getCartRef() {
        return cartRef;
    }

    public void setCartRef(int cartRef) {
        this.cartRef = cartRef;
    }
}
