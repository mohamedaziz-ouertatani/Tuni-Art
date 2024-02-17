package tn.esprit.entities;

public class Cart {
    private int cartRef;
    private float totalPrice;
    private int uid;
    private String state;

    public Cart(int cartRef, float totalPrice, int uid, String state) {
        this.cartRef = cartRef;
        this.totalPrice = totalPrice;
        this.uid = uid;
        this.state = state;
    }

    // Getters and setters
    public int getCartRef() {
        return cartRef;
    }

    public void setCartRef(int cartRef) {
        this.cartRef = cartRef;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
