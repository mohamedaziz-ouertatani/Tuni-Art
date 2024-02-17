package tn.esprit.services;

import tn.esprit.entities.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartService implements IService<Cart> {

    private Connection connection;

    public CartService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Cart cart) throws SQLException {
        String query = "INSERT INTO cart (totalPrice, uid, state) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, cart.getTotalPrice());
            statement.setInt(2, cart.getUid());
            statement.setString(3, cart.getState());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Cart cart) throws SQLException {
        String query = "UPDATE cart SET totalPrice = ?, uid = ?, state = ? WHERE cart_ref = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, cart.getTotalPrice());
            statement.setInt(2, cart.getUid());
            statement.setString(3, cart.getState());
            statement.setInt(4, cart.getCartRef());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int cartRef) throws SQLException {
        String query = "DELETE FROM cart WHERE cart_ref = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartRef);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Cart> displayList() throws SQLException {
        List<Cart> cartList = new ArrayList<>();
        String query = "SELECT * FROM cart";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int cartRef = resultSet.getInt("cart_ref");
                float totalPrice = resultSet.getFloat("totalPrice");
                int uid = resultSet.getInt("uid");
                String state = resultSet.getString("state");
                cartList.add(new Cart(cartRef, totalPrice, uid, state));
            }
        }
        return cartList;
    }
}
