package org.ulpgc.is1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private static int NEXT_ID = 0;
    final private int id;
    private Date date;
    private Court court;
    private Customer customer;
    private List<Extra> extras = new ArrayList<>();
    private int price;

    public Reservation(Court court, Customer customer) {
        this.id = NEXT_ID + 1;
        this.date = new Date();
        this.court = court;
        this.customer = customer;
        this.price = 0;
    }

    public String price() {
    }

    public int getId() {

    }

    public Date getDate() {

    }

    public Court getCourt() {

    }

    public Customer getCustomer() {

    }

    public List<Extra> getExtras() {

    }

    public void setCourt(Court court) {

    }

    public void setCustomer(Customer customer) {

    }

    public void setExtras(Extra extra) {
    }
}
