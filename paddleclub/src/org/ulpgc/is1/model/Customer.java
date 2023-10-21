package org.ulpgc.is1.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name, surname;
    private NIF nif;
    private List<Reservation> reservations = new ArrayList<>();

    public Customer(String name, String surname, String nf) {
        this.name = name;
        this.surname = surname;
        this.nif = new NIF(nf);
    }
    public void getName() {
    }

    public void getSurname() {

    }

    public void getNif() {
    }

    public void getReservations() {
    }

    public void setName() {

    }

    public void setSurname() {
    }

    public void setNif() {

    }
    public void setReservations() {
    }

}
