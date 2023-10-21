package org.ulpgc.is1.model;

public class Equipment extends Extra{

    public String name;

    public Equipment(int price, String name) {
        super(price);
        this.name = name;
    }

    public void setName(String s) {
        this.name = s;
    }
    @Override
    public String getName(){
        return name;
    }
}
