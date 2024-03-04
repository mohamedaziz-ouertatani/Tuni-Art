package tn.esprit.tests;

import java.awt.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFx extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/LogIn.fxml"));
    javafx.scene.image.Image icon = new Image("file:src/images/logo.png");
    // Set the icon for the primary stage
    stage.getIcons().add(icon);
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setTitle("Sign Up");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) { launch(args); }
}
