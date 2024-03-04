package tn.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.esprit.Controllers.ClientMain;

public class MainFx extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ClientMain.fxml"));
        javafx.scene.image.Image icon = new Image("file:src/main/java/tn/esprit/images/logo.png");
        // Set the icon for the primary stage
        stage.getIcons().add(icon);
        Parent root=loader.load();

        ///////////////
        // Access the controller to set up the OnCloseRequest event
        ClientMain controller = loader.getController();
        controller.initializeOnCloseRequest(stage); // Add this line to initialize the OnCloseRequest event

        //////////////
        Scene scene=new Scene(root);
        stage.setTitle("Order Art");
        stage.setScene(scene);
        stage.show();
    }

}
