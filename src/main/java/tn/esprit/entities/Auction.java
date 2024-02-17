package tn.esprit.entities;

import java.sql.Date;

public class Auction {
    int auction_ref,aid;
    Date start_date,end_date;
    String auction_name;
    float threshold;

    public Auction(int auction_ref, String auction_name, Date start_date, Date end_date,float threshold, int aid) {
        this.auction_ref = auction_ref;
        this.auction_name = auction_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.threshold = threshold;
        this.aid = aid;

    }

  //wihout ref because it is autoincrement
    public Auction( String auction_name,Date start_date, Date end_date, float threshold,int aid) {
        this.auction_name = auction_name;
        this.start_date = start_date;
        this.end_date = end_date;

        this.threshold = threshold;
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auction_ref=" + auction_ref +
                ", aid=" + aid +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", auction_name='" + auction_name + '\'' +
                ", threshold=" + threshold +
                '}';
    }

    public int getAuction_ref() {
        return auction_ref;
    }

    public void setAuction_ref(int auction_ref) {
        this.auction_ref = auction_ref;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getAuction_name() {
        return auction_name;
    }

    public void setAuction_name(String auction_name) {
        this.auction_name = auction_name;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }
}
