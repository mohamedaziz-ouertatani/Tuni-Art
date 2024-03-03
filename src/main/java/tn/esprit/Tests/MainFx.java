package tn.esprit.Tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/showEvents.fxml"));
        //javafx.scene.image.Image icon = new Image("file:/D://Documents//Tuni Art//src//images//logo.png");
        // Set the icon for the primary stage
        //stage.getIcons().add(icon);
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setTitle("submit ");
        stage.setScene(scene);
        stage.show();
    }
}