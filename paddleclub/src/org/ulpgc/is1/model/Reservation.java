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
        this.price = Integer.parseInt(getCourt().getPrice());
    }

    public String price(){
        if(!extras.isEmpty()){
            for(Extra o : extras){
                price = price + o.getPrice();
            }
            return String.valueOf(price);
        }else {
            return String.valueOf(price);
        }
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Court getCourt() {
        return court;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setExtras(Extra extra) {
        if(!extras.contains(extra)) extras.add(extra);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", court=" + court.getName() +
                ", customer=" + customer.getName() +
                ", extras=" + extras +
                ", price=" + price +
                '}';
    }
}