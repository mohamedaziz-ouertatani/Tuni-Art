package tn.esprit.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public void loadDeliveryManagement() {
        loadFXML("/tn/esprit/delivery_management.fxml");
    }

    @FXML
    public void loadUserManagement() {
        loadFXML("/tn/esprit/user_management.fxml");
    }

    @FXML
    public void loadProductManagement() {
        loadFXML("/tn/esprit/product_management.fxml");
    }

    @FXML
    public void loadOrderManagement() {
        loadFXML("/tn/esprit/order_management.fxml");
    }

    @FXML
    public void loadEventManagement() {
        loadFXML("/tn/esprit/event_management.fxml");
    }

    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
