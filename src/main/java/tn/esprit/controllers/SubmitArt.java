package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.entities.Art_Piece;
import tn.esprit.services.Art_PieceService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

import static javax.swing.plaf.synth.SynthLookAndFeel.updateStyles;

public class SubmitArt implements Initializable {

    private static final Pattern TITLE_PATTERN = Pattern.compile("[a-zA-Z]+");
    private static final Pattern PRICE_PATTERN = Pattern.compile("\\d{1,7}");
    private final Art_PieceService AS = new Art_PieceService();

    @FXML
    private TextField description_field_id;

    @FXML
    private Text price_alert;

    @FXML
    private Text title_alert;

    @FXML
    private TextField price_field_id;

    @FXML
    private ImageView logoId;


    @FXML
    private Button publish_button_id;

    @FXML
    private ChoiceBox<String> style_field_id;

    @FXML
    private TextField title_field_id;

    @FXML
    private ChoiceBox<String> type_field_id;

    @FXML
    private Button upload_image_button_id;

    private final  String[] types = {"Painting","Sculpture","Music","Literature","Architecture"," Crocheting "};
    private final  String[] styles = {};
    String art_image;

    private final Map<String, List<String>> typeToStylesMap = new HashMap<>();

    @FXML
    void HoverIn(MouseEvent event) {
        publish_button_id.setStyle("-fx-background-color: #E15B10; -fx-background-radius: 55;");
    }

    @FXML
    void HoverOut(MouseEvent event) {
        publish_button_id.setStyle("-fx-background-color: #E18B10; -fx-background-radius: 55;");
    }
    @FXML
    void HoverIn1(MouseEvent event) {
        upload_image_button_id.setStyle("-fx-background-color: #E15B10; -fx-background-radius: 55;");
    }
    @FXML
    void HoverOut1(MouseEvent event) {
        upload_image_button_id.setStyle("-fx-background-color: #E18B10; -fx-background-radius: 55;");
    }

    private boolean validateFields() throws SQLException {
        // Clear error messages and styles for all error fields
        title_alert.setText("");
        price_alert.setText("");


        // Check for missing information
        if (title_field_id.getText().isEmpty() || price_field_id.getText().isEmpty() || description_field_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Fill in all fields.");
            alert.showAndWait();
            return false;
        }

        boolean valid = true; //my flag so error messages won't appear individually

        // Validate first name
        if (!TITLE_PATTERN.matcher(title_field_id.getText()).matches()) {
            title_alert.setText("Title only contains letters.");
            title_alert.setStyle("-fx-background-color: #ff4d4d;");
            valid = false;
        }

        // Validate last name
        if (!PRICE_PATTERN.matcher(price_field_id.getText()).matches()) {
            price_alert.setText("Price only contains numbers.");
            price_alert.setStyle("-fx-background-color: #ff4d4d;");
            valid = false;
        }

        return valid;
    }

    @FXML
    void addArt(ActionEvent event) {

        try {
            if(validateFields()){
            String type = "Painting";
            String style = "Pop Art";
            type=type_field_id.getValue();
            style=style_field_id.getValue();
            String Price = price_field_id.getText();
            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
            AS.addd(new Art_Piece(title_field_id.getText(), Float.parseFloat(Price),5,type,sqlDate,description_field_id.getText(), style,art_image));
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Art added !");
            alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/gallery.fxml"));
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

                System.out.println("moved");}
        }
        catch (SQLException e){

            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addImage(ActionEvent event) {
        // Create a new file chooser
        FileChooser fileChooser = new FileChooser();

        // Set the title for the file chooser dialog
        fileChooser.setTitle("Select Art Picture");

        // Set the initial directory to the user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Add filters to restrict file selection to image files only
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show the file chooser dialog and wait for user selection
        File selectedFile = fileChooser.showOpenDialog(null);

        // Check if a file was selected
        if (selectedFile != null) {
            try {
                // Create a target directory if it doesn't exist
                File targetDir = new File("src/images");
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }

                // Define the target file path
                String fileName = selectedFile.getName();
                System.out.println(fileName);
                 art_image = fileName;
                Path targetPath = new File(targetDir, fileName).toPath();

                // Copy the selected file to the target directory
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Art picture added successfully!");
                alert.showAndWait();
            } catch (IOException e) {
                // Show error message if an exception occurs during file copying
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error occurred while adding art picture!");
                alert.showAndWait();
            }
        }

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
