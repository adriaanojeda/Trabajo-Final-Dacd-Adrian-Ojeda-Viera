package org.ulpgc.is1.model;

public class Umpire extends Extra{

    public String name, surname;

    public Umpire(int price, String name, String surname) {
        super(price);
        this.name = name;
        this.surname = surname;
    }
    public void setName(String name) {
    }
    public void setSurname(String surname) {

    }

    @Override
    public String getName() {
        return name + " " + surname;
    }
}
