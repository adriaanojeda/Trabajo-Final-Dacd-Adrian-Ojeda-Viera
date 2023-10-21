package org.ulpgc.is1.model;

import java.util.Objects;

public class Court {
    private String name;
    private CourtType type;
    private int price;

    public Court(String name, CourtType type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(CourtType type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public CourtType getType() {
        return type;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Court court = (Court) o;
        return Objects.equals(name, court.name) && type == court.type;
    }

}
