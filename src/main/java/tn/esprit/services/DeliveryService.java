package tn.esprit.services;

import tn.esprit.entities.Delivery;
import tn.esprit.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryService implements IService<Delivery> {

    private final Connection connection;

    public DeliveryService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Delivery delivery) throws SQLException {
        String query = "INSERT INTO delivery (delivery_id, order_id, estimated_date, delivery_fees, destination, state, agency_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, delivery.getDeliveryId());
            statement.setInt(2, delivery.getOrderId());
            statement.setDate(3, Date.valueOf(delivery.getEstimatedDate()));
            statement.setFloat(4, delivery.getDeliveryFees());
            statement.setString(5, delivery.getDestination());
            statement.setBoolean(6, delivery.isState());
            statement.setInt(7, delivery.getAgencyId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Delivery delivery) throws SQLException {
        String query = "UPDATE delivery SET order_id = ?, estimated_date = ?, delivery_fees = ?, destination = ?, state = ?, agency_id = ? WHERE delivery_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, delivery.getOrderId());
            statement.setDate(2, Date.valueOf(delivery.getEstimatedDate()));
            statement.setFloat(3, delivery.getDeliveryFees());
            statement.setString(4, delivery.getDestination());
            statement.setBoolean(5, delivery.isState());
            statement.setInt(6, delivery.getAgencyId());
            statement.setInt(7, delivery.getDeliveryId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM delivery WHERE delivery_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Delivery> displayList() throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();
        String query = "SELECT * FROM delivery";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Delivery delivery = new Delivery();
                delivery.setDeliveryId(resultSet.getInt("delivery_id"));
                delivery.setOrderId(resultSet.getInt("order_id"));
                delivery.setEstimatedDate(resultSet.getDate("estimated_date").toLocalDate());
                delivery.setDeliveryFees(resultSet.getFloat("delivery_fees"));
                delivery.setDestination(resultSet.getString("destination"));
                delivery.setState(resultSet.getBoolean("state"));
                delivery.setAgencyId(resultSet.getInt("agency_id"));
                deliveries.add(delivery);
            }
        }
        return deliveries;
    }
}
