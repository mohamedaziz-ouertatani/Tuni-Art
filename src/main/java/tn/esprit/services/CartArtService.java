package tn.esprit.services;

import tn.esprit.entities.CartArt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartArtService implements IService<CartArt> {

    private Connection connection;

    public CartArtService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(CartArt cartArt) throws SQLException {
        String query = "INSERT INTO cart_art (cart_ref, art_ref, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartArt.getCartRef());
            statement.setInt(2, cartArt.getArtRef());
            statement.setInt(3, cartArt.getQuantity());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(CartArt cartArt) throws SQLException {
        String query = "UPDATE cart_art SET quantity = ? WHERE cart_ref = ? AND art_ref = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartArt.getQuantity());
            statement.setInt(2, cartArt.getCartRef());
            statement.setInt(3, cartArt.getArtRef());
            statement.executeUpdate();
        }
    }

    // CartArtService.java
    @Override
    public void delete(int cartRef) throws SQLException {
        // Corrected implementation for the delete method
        String query = "DELETE FROM cart_art WHERE cart_ref = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartRef);
            statement.executeUpdate();
        }
    }


    @Override
    public List<CartArt> displayList() throws SQLException {
        List<CartArt> cartArtList = new ArrayList<>();
        String query = "SELECT * FROM cart_art";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int cartRef = resultSet.getInt("cart_ref");
                int artRef = resultSet.getInt("art_ref");
                int quantity = resultSet.getInt("quantity");
                cartArtList.add(new CartArt(cartRef, artRef, quantity));
            }
        }
        return cartArtList;
    }

}
