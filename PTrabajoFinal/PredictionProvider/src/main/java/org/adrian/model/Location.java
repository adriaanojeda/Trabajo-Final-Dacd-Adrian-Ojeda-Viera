package org.adrian.model;

/**
 * Clase que representa la información de ubicación.
 */
public class Location {
    private final String name;
    private final Double latitude;
    private final Double longitude;

    /**
     * Constructor para crear un objeto Location.
     *
     * @param name      Nombre de la ubicación.
     * @param latitude  Latitud de la ubicación.
     * @param longitude Longitud de la ubicación.
     */
    public Location(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;

        // Puedes agregar validaciones aquí si es necesario.
        // Por ejemplo, asegurarte de que la latitud y longitud estén en rangos válidos.
    }

    /**
     * Obtiene el nombre de la ubicación.
     *
     * @return Nombre de la ubicación.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la latitud de la ubicación.
     *
     * @return Latitud de la ubicación.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Obtiene la longitud de la ubicación.
     *
     * @return Longitud de la ubicación.
     */
    public Double getLongitude() {
        return longitude;
    }
}
