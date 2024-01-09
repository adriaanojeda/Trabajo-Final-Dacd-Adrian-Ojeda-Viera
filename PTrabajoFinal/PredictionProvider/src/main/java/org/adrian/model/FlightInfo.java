package org.adrian.model;

import java.time.Instant;

public class FlightInfo {
    private Instant timestamp;
    private String sourceLocation;
    private String destination;
    private double price;
    private String location;

    public FlightInfo(Instant timestamp, String sourceLocation, String destination, double price) {
        this.timestamp = timestamp;
        this.sourceLocation = sourceLocation;
        this.destination = destination;
        this.price = price;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }
    public String getLocation() {
        // Lógica para obtener y devolver la ubicación
        // Puedes devolver el valor del atributo correspondiente según tus necesidades
        return location;
    }

}
