package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.esprit.entities.Art_Piece;
import tn.esprit.services.Art_PieceService;


import javax.imageio.IIOParam;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Gallery implements Initializable {

    Art_PieceService as = new Art_PieceService();

    @FXML
    private MenuBar Menu_id;

    @FXML
    private ImageView profilePictureId;

    @FXML
    private ImageView logoId;

    @FXML
    private FlowPane artsContainer;

    @FXML
    private ImageView printId;


    @FXML
    private Text uidTextId;
    @FXML
    private Button upload_art;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image logo = new Image("file:src\\images\\logo.png");
        logoId.setImage(logo);
        Image print = new Image("file:src\\images\\tun.png");
        printId.setImage(print);

        artsContainer.setPadding(new Insets(30)); // Adjust padding as needed
        artsContainer.setHgap(80); // Set horizontal gap between elements
        artsContainer.setVgap(20); // Set vertical gap between lines
//style="-fx-background-color: #3B2A19; -fx-background-radius: 55;
        displayArts();
        }


    private void displayArts() {
        try {
            List<Art_Piece> arts = as.diplayList();
            System.out.println("in displayarts");
            // Call the method to create art boxes and add them to the container
            List<VBox> artBoxes = createArtBox(arts);
            artsContainer.getChildren().addAll(artBoxes);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private List<VBox> createArtBox(List<Art_Piece> arts) {
        List<VBox> artBoxes = new ArrayList<>();
        for (Art_Piece art : arts) {
            System.out.println(art);
            VBox artBox = new VBox();
            artBox.getStyleClass().add("artBox"); // Add style class to VBox
            //artBox.setStyle("-fx-padding: 30px; -fx-spacing: 20px; -fx-border-radius: 10px; -fx-background-color: #f7f8fa; -fx-border-color: #5dade2; ");
            artBox.setStyle("-fx-padding: 20px; -fx-spacing: 10px; -fx-background-color: #f7f8fa; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

            // Set a fixed width for the VBox
            artBox.setPrefWidth(185); // Adjust width here (e.g., 200 pixels)

            // Add margin to the VBox
            artBox.setMargin(artBox, new Insets(10));

            // Add art name label
            Button updateButton = new Button("Update");
            updateButton.setUserData(art.getArt_ref());
            Button deleteButton = new Button("Delete");
            HBox buttonsBox = new HBox(10); // 10 is the spacing between buttons (adjust as needed)
            buttonsBox.getChildren().addAll( deleteButton, updateButton);
            buttonsBox.setPadding(new Insets(10, 50, 20, 10));

            Label title_label = new Label(art.getArt_title().toUpperCase());
            Label price_label = new Label(String.valueOf(art.getArt_price())+"DT");
            Label category_label = new Label(art.getType().toUpperCase());
            title_label.setFont(Font.font("Gill Sans MT", FontWeight.NORMAL, FontPosture.REGULAR, 16));
            title_label.setPadding(new Insets(15));
            title_label.setTextFill(Color.WHITE);
            category_label.setTextFill(Color.WHITE);
            //style="-fx-background-color: E18B10; -fx-background-radius: 55" text="Upload Your Image" textFill="WHITE"
            updateButton.setStyle("-fx-background-color: E18B10; -fx-background-radius: 55; -fx-text-fill: white;");
            deleteButton.setStyle("-fx-background-color: E18B10; -fx-background-radius: 55; -fx-text-fill: white;");
            price_label.setTextFill(Color.WHITE);// Set text color to white
            artBox.setAlignment(Pos.CENTER);
            artBox.getChildren().addAll(title_label, price_label,category_label);

            updateButton.setOnAction(event -> {
                // Handle upload button action
                try {
                    int primaryKey = (int) updateButton.getUserData();

                    Parent root = FXMLLoader.load(getClass().getResource("/Update_Art.fxml"));
                    javafx.scene.image.Image icon = new Image("file:/src/images/logo.png");

                    // Pass primary key to update page controller

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Update_Art.fxml"));
                    Parent root1 = loader.load();
                    UpdateArt controller = loader.getController();
                    if (controller != null) {
                        controller.setPrimaryKey(primaryKey);
                    } else {
                        // Handle null controller
                        System.err.println("Controller is null");
                    }


                    // Create a new stage for the new window
                    Stage newStage = new Stage();
                    newStage.getIcons().add(icon);

                    // Set the scene with the new root
                    Scene scene = new Scene(root);
                    newStage.setScene(scene);
                    newStage.setTitle("Update Art");

                    // Close the old stage
                    Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    oldStage.close();

                    // Show the new stage
                    newStage.show();

                    System.out.println("moved");
            } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                });

            deleteButton.setOnAction(event -> {
                // Handle delete button action

                try {
                    as.delete(art.getArt_ref()); // Call the delete method with the art_ref
                    System.out.println("Art_Piece deleted");

                    // Display a confirmation dialog
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Art Piece has been deleted successfully!");
                    alert.showAndWait();

                    // Remove the VBox containing the art piece from the UI
                    artBoxes.remove(artBox);
                    artsContainer.getChildren().remove(artBox);
                } catch (SQLException e) {
                    // Display an error dialog if deletion fails
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error deleting the Art Piece: " + e.getMessage());
                    alert.showAndWait();
                }
            });
            ImageView artImage;
            if(art.getImage_path()==null) {
                System.out.println("purr");
                artImage = new ImageView(new Image("file:src\\images\\default.png"));
            }
            else {
                artImage = new ImageView(new Image("file:src\\images\\" + art.getImage_path()));
            }
            artImage.setFitWidth(100);
            artImage.setFitHeight(100);
            artBox.getChildren().add(artImage);

            // Attach event handler to the profile picture
            artImage.setOnMouseClicked(event -> {
                // Redirect to the profile page for the selected art
                redirectToProfilePage(art);
            });

            // Add margin to the bottom of the VBox
            artBox.setMargin(artImage, new Insets(10));
            
            /*// Attach event handler to the profile picture
            artImage.setOnMouseClicked(event -> {
                // Redirect to the profile page for the selected art
                redirectToProfilePage(Art_Piece);
            });*/

            // Add margin to the bottom of the VBox
            artBox.setMargin(artBox, new Insets(8));
            artBox.setStyle("-fx-background-color: #3B2A19; -fx-background-radius: 55;");
            artBox.setAlignment(Pos.CENTER);

            artBoxes.add(artBox);
            artBox.getChildren().addAll( buttonsBox);
        }
        return artBoxes;
    }

    private void redirectToProfilePage(Art_Piece art) {
        // Load the profile page FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        Parent root;
        try {
            root = loader.load();
            //ProfilePageController controller = loader.getController();

            // Pass the selected art's information to the profile page controller
            //controller.initData(art);

            // Display the profile page
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void upload_art(javafx.event.ActionEvent event) throws IOException {

        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/submit_art.fxml"));
        javafx.scene.image.Image icon = new Image("file:/src/images/logo.png");

        // Create a new stage for the new window
        Stage newStage = new Stage();
        newStage.getIcons().add(icon);

        // Set the scene with the new root
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setTitle("Submit Art");

        // Close the old stage
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        // Show the new stage
        newStage.show();

        System.out.println("moved");

    }
    @FXML
    public void openGalleriesPage(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View_Galleries.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        javafx.scene.image.Image icon = new Image("file:/src/images/logo.png");

        // Create a new stage for the new window
        Stage newStage = new Stage();
        newStage.getIcons().add(icon);

        // Set the scene with the new root
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setTitle("Submit Art");

        // Close the old stage
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        // Show the new stage
        newStage.show();

        System.out.println("moved");

    }
}
