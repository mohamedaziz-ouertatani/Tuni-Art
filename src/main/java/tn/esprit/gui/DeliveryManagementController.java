package tn.esprit.gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tn.esprit.entities.Delivery;
import tn.esprit.services.DeliveryService;
import tn.esprit.utils.MyDatabase;

public class DeliveryManagementController {

  @FXML private TableView<Delivery> deliveryTable;

  @FXML private TextField orderIdField;

  @FXML private TextField estimatedDateField;

  @FXML private TextField deliveryFeesField;

  @FXML private TextField destinationField;

  @FXML private TextField stateField;

  @FXML private TextField agencyIdField;

  private DeliveryService deliveryService;

  public DeliveryManagementController() {
    Connection connection = MyDatabase.getInstance().getCon();
    deliveryService = new DeliveryService(connection);
  }

  @FXML
  private void initialize() throws SQLException {
    deliveryTable.getItems().addAll(deliveryService.displayList());
  }

  @FXML
  private void addDelivery() throws SQLException {
    int orderId = Integer.parseInt(orderIdField.getText());
    LocalDate estimatedDate = LocalDate.parse(estimatedDateField.getText());
    float deliveryFees = Float.parseFloat(deliveryFeesField.getText());
    String destination = destinationField.getText();
    boolean state = Boolean.parseBoolean(stateField.getText());
    int agencyId = Integer.parseInt(agencyIdField.getText());

    Delivery delivery = new Delivery(0, orderId, estimatedDate, deliveryFees,
                                     destination, state, agencyId);
    deliveryService.add(delivery);
    deliveryTable.getItems().add(delivery);
  }

  @FXML
  private void updateDelivery() throws SQLException {
    Delivery selectedDelivery =
        deliveryTable.getSelectionModel().getSelectedItem();
    if (selectedDelivery != null) {
      int orderId = Integer.parseInt(orderIdField.getText());
      LocalDate estimatedDate = LocalDate.parse(estimatedDateField.getText());
      float deliveryFees = Float.parseFloat(deliveryFeesField.getText());
      String destination = destinationField.getText();
      boolean state = Boolean.parseBoolean(stateField.getText());
      int agencyId = Integer.parseInt(agencyIdField.getText());

      selectedDelivery.setOrderId(orderId);
      selectedDelivery.setEstimatedDate(estimatedDate);
      selectedDelivery.setDeliveryFees(deliveryFees);
      selectedDelivery.setDestination(destination);
      selectedDelivery.setState(state);
      selectedDelivery.setAgencyId(agencyId);

      deliveryService.update(selectedDelivery);

      deliveryTable.refresh(); // Refresh the TableView to reflect the changes
    }
  }

  @FXML
  private void deleteDelivery() throws SQLException {
    Delivery selectedDelivery =
        deliveryTable.getSelectionModel().getSelectedItem();
    if (selectedDelivery != null) {
      deliveryService.delete(selectedDelivery.getDeliveryId());
      deliveryTable.getItems().remove(selectedDelivery);
    }
  }
}
