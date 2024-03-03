package tn.esprit;

import tn.esprit.entities.Auction;
import tn.esprit.entities.Event;
import tn.esprit.services.AuctionService;
import tn.esprit.services.EventService;

import java.sql.Date;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        AuctionService as = new AuctionService();

        System.out.println("Hello world!");
        Event e1 = new Event(25, 1, "LetArt", "talk", Date.valueOf("2024-01-24"));
        Event e2 = new Event(30, 1, "ArtArij", "talk", Date.valueOf("2024-01-21"));
        Event e3 = new Event(30, 1, "GreatShow", "entertain", Date.valueOf("2024-01-21"));

        EventService es = new EventService();
        try {
            es.addd(e3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }

         /*   try{
                es.delete(5);
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }**/

        try {
            System.out.println(es.diplayList());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
        Event updatedEvent = new Event(4, 49, 1, "exhibition", "artouta", Date.valueOf("2023-02-25"));
        try {
            es.update(updatedEvent);
            System.out.println("event updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating Event: " + e.getMessage());
        }
        Auction a4 = new Auction("ladyD", Date.valueOf("2024-02-24"), Date.valueOf("2023-02-25"), 234, 9, 1);
        Auction a1 = new Auction("ladytroc", Date.valueOf("2024-02-24"), Date.valueOf("2023-02-25"), 1330, 9, 1);
        Auction a2 = new Auction("Big affaire", Date.valueOf("2024-03-24"), Date.valueOf("2023-03-25"), 1430, 8, 1);
        Auction a3 = new Auction("butterfly", Date.valueOf("2024-04-24"), Date.valueOf("2023-04-25"), 1770, 8, 1);

        /*try{
            as.addd(a3);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());;
        }
        try{
            System.out.println(as.diplayList());
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        try{
            as.delete(6);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }*/
      //  Auction newAuction = new Auction("YSFF",Date.valueOf("2023-02-25") , Date.valueOf("2023-02-28"), 239, 9, 2);
        Auction noww = new Auction("cute",Date.valueOf("2023-02-25") , Date.valueOf("2023-02-28"), 239, 9, 2);
//2
/*
        try {
            as.addd(noww);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/
//2
        System.out.println(noww);
//1

       noww.setAuction_ref(134);
        noww.setAuction_name("verycute");
        System.out.println(noww);

        try {
            as.update(noww);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(noww);



        //1
        try {
            System.out.println(as.diplayList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}