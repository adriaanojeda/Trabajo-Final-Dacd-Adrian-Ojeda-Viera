package org.ulpgc.is1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String name, surname;
    private NIF nif;
    private List<Reservation> reservations = new ArrayList<>();

    public Customer(String name, String surname, String nf) {
        this.name = name;
        this.surname = surname;
        NIF nif_p = new NIF(nf);
        if (nif_p.isValid()){
            this.nif = nif_p;
        } else{
            this.nif = new NIF("XXXX");
        }
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public NIF getNif() {
        return nif;
    }

    public List<Reservation> getReservations() {
        return this.reservations;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNif(NIF nif) {
        this.nif = nif;
    }
    public void setReservations(Reservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname);
    }


}
