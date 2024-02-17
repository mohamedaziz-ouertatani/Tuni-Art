package tn.esprit;

import tn.esprit.entities.Event;
import tn.esprit.services.EventService;

import java.sql.Date;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       

            System.out.println("Hello world!");
          /*Event e1 = new Event(25,1,"LetArt","talk", Date.valueOf("2024-01-24"));
          Event e2 = new Event(30,1,"ArtArij","talk", Date.valueOf("2024-01-21"));*/
            EventService es = new EventService();
            /*try {
                es.addd(e2);
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }*/

            try{
                es.delete(2);
                es.delete(3);
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }

            try{
                System.out.println(es.diplayList());
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }

        }
}