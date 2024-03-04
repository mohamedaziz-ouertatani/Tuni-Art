package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.JSONObject;
import tn.esprit.utils.MyDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private Label totalPriceLabel;
    @FXML
    private Label joke_id;


    @FXML
    private Button confirmButton;

    private float totalPrice;
    private static Connection connection;
    int userId = 1;

    // Default constructor
    public OrderController() {
        this.connection = MyDatabase.getInstance().getConn();

        // This constructor is needed for FXMLLoader to create an instance.
    }

    // Setter for totalPrice
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
        // Use the totalPrice here to initialize the UI or perform other operations
        totalPriceLabel.setText("Total Price: TND " + totalPrice);
    }
    private void showJoke() {
        String apiUrl = "https://v2.jokeapi.dev/joke/Any";

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        // Send the HTTP request and handle the response asynchronously
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::processJokeResponse)
                .join();
    }
    private void processJokeResponse(String response) {
        // Parse the JSON response
        JSONObject json = new JSONObject(response);

        // Check if the response has the "setup" and "delivery" fields
        if (json.has("setup") && json.has("delivery")) {
            // Extract the setup and delivery parts of the joke
            String setup = json.getString("setup");
            String delivery = json.getString("delivery");

            // Combine the setup and delivery to form the complete joke
            String completeJoke = setup + "\n" + delivery;

            // Set the complete joke text to the label
            joke_id.setText(completeJoke);
        } else {
            // Handle the case where the expected fields are not present in the response
            joke_id.setText("Joke format is not as expected");
        }
    }

    // Setter for Connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Use the totalPrice here to initialize the UI
        totalPriceLabel.setText("Total Price: TND " + totalPrice);
        showJoke();
    }

    @FXML
    private void confirmOrder() {
        CartController cartController =new CartController();
        ClientMain clientMain =new ClientMain();
        int userId = 1;
        System.out.println("120");
        LocalDate orderDate = LocalDate.now();


        try {

            System.out.println("in try");

            // Ensure the connection is open before proceeding
            if (connection == null ) {
                System.out.println("12");
                showErrorAlert("Error confirming order. Connection is null or closed.");
                return;
            }
            System.out.println("before query");

            String query = "INSERT INTO `order` (uid, order_date, totalprice, status) VALUES (?, ?, ?, ?)";

            System.out.println("after query");


            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setDate(2, Date.valueOf(orderDate));
                statement.setFloat(3, totalPrice);
                statement.setInt(4, 1); // Assuming default status

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Order inserted successfully.");
                } else {
                    System.out.println("Failed to insert order.");
                }
                System.out.println("updted");

            }
            showConfirmationAlert("Order Confirmed! Thank you for your purchase.");


            try {
                List<Integer> artRefs = getArtRefsByUserIdUntilEmpty(userId);

                // Now, artRefs contains all art_ref values for the given userId
                for (int artRef : artRefs) {
                    System.out.println("ArtRef: " + artRef);
                    cartController.deleteCartItem(userId,  artRef);

                }

                try {
                    clientMain.clearCartForUser(userId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

           // clientMain.clearCartForUser(userId);

          //  cartController.deleteCartItem(userId,  artRef);

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error confirming order. Please try again.");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////getartrefbyuserid

    public static List<Integer> getArtRefsByUserId(int userId) throws SQLException {
        List<Integer> artRefs = new ArrayList<>();

        if (connection == null) {
            System.err.println("Connection is null. Cannot retrieve art references.");
            return artRefs;
        }

        String sql = "SELECT art_ref FROM cart WHERE uid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int artRef = resultSet.getInt("art_ref");
                    artRefs.add(artRef);
                }
            }
        }

        return artRefs;
    }
   public static List<Integer> getArtRefsByUserIdUntilEmpty(int userId) throws SQLException {
        List<Integer> allArtRefs = new ArrayList<>();
        boolean hasMoreArtRefs = true;

        while (hasMoreArtRefs) {
            List<Integer> currentArtRefs = getArtRefsByUserId(userId);
            if (!currentArtRefs.isEmpty()) {
                allArtRefs.addAll(currentArtRefs);
            } else {
                hasMoreArtRefs = false;
            }
        }

        return allArtRefs;
    }

    /*   @FXML
    private void confirmOrder() {
        if (connection == null) {
            showErrorAlert("Error confirming order. Connection is null.");
            return;
        }

        int userId = 1;
        LocalDate orderDate = LocalDate.now();

        try {
            String query = "INSERT INTO `order` (uid, order_date, totalprice, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setDate(2, Date.valueOf(orderDate));
                statement.setFloat(3, totalPrice);
                statement.setInt(4, 1); // Assuming default status

                statement.executeUpdate();
            }

            showConfirmationAlert("Order Confirmed! Thank you for your purchase.");
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error confirming order. Please try again.");
        } finally {
            closeConnection(); // Make sure to close the connection after use
        }
    }
*/
    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed in OrderController.");
            } catch (SQLException e) {
                throw new RuntimeException("Error closing connection: " + e.getMessage());
            }
        }
    }

    private void showConfirmationAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void showErrorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void convert1(ActionEvent event) {
        try {
            // Replace "YOUR_API_KEY" with the actual API key you obtained from Open Exchange Rates
            String apiKey = "14d9e56b2ceb46a59dc3ae99173a4914";

            // Replace "TND" with the source currency (Tunisian Dinar)
            String sourceCurrency = "TND";

            // Replace "EUR" with the target currency (Euro)
            String targetCurrency = "EUR";

            // Make the API request to get the latest exchange rates
            String apiUrl = "https://open.er-api.com/v6/latest";
            String requestUrl = apiUrl + "?apikey=" + apiKey + "&symbols=" + sourceCurrency + "," + targetCurrency;

            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract the exchange rates
            double sourceRate = jsonResponse.getJSONObject("rates").getDouble(sourceCurrency);
            double targetRate = jsonResponse.getJSONObject("rates").getDouble(targetCurrency);

            // Assuming you have a TextField named "amountTextField" for user input
            double amount = totalPrice;

            // Perform the currency conversion
            double convertedAmount = amount * (targetRate / sourceRate);

            // Assuming you have a Label named "resultLabel" to display the result
            totalPriceLabel.setText(String.format("%.2f %s = %.2f %s", amount, sourceCurrency, convertedAmount, targetCurrency));

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that might occur during the API request or currency conversion
        }
    }
}
