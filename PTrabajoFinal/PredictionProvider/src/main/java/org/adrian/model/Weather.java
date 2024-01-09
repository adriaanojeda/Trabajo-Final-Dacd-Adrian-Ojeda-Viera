package org.adrian.model;

import java.time.Instant;

/**
 * Clase que representa la información meteorológica.
 */
public class Weather {
    private final Instant ts;
    private final String ss;
    private final String predictionTime;
    private final String name;
    private final String location;
    private final String date;
    private final double temperature;
    private final int humidity;
    private final int clouds;
    private final double windSpeed;
    private final String description;
    private final double precipitation;
    private final Location locationObject;
    private String weatherDescription;
    private String destination;

    /**
     * Constructor para crear un objeto Weather.
     *
     * @param locationObject Objeto de ubicación.
     * @param ts             Instante de tiempo.
     * @param ss             Identificador de sistema.
     * @param predictionTime Tiempo de predicción.
     * @param name           Nombre.
     * @param location       Ubicación.
     * @param date           Fecha.
     * @param temperature    Temperatura.
     * @param precipitation  Precipitación.
     * @param humidity       Humedad.
     * @param clouds         Nubosidad.
     * @param windSpeed      Velocidad del viento.
     * @param description    Descripción meteorológica.
     */
    public Weather(Location locationObject, Instant ts, String ss, String predictionTime, String name, String location, String date, double temperature, double precipitation, int humidity, int clouds, double windSpeed, String description) {
        this.name = name;
        this.location = location;
        this.locationObject = locationObject;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.description = description;
        this.precipitation = precipitation;
        this.ts = ts;
        this.ss = ss;
        this.predictionTime = predictionTime;
        this.destination = destination;
    }

    // Métodos getters
    // ...

    /**
     * Obtiene la cantidad de precipitación.
     *
     * @return Cantidad de precipitación.
     */
    public double getPrecipitation() {
        return precipitation;
    }

    /**
     * Obtiene el objeto de ubicación.
     *
     * @return Objeto de ubicación.
     */
    public Location getLocationObject() {
        return locationObject;
    }
    public String getLocation() {
        // Lógica para obtener y devolver la ubicación
        // Puedes devolver el valor del atributo correspondiente según tus necesidades
        return location;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }
    public double getTemperature() {
        return temperature;
    }
    public String getDestination() {
        return destination;
    }

}
