package tn.esprit.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReceiveOrder {

  @FXML
  private void deliverItClicked(ActionEvent event) throws IOException {
    // Load the new FXML file for the delivery details page
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/tn/esprit/DeliveryDetails.fxml"));
    Parent root = loader.load();

    // Get the controller for the delivery details page
    DeliveryDetails controller = loader.getController();

    // Show the delivery details page
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle("Delivery Details");
    stage.show();
  }
}
