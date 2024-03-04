package tn.esprit.entities;

import tn.esprit.utils.MyDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;

public class Art {
    private int art_ref;
    private String art_title;
    private float art_price;
    private String type;
    private LocalDate creation;
    private String description;
    private String style;
    private int artist_id;
    private boolean isAvailable;


    public Art() {
    }

    public Art(int art_ref, String art_title, float art_price, String type, LocalDate creation, String description, String style, int artist_id, boolean isAvailable) {
        this.art_ref = art_ref;
        this.art_title = art_title;
        this.art_price = art_price;
        this.type = type;
        this.creation = creation;
        this.description = description;
        this.style = style;
        this.artist_id = artist_id;
        this.isAvailable = isAvailable;
    }

    public int getArt_ref() {
        return art_ref;
    }

    public void setArt_ref(int art_ref) {
        this.art_ref = art_ref;
    }

    public String getArt_title() {
        return art_title;
    }

    public void setArt_title(String art_title) {
        this.art_title = art_title;
    }

    public float getArt_price() {
        return art_price;
    }

    public void setArt_price(float art_price) {
        this.art_price = art_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "Art{" +
                "art_ref=" + art_ref +
                ", art_title='" + art_title + '\'' +
                ", art_price=" + art_price +
                ", type='" + type + '\'' +
                ", creation=" + creation +
                ", description='" + description + '\'' +
                ", style='" + style + '\'' +
                ", artist_id=" + artist_id +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public void setAvailable(boolean isAvailable) throws SQLException {
        this.isAvailable = isAvailable;
        // Assuming there's a method to update the availability status in the database
        updateAvailabilityInDatabase();
    }

    private void updateAvailabilityInDatabase() throws SQLException {
        // Assuming you have a database connection
        Connection connection = MyDatabase.getInstance().getConn();
        String sql = "UPDATE art SET isAvailable = ? WHERE art_ref = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isAvailable);
            statement.setInt(2, art_ref);
            statement.executeUpdate();
        }
    }


}

