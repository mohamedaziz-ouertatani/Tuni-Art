package tn.esprit.entities;

public class CartArt {
    private int cartRef;
    private int artRef;
    private int quantity;

    public CartArt(int cartRef, int artRef, int quantity) {
        this.cartRef = cartRef;
        this.artRef = artRef;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getCartRef() {
        return cartRef;
    }

    public void setCartRef(int cartRef) {
        this.cartRef = cartRef;
    }

    public int getArtRef() {
        return artRef;
    }

    public void setArtRef(int artRef) {
        this.artRef = artRef;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
