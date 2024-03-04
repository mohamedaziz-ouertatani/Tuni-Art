package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;
import tn.esprit.utils.SessionManager;

public class Verification implements Initializable {

  @FXML private ImageView LogoId;

  @FXML private Button continueButton;

  @FXML private Text emailAlertId;

  @FXML private TextField verficationCodeId;

  @FXML private Text VerificationAlertId;

  @FXML private ImageView goBackButtonId;

  @FXML private Text resetPwdTextId;

  User currentUser;
  UserService us = new UserService();

  @FXML
  void HoverIn(MouseEvent event) {
    continueButton.setStyle(
        "-fx-background-color: #E15B10; -fx-background-radius: 55;");
  }

  @FXML
  void HoverOut(MouseEvent event) {
    continueButton.setStyle(
        "-fx-background-color: #E18B10; -fx-background-radius: 55;");
  }

  @FXML
  void redirectToLogIn(MouseEvent event) throws IOException {
    // Get the current stage from any node in the scene graph
    Stage oldStage = (Stage)((Node)event.getSource()).getScene().getWindow();

    // Load the new FXML file
    Parent root = FXMLLoader.load(getClass().getResource("/LogIn.fxml"));
    javafx.scene.image.Image icon = new Image(
        "file:/C:/Users/DELL/Documents/3A/Semester 2/PIDEV/Tuni Art/src/images/logo.png");

    // Create a new stage for the new window
    Stage newStage = new Stage();
    newStage.getIcons().add(icon);

    // Set the scene with the new root
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.setTitle("Log In");

    // Close the old stage
    oldStage.close();

    // Show the new stage
    newStage.show();

    System.out.println("moved");
  }

  @FXML
  void redirectToPwd(ActionEvent event) throws IOException {
    if (verficationCodeId.getText().isEmpty()) {
      // Show error message for database exception
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("Enter Verification Code.");
      alert.showAndWait();
    } else {
      if (currentUser.getVerification_code().equals(
              verficationCodeId.getText())) {

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(
            "Correct verification code. You can proceed with password reset.");
        alert.showAndWait();

        // Get the current stage from any node in the scene graph
        Stage oldStage =
            (Stage)((Node)event.getSource()).getScene().getWindow();

        // Load the new FXML file
        Parent root =
            FXMLLoader.load(getClass().getResource("/UpdatePassword.fxml"));
        javafx.scene.image.Image icon = new Image(
            "file:/C:/Users/DELL/Documents/3A/Semester 2/PIDEV/Tuni Art/src/images/logo.png");

        // Create a new stage for the new window
        Stage newStage = new Stage();
        newStage.getIcons().add(icon);

        // Set the scene with the new root
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setTitle("Log In");

        // Close the old stage
        oldStage.close();

        // Show the new stage
        newStage.show();

        System.out.println("moved");
      } else {
        VerificationAlertId.setText("The verification code is incorrect");
        VerificationAlertId.setStyle("-fx-background-color: #ff4d4d;");
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Retrieve the UID from the session
    int uid = SessionManager.getInstance().getCurrentUserUid();
    try {
      currentUser = us.searchByUid(uid);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    Image logo = new Image("file:src\\images\\logo.png");
    LogoId.setImage(logo);
    Image goBack = new Image("file:src\\images\\arrow-left.png");
    goBackButtonId.setImage(goBack);
  }
}
