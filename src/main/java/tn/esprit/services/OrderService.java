package tn.esprit.services;

import tn.esprit.entities.Order;
import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IService<Order> {

    private Connection connection;

    public OrderService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Order order) throws SQLException {
        String query = "INSERT INTO `order` (order_date, paymentMethod, receiptionMethod, cart_ref) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, order.getOrderDate());
            statement.setString(2, order.getPaymentMethod());
            statement.setString(3, order.getReceptionMethod());
            statement.setInt(4, order.getCartRef());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Order order) throws SQLException {
        String query = "UPDATE `order` SET order_date = ?, paymentMethod = ?, receiptionMethod = ?, cart_ref = ? WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, order.getOrderDate());
            statement.setString(2, order.getPaymentMethod());
            statement.setString(3, order.getReceptionMethod());
            statement.setInt(4, order.getCartRef());
            statement.setInt(5, order.getOrderId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int orderId) throws SQLException {
        String query = "DELETE FROM `order` WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Order> displayList() throws SQLException {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM `order`";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                Date orderDate = resultSet.getDate("order_date");
                String paymentMethod = resultSet.getString("paymentMethod");
                String receptionMethod = resultSet.getString("receiptionMethod");
                int cartRef = resultSet.getInt("cart_ref");
                orderList.add(new Order(orderId, orderDate, paymentMethod, receptionMethod, cartRef));
            }
        }
        return orderList;
    }
}
