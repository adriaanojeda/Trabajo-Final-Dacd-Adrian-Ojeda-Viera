package org.ulpgc.is1.model;

public class Address {
    private String street, city;
    private int number, postalCode;

    public Address(String street, int number, int postalCode, String city) {
        this.street = street;
        this.city = city;
        this.number = number;
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getNumber() {
        return number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", number=" + number +
                ", postalCode=" + postalCode +
                '}';
    }
}
