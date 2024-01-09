package org.adrian;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.jms.JMSException;
import java.io.IOException;
import java.util.Scanner;

public class FlightSensor {
    private SkyscannerApiConnector apiConnector;
    private EventPublisher eventPublisher;
    private String apiKey;
    private Scanner scanner;

    private static final String FLIGHT_PRICES_TOPIC = "flightPrices";

    public FlightSensor(SkyscannerApiConnector apiConnector, EventPublisher eventPublisher, String apiKey) {
        this.apiConnector = apiConnector;
        this.eventPublisher = eventPublisher;
        this.apiKey = apiKey;
        this.scanner = new Scanner(System.in);
    }

    public void startFlightMonitoring(String originAirportCode, String destinationAirportCode, String outboundDate, String inboundDate, String date, String currency) {
        try {
            System.out.println("Iniciando iteración del monitoreo de vuelos...");

            // Solicitar al usuario que ingrese la lista de piernas de consulta
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa la lista de piernas de consulta (por ejemplo, LPA,TFS,TFN): ");
            String legList = scanner.nextLine();

            // Solicitar al usuario que ingrese el número de adultos
            System.out.print("Ingresa el número de adultos (entre 1 y 8): ");
            int numberOfAdults = Integer.parseInt(scanner.nextLine());

            // Solicitar al usuario que ingrese la clase de cabina
            System.out.print("Ingresa la clase de cabina (por ejemplo, economy): ");
            String cabinClass = scanner.nextLine();

            // Construir la consulta para la API de Skyscanner
            String query = buildSkyscannerQuery(originAirportCode, destinationAirportCode, outboundDate, inboundDate, legList, numberOfAdults, cabinClass);

            // Crear sesión en Skyscanner
            System.out.println("Número de adultos: " + numberOfAdults);
            String sessionToken = apiConnector.createSession(apiKey, originAirportCode, destinationAirportCode, outboundDate, inboundDate, legList, numberOfAdults, cabinClass);


            if (sessionToken != null) {
                System.out.println("Sesión creada exitosamente. Token de sesión: " + sessionToken);

                // Monitorear precios de vuelos
                while (true) {
                    System.out.println("Realizando solicitud de precios de vuelos...");
                    String flightData = apiConnector.getFlightPrices(sessionToken, apiKey);

                    if (flightData != null) {
                        // Procesar datos de vuelo (aquí debes implementar tu lógica específica)
                        System.out.println("Datos de vuelo recibidos correctamente: " + flightData);

                        // Publicar eventos de precios de vuelos
                        eventPublisher.publishEvent(FLIGHT_PRICES_TOPIC, flightData);
                    } else {
                        System.err.println("Error al obtener datos de precios de vuelos. Reintentando...");
                        // Dormir por un tiempo antes de volver a intentar
                        Thread.sleep(5000); // Esperar 5 segundos antes de intentar nuevamente
                    }
                }
            } else {
                System.err.println("Error al crear la sesión. El monitoreo de vuelos no puede continuar.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildSkyscannerQuery(String originAirportCode, String destinationAirportCode, String outboundDate, String inboundDate, String legList, int numberOfAdults, String cabinClass) {
        // Construir y devolver la consulta en formato JSON
        JsonObject queryObject = new JsonObject();

        // Agregar parámetros a la consulta
        queryObject.addProperty("destination", "place/" + destinationAirportCode + "-sky");
        queryObject.addProperty("market", "ES");  // Fijar el mercado a España por ahora
        queryObject.addProperty("locale", "es-ES");  // Fijar la localidad a español por ahora
        queryObject.addProperty("currency", "EUR");  // Fijar la moneda a Euro por ahora
        queryObject.addProperty("adults", numberOfAdults);
        queryObject.addProperty("cabinClass", cabinClass);

        // Validar y agregar la lista de piernas a la consulta
        String[] legs = legList.split(",");
        if (legs.length < 2) {
            throw new IllegalArgumentException("La lista de piernas debe contener al menos dos piernas válidas separadas por coma (origen,destino).");
        }
        JsonObject leg = new JsonObject();
        leg.addProperty("origin", legs[0].trim());
        leg.addProperty("destination", legs[1].trim());
        JsonArray legListArray = new JsonArray();
        legListArray.add(leg);
        queryObject.add("legs", legListArray);

        // Otros parámetros
        queryObject.addProperty("outboundPartialDate", outboundDate);
        queryObject.addProperty("inboundPartialDate", inboundDate);

        return queryObject.toString();
    }


    public static void main(String[] args) {
        // Código de inicialización y ejecución de la aplicación
    }
}