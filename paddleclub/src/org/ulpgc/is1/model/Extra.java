package org.ulpgc.is1.model;

public abstract class Extra {
    private int price;
    public Extra(int price) {
        this.price = price;
    }

    public void getPrice() {
    }
    public void setPrice(int price) {
    }

    public abstract String getName();


}