package org.ulpgc.is1.model;

import java.util.ArrayList;
import java.util.List;

public class PaddleManager {
    private List<Court> courts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();


    //AÑADIR
    public void addCustomer(String name, String surname, String nf){
        Customer customer = new Customer(name, surname, nf);
        if(!customers.contains(customer)) customers.add(customer);
    }
    public void addMember(String name, String surname, String nf, int points,String street, int number, int postalCode, String city){
        Member member = new Member(name, surname,nf,points,street,number,postalCode,city);
        if(!customers.contains(member)) customers.add(member);
    }
    public void addCourt(String name, CourtType type, int price){
        Court court = new Court(name, type, price);
        if(!courts.contains(court)) courts.add(court);
    }

    //QUITAR
    public void removeCustomer(int index){
        customers.remove(index);
    }
    public void removeCourt(int index){
        courts.remove(index);
    }

    // GETS
    public List<Customer> getCustomers() {
        return customers;
    }
    public List<Court> getCourts() {
        return courts;
    }

    public void getReserves(){
        for (Customer c: customers){
            System.out.println(c.getReservations());
        }
    }

    /// Crear una reserva por parte del primer cliente en la segunda pista.
    public void reserve(int index_c, int index_p){
    }


    //CONTAR

    public int count(){
        return customers.size();
    }
    public int countMembers(){

    }
    public int countCustomer(){
    }
