package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.esprit.entities.Art_Piece;
import tn.esprit.services.Art_PieceService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateArt {
    private final Map<String, List<String>> typeToStylesMap = new HashMap<>();
    private final  String[] styles = {};

    @FXML
    private ImageView logoId;

    @FXML
    private TextField title_field_id;

    @FXML
    private TextField price_field_id;

    @FXML
    private TextField description_field_id;

    @FXML
    private Button update_image_button_id;

    @FXML
    private Button update_button_id;

    @FXML
    private ChoiceBox<String> type_field_id;

    @FXML
    private ChoiceBox<String> style_field_id;

    @FXML
    private Text title_alert;

    @FXML
    private Text price_alert;

    @FXML
    void HoverIn(MouseEvent event) {
        update_button_id.setStyle("-fx-background-color: #E15B10; -fx-background-radius: 55;");
    }

    @FXML
    void HoverOut(MouseEvent event) {
        update_button_id.setStyle("-fx-background-color: #E18B10; -fx-background-radius: 55;");
    }

    @FXML
    void HoverIn1(MouseEvent event) {
        update_image_button_id.setStyle("-fx-background-color: #E15B10; -fx-background-radius: 55;");
    }
    @FXML
    void HoverOut1(MouseEvent event) {
        update_image_button_id.setStyle("-fx-background-color: #E18B10; -fx-background-radius: 55;");
    }

    private int primaryKey;

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }
    @FXML
    void updateArt() {
        String title = title_field_id.getText();
        double price = Double.parseDouble(price_field_id.getText());
        String description = description_field_id.getText();
        String type = type_field_id.getValue();
        String style = style_field_id.getValue();


        // Create an instance of UpdateService
        Art_PieceService updateService = new Art_PieceService();
        Art_Piece updatedArtPiece = new Art_Piece(primaryKey,title, (float) price,5,type,Date.valueOf("2024-01-01"),description,style);


        try {
            // Call the update method of UpdateService
            updateService.update(updatedArtPiece);

            // Display a success message to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Art Piece has been updated successfully!");
            alert.showAndWait();
        } catch (SQLException e) {
            // Display an error message if update fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error updating the Art Piece: " + e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    void updateImage() {
        // Handle update image functionality
    }
    @FXML
    public void initialize() {
        Image logo = new Image("file:src\\images\\logo.png");
        logoId.setImage(logo);
        type_field_id.setItems(FXCollections.observableArrayList("Painting","Sculpture","Music","Literature","Architecture","Crocheting "));
        style_field_id.setItems(FXCollections.observableArrayList(styles));
        typeToStylesMap.put("Painting", Arrays.asList("Impressionism","Expressionism","Cubism","Surrealism","Abstract Expressionism","Pop Art"));
        typeToStylesMap.put("Sculpture", Arrays.asList("Classical","Romanesque","Gothic","Baroque","Neoclassical","Modern"));
        typeToStylesMap.put("Music", Arrays.asList("Classical","Romantic","Jazz","Rock","Hip Hop","Electronic"));
        typeToStylesMap.put("Literature", Arrays.asList("Epic","Lyric","Drama","Novel","Poetry","Short Story"));
        typeToStylesMap.put("Architecture", Arrays.asList("Classical","Gothic","Renaissance","Baroque","Modern","Postmodern"));
        typeToStylesMap.put("Crocheting", Arrays.asList("Basic Crochet","Amigurumi","Filet Crochet","Tunisian Crochet","Irish Crochet","Freeform Crochet","Broomstick Lace Crochet","Hairpin Lace Crochet"));

        type_field_id.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                String selectedType = type_field_id.getItems().get(newValue.intValue());
                updateStyles(selectedType);
            }
        });

        // Select the first type by default
        type_field_id.getSelectionModel().selectFirst();
    }

    private void updateStyles(String selectedType) {
        List<String> stylesForType = typeToStylesMap.get(selectedType);
        if (stylesForType != null) {
            style_field_id.setItems(FXCollections.observableArrayList(stylesForType));
        } else {
            style_field_id.setItems(FXCollections.observableArrayList());
        }


    }


}