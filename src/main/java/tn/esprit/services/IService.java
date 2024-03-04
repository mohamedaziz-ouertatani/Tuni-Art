package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void add(T t) throws SQLException;

    void update(T t) throws  SQLException;

    void delete(int id) throws  SQLException;

    List<T> displayList() throws  SQLException;

    /*
        public List<Art_Piece> diplayList() throws SQLException {
            String query = "SELECT * FROM `art`";
            stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);
            List<Art_Piece> Art_Pieces = new ArrayList<>();
            while (res.next()) {
                int Art_ref = res.getInt(1);
                String Art_title = res.getString(2);
                float Art_price = res.getFloat(3);
                int aid = res.getInt(8);
                String Type = res.getString(4);
                String Description = res.getString(6);
                Date Creation = res.getDate(5);
                String Style = res.getString(7);


                Art_Piece a = new Art_Piece(Art_ref,Art_title, Art_price, aid, Type, Creation, Description ,Style);
                Art_Pieces.add(a);
            }
            return Art_Pieces;
        }*/
    //List<Art_Piece> diplayList() throws SQLException;
}
