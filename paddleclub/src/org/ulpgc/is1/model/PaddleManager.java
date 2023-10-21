package org.ulpgc.is1.model;

import java.util.ArrayList;
import java.util.List;

public class PaddleManager {
    private List<Court> courts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(String name, String surname, String nf){
    }
    public void addMember(String name, String surname, String nf, int points,String street, int number, int postalCode, String city){
    }
    public void addCourt(String name, CourtType type, int price){
    }

    public void removeCustomer(int index){
        customers.remove(index);
    }
    public void removeCourt(int index){
        courts.remove(index);
    }

    // GETS
    public List<Customer> getCustomers() {
    }
    public List<Court> getCourts() {
    }
    public void getReserves(){
        }
    }

    /// Crear una reserva por parte del primer cliente en la segunda pista.
    public void reserve(int index_c, int index_p){
    }


    //CONTAR

    public int count(){

    }
    public int countMembers(){
    }
    public int countCustomer(){
    }
}
