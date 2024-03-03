package tn.esprit;

import tn.esprit.entities.Art_Piece;
import tn.esprit.entities.Art_Review;
import tn.esprit.entities.gallery;
import tn.esprit.services.Art_PieceService;
import tn.esprit.services.Art_ReviewService;
import tn.esprit.services.galleryService;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        //Art_Piece a1= new Art_Piece("art_title",145,5,"type",Date.valueOf("2024-01-01"),"desc","type");
        //Art_Piece a2= new Art_Piece("art_title1",245,5,"tjype",Date.valueOf("2024-01-01"),"desc","type");
        Art_Review r1= new Art_Review(7,2,Date.valueOf("2024-01-02"),4,"comment1");

        gallery g1 = new gallery("gallery","description","location",96841763,"from 10 to 19");
        galleryService gs = new galleryService();
        Art_PieceService as = new Art_PieceService();
        Art_ReviewService rs = new Art_ReviewService();
        //User u1 = new User("Skander", "Kechaou", "skander.kechaou.e@gmail.com",false, 21635020, Date.valueOf("2002-08-23"), "12345");
        Art_Piece NEWPIECE = new Art_Piece("art_title2",772,5,"type",Date.valueOf("2024-07-01"),"desc","type",null);

        //UserService us = new UserService();
        try {
        rs.addd(r1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

       /* try {
            as.delete(7);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }*/
        /*try {
            // Create an Art_Piece object with updated values

            // ADD
            //as.add(NEWPIECE);

            // Call the update method to update the Art_Piece
            //as.update(updatedArtPiece);

        } catch (SQLException e) {
            System.err.println("Error updating Art_Piece: " + e.getMessage());
        }*/

        NEWPIECE.setArt_title("newnewnew");
        NEWPIECE.setArt_ref(18);
        System.out.println(NEWPIECE);

        try {
            as.update(NEWPIECE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try{
            System.out.println(as.diplayList());
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

    }
}