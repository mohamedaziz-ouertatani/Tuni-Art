package tn.esprit;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root =
        FXMLLoader.load(getClass().getResource("/tn/esprit/main.fxml"));
    primaryStage.setTitle("Delivery Management System");
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.show();
  }

  public static void main(String[] args) { launch(args); }
}
