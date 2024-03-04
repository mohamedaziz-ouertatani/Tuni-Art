package tn.esprit.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMain {

  private void loadWindow(String fxmlFile) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void loadDeliveryManagement() {
    loadWindow("/tn/esprit/DeliveryManagement.fxml");
  }

  @FXML
  private void loadDeliveryAgencyManagement() {
    loadWindow("/tn/esprit/DeliveryAgencyManagement.fxml");
  }

  @FXML
  private void loadUserManagement() {
    // Implement user management window loading here
  }

  @FXML
  private void loadProductManagement() {
    // Implement product management window loading here
  }

  @FXML

  private void loadOrderManagement() {
    // Implement order management window loading here
  }

  @FXML
  private void loadEventManagement() {
    // Implement event management window loading here
  }
}
