package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javafx.scene.image.Image;

import tn.esprit.entities.Event;
import tn.esprit.services.EventService;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class AddEvent implements Initializable {
    private static final Pattern TITLE_PATTERN = Pattern.compile("[a-zA-Z]+");
    private static final Pattern DURATION_PATTERN = Pattern.compile("\\d{1,2}");
    private final String[] Categories={"Exhibitions", "Art Fairs", "Biennials and Triennials", "Art Auctions", "Art Festivals", "Open Studios", "Public Art Installations", "Performance Art Events", "Art Workshops and Classes", "Artist Residencies"};
    @FXML
    private Button add_event;

    @FXML
    private ChoiceBox<String> category_id;

    @FXML
    private DatePicker date_id;

    @FXML
    private TextField hours;
    @FXML
    private ImageView logoId;


    @FXML
    private Text price_alert;

    @FXML
    private Text title_alert;

    @FXML
    private TextField title_id;

    @FXML
    private Button upload_image_button_id;

    @FXML
    void HoverIn(MouseEvent event) {
        add_event.setStyle("-fx-background-color: #E15B10; -fx-background-radius: 55;");

    }

    @FXML
    void HoverIn1(MouseEvent event) {
        upload_image_button_id.setStyle("-fx-background-color: #E15B10; -fx-background-radius: 55;");

    }

    @FXML
    void HoverOut(MouseEvent event) {
        add_event.setStyle("-fx-background-color: #E18B10; -fx-background-radius: 55;");

    }

    @FXML
    void HoverOut1(MouseEvent event) {
        upload_image_button_id.setStyle("-fx-background-color: #E18B10; -fx-background-radius: 55;");

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image logo = new Image("file:src\\images\\logo.png");
        logoId.setImage(logo);

    }

        private boolean validateFields() throws SQLException {
            // Clear error messages and styles for all error fields
            title_alert.setText("");
            price_alert.setText("");


            // Check for missing information
            if (title_id.getText().isEmpty() || date_id.getValue()== null || hours.getText().isEmpty())  {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Fill in all fields.");
                alert.showAndWait();
                return false;
            }

            boolean valid = true; //my flag so error messages won't appear individually

            // Validate first name
            if (!TITLE_PATTERN.matcher(title_id.getText()).matches()) {
                title_alert.setText("Title only contains letters.");
                title_alert.setStyle("-fx-background-color: #ff4d4d;");
                valid = false;

            }if (!DURATION_PATTERN.matcher(hours.getText()).matches()) {
                price_alert.setText("Duration only contains Numbers.");
                price_alert.setStyle("-fx-background-color: #ff4d4d;");
                valid = false;
            }

            return valid;
        }






        private final EventService ES = new EventService();
        @FXML
        void addEvent(ActionEvent event) {

            try {
                if(validateFields()) {


                    //String operating_hours = "from " + starting_hour.getValue() + "h" + " to " + ending_hour.getValue() + "h";
                    String category = String.valueOf(category_id.getValue());
                    int duration = Integer.parseInt(hours.getText());
                    ES.addd(new Event(duration,1,title_id.getText(),category, Date.valueOf(date_id.getValue()) ));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Event added !");
                    alert.showAndWait();
                }
            }
            catch (SQLException e){

                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText(e.getMessage());
                alert.showAndWait();

            }

        }
    }


