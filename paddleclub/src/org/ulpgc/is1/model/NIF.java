package org.ulpgc.is1.model;

public class NIF {
    private String number;

    public NIF(String number) {
        this.number = number;
    }

    public boolean isValid() {
    }

    private char calcularLetraControl(String digits) {
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

}