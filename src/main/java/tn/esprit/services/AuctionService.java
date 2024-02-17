package tn.esprit.services;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import tn.esprit.Utils.MyDatabase;
import tn.esprit.entities.Auction;
import tn.esprit.entities.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuctionService implements IService<Auction> {
    Connection con;
    Statement stm;

    public AuctionService() {
        con = MyDatabase.getInstance().getConn();
    }

    @Override
    public void add(Auction auction) throws SQLException {
        String query = "INSERT INTO `auction`(`auction_ref`, `auction_name`, `start_date`, `end_date`, `threshold`, `aid`) VALUES ('" + auction.getAuction_ref() + "','" + auction.getAuction_name() + "','" + auction.getStart_date() + "','" + auction.getEnd_date() + "','" + auction.getThreshold() + "','" + auction.getAid() + "')";
        stm = con.createStatement();
        stm.executeUpdate(query);
        System.out.println("Auction added!");
    }

    @Override
    public void addd(Auction auction) throws SQLException {
        String query = "INSERT INTO `auction`(`auction_name`, `start_date`, `end_date`, `threshold`, `aid`) VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, auction.getAuction_name());
        ps.setDate(2, auction.getStart_date());
        ps.setDate(3, auction.getEnd_date());
        ps.setFloat(4, auction.getThreshold());
        ps.setInt(5, auction.getAid());
        ps.executeUpdate();
        System.out.println("Auction added!");
    }

    @Override
    public void update(Auction auction) throws SQLException {
        String query = "UPDATE auction SET  auction_name=?, start_date=?, end_date=?, threshold=?, aid=?WHERE auction_ref=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, auction.getAuction_name());
        ps.setDate(2, auction.getStart_date());
        ps.setDate(3, auction.getEnd_date());
        ps.setFloat(4, auction.getThreshold());
        ps.setInt(5, auction.getAid());
        ps.executeUpdate();
        System.out.println("Auction updated!");

    }

    @Override
    public void delete(int ref) throws SQLException {
        String query = "DELETE FROM auction WHERE auction_ref = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, ref);
        ps.executeUpdate();
        System.out.println("Auction deleted");
    }

    @Override
    public List<Auction> diplayList() throws SQLException {
        String query = "SELECT * FROM `auction`";
        stm = con.createStatement();
        ResultSet res = stm.executeQuery(query);
        List<Auction> auctions = new ArrayList<>();
        while (res.next()) {
            String auction_name = res.getString(2);
            Date start_date = res.getDate(3);
            Date end_date = res.getDate(4);
            float threshold = res.getFloat(5);
            int aid = res.getInt(6);
            Auction a = new Auction(auction_name, start_date, end_date, threshold, aid);
            auctions.add(a);
        }
        return null;
    }
}

