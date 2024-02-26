package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.esprit.entities.Art_Piece;
import tn.esprit.services.Art_PieceService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateGallery {
    @FXML
    private TextField Name_id;

    @FXML
    private Button add_gallery;

    @FXML
    private TextField description_id;

    @FXML
    private ChoiceBox<?> ending_hour;

    @FXML
    private ImageView image_id;

    @FXML
    private ChoiceBox<?> location_id;

    @FXML
    private TextField phone_id;

    @FXML
    private Text price_alert;

    @FXML
    private ChoiceBox<?> starting_hour;

    @FXML
    private Text title_alert;

    @FXML
    private Button upload_image_button_id;

    @FXML
    void HoverIn(MouseEvent event) {

    }

    @FXML
    void HoverIn1(MouseEvent event) {

    }

    @FXML
    void HoverOut(MouseEvent event) {

    }

    @FXML
    void HoverOut1(MouseEvent event) {

    }

    @FXML
    void addGallery(ActionEvent event) {

    }

    @FXML
    void addImage(ActionEvent event) {

    }

    private int primaryKey;

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }
}
