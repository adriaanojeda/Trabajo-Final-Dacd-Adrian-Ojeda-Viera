package org.ulpgc.is1.model;

public class NIF {
    private String number;

    public NIF(String number) {
        this.number = number;
    }

    public boolean isValid() {
        if (number == null || number.length() != 9) {
            return false; // El NIF debe tener 9 caracteres
        }

        char letraControl = number.charAt(8);
        String digitPart = number.substring(0, 8);

        if (!digitPart.matches("[0-9]+")) {
            return false; // Los primeros 8 caracteres deben ser dígitos
        }

        char letraEsperada = calcularLetra(digitPart);

        return letraControl == letraEsperada;
    }

    private char calcularLetra(String digits) {
        final String letrasControl = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(digits);
        int indice = numero % 23;
        return letrasControl.charAt(indice);
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

}