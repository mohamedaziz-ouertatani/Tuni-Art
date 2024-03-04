package tn.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
    private final String URL = "jdbc:mysql://localhost:3306/tuni'art"; // API:DBMS:ADDRESS: PORT
    private final String USERNAME = "root";
    private final String PWD = "";
    private Connection conn;
    public static MyDatabase instance;

    private MyDatabase() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connected.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }





    // Placeholder method for establishing a new connection
    public Connection establishConnection() {
        try {
            // Load the JDBC driver for MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a new connection (replace the URL, username, and password with your actual values)
            Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tuni'art", "root", "");

            // Set the new connection in your MyDatabase instance
            this.conn = newConnection;

            System.out.println("New connection established successfully.");
            return newConnection; // Return the new connection
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error establishing a new connection: " + e.getMessage());
            return null; // Return null in case of an error
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection: " + e.getMessage());
        }
    }
}
