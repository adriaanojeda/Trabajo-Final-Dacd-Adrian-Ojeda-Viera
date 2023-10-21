package org.ulpgc.is1.model;

public  class Member extends Customer {
    public int points;
    public Address address;
    public Member(String name, String surname, String nf, int points,String street, int number, int postalCode, String city) {
        super(name, surname, nf);
        address = new Address(street,number,postalCode,city);
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
    public Address getAddress() {
        return address;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
