package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import tn.esprit.Utils.MyDatabase;
import tn.esprit.entities.Event;
import tn.esprit.services.EventService;
import javafx.event.ActionEvent;
import tn.esprit.services.sendsmsService;

import java.io.BufferedReader;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.scene.control.Button;

import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ShowEvents implements Initializable {

    EventService ES = new EventService();
    @FXML
    private FlowPane eventContainer;

    @FXML
    private ImageView logoId;

    @FXML
    private Text uidTextId;
    @FXML
    private GridPane calendarGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image logo = new Image("file:src\\images\\logo.png");
        logoId.setImage(logo);

        displayEvents();
    }

    private void displayEvents() {
        try {
            List<Event> events = ES.diplayList();

            // Call the method to create user boxes and add them to the container
            List<VBox> eventBoxes = createeventBox(events);
            eventContainer.getChildren().addAll(eventBoxes);
            // Add padding to the eventContainer
            eventContainer.setPadding(new Insets(10)); // Adjust the value as needed
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }



    private final String SQL_SELECT_EVENT_DATES = "SELECT event_date FROM event";
    private final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public List<LocalDate> getDatesWithEvents() {
        List<LocalDate> eventDates = new ArrayList<>();

        // Ensure that the database connection is open
        try (Connection connection = MyDatabase.getInstance().getConn()) {
            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EVENT_DATES);
            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Process result set
            while (resultSet.next()) {
                // Retrieve date from result set
                String dateString = resultSet.getString("event_date");

                // Parse date string to LocalDate
                LocalDate eventDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));

                // Add event date to the list
                eventDates.add(eventDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception (e.g., log error, show error message to user)
        }

        return eventDates;
    }

    /*@FXML

    void show_calendar(ActionEvent event) {

        Connection connection = null;
        try {
            connection = MyDatabase.getInstance().getConn();

            CalendarView calendarView = new CalendarView();
            calendarView.setShowMonthPage(true);

            // Populate disabled dates with events
            List<LocalDate> datesWithEvents = getDatesWithEvents(connection);
            ObservableList<LocalDate> disabledDates = FXCollections.observableArrayList(datesWithEvents);

            // Customize the appearance of disabled dates
            calendarView.setDayCellFactory(date -> {
                Label dayLabel = new Label();
                dayLabel.setText(String.valueOf(date.getDayOfMonth()));
                if (disabledDates.contains(date)) {
                    dayLabel.setStrikethrough(true);
                }
                VBox vbox = new VBox(dayLabel);
                vbox.setAlignment(Pos.CENTER);
                return vbox;
            });

            // Create a new Stage to display the CalendarView
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Calendar with Events");
            stage.setScene(new Scene(calendarView));
            stage.show();
        } finally {
            // Close the database connection in a finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        }

*/




    private List<VBox> createeventBox(List<Event> events) {
        List<VBox> eventBoxes = new ArrayList<>();
        for (Event event : events) {
            VBox eventBox = new VBox();
            eventBox.getStyleClass().add("eventBox"); // Add style class to VBox
            eventBox.setStyle("-fx-padding: 20px; -fx-spacing: 10px; -fx-background-color: #f7f8fa; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

            // Add user name label
            Label titleLabel = new Label(event.getEvent_title().toUpperCase());
            Label categoryLabel = new Label(event.getCategory());
            Label dateLabel = new Label(event.getEvent_date().toString());
            Label durationLabel = new Label(String.valueOf(event.getDuration()) + " HOUR(S)");

            titleLabel.setFont(Font.font("Gill Sans MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            categoryLabel.setFont(Font.font("Gill Sans MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            dateLabel.setFont(Font.font("Gill Sans MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            durationLabel.setFont(Font.font("Gill Sans MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            Button attendEventButton = new Button("Attend Event");

// Define the spacing between buttons (adjust as needed)
            HBox buttonsBox = new HBox(10); // 10 is the spacing between buttons (adjust as needed)
            buttonsBox.getChildren().add(attendEventButton);

// Set padding for the HBox
            buttonsBox.setPadding(new Insets(10, 0, 0, 10));
            eventBox.setAlignment(Pos.CENTER);
            eventBox.getChildren().addAll(titleLabel, categoryLabel, dateLabel, durationLabel,attendEventButton);//display everything
            attendEventButton.setOnMouseClicked(mouseEvent -> {
                System.out.println("clicked");
                sendsmsService.SendSms("+216 28 780 217","attended this event");
            });
            // Add margin to the bottom of the VBox
            eventBox.setMargin(eventBox, new Insets(10));
            eventBoxes.add(eventBox);

        }
        return eventBoxes;
    }
    @FXML
    void moveToAuction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/placeBid.fxml"));
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
        newStage.setTitle("Tuni'Art");

        // Close the old stage
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        // Show the new stage
        newStage.show();

        System.out.println("moved");

    }
    @FXML
    void show_weather(ActionEvent event) {
        String apiKey = "GywZa53kD1MZRvb7HBs9SsmBUWK62MUD";
        String city = "Tunis";

        try {
            // Fetch location key for the specified city
            String locationKey = fetchLocationKey(apiKey, city);

            if (locationKey != null) {
                // Fetch weather information using the location key
                fetchWeatherData(apiKey, locationKey);
            } else {
                displayError("City not found.");
            }
        } catch (Exception e) {
            displayError("Failed to fetch weather information. Please try again later.");
        }
    }

    private String fetchLocationKey(String apiKey, String city) throws Exception {
        String apiUrl = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + city;
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONArray jsonArray = new JSONArray(response.toString());
        if (jsonArray.length() > 0) {
            JSONObject location = jsonArray.getJSONObject(0);
            return location.getString("Key");
        }
        return null;
    }

    private void fetchWeatherData(String apiKey, String locationKey) throws Exception {
        String apiUrl = "http://dataservice.accuweather.com/currentconditions/v1/" + locationKey + "?apikey=" + apiKey;
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONArray jsonArray = new JSONArray(response.toString());
        if (jsonArray.length() > 0) {
            JSONObject weatherData = jsonArray.getJSONObject(0);
            String weatherDescription = weatherData.getString("WeatherText");
            JSONObject temperature = weatherData.getJSONObject("Temperature");
            double celsiusTemperature = temperature.getJSONObject("Metric").getDouble("Value");

            // Display weather information in an alert dialog
            displayWeatherInfo(weatherDescription, celsiusTemperature);
        } else {
            displayError("Failed to fetch weather information. Please try again later.");
        }
    }

    private void displayWeatherInfo(String weatherDescription, double temperature) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Weather Information");
        alert.setHeaderText(null);
        alert.setContentText("Current Weather In Tunis: " + weatherDescription + "\nTemperature: " + temperature + "°C");
        alert.showAndWait();
    }

    private void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Your existing
}