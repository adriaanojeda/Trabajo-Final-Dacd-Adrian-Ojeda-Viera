package org.adrian.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.adrian.model.Location;
import org.adrian.model.Weather;

import java.io.IOException;
import java.util.List;

/**
 * Interfaz para proveedores de datos meteorológicos.
 */
public interface WeatherProviderInterface {
    /**
     * Obtiene datos meteorológicos para la lista de ubicaciones dada.
     *
     * @param locationObjectList Lista de ubicaciones para las cuales obtener datos meteorológicos.
     * @return Lista de objetos Weather con datos meteorológicos.
     * @throws IOException Si hay un error al obtener los datos meteorológicos.
     */
    List<Weather> getWeatherData(List<Location> locationObjectList) throws IOException;

    /**
     * Analiza la lista de datos meteorológicos y devuelve una lista de objetos Weather.
     *
     * @param location      Ubicación asociada a los datos meteorológicos.
     * @param weatherList   Lista de datos meteorológicos en formato JSON.
     * @param city           Información de la ciudad asociada a los datos meteorológicos.
     * @param locationName   Nombre de la ubicación.
     * @return Lista de objetos Weather analizados.
     */
    List<Weather> parseWeatherList(Location location, JsonArray weatherList, JsonObject city, String locationName);

    /**
     * Crea un objeto Weather a partir de los datos meteorológicos proporcionados.
     *
     * @param location      Ubicación asociada a los datos meteorológicos.
     * @param weatherData   Datos meteorológicos en formato JSON.
     * @param locationName   Nombre de la ubicación.
     * @param cityName       Nombre de la ciudad asociada a los datos meteorológicos.
     * @return Objeto Weather creado.
     */
    Weather createWeatherObject(Location location, JsonObject weatherData, String locationName, String cityName);

    /**
     * Construye un objeto Gson personalizado.
     *
     * @return Objeto Gson configurado según las necesidades.
     */
    Gson createGson();

    /**
     * Convierte un objeto Weather a su representación JSON.
     *
     * @param weatherObject Objeto Weather a serializar.
     * @return Representación JSON del objeto Weather.
     */
    String serializeWeatherObject(Weather weatherObject);

}
