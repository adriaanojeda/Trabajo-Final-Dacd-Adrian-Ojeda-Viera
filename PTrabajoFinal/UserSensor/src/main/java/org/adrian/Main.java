package org.adrian;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Configurar la conexión con la API de Skyscanner y la clave API
        SkyscannerApiConnector apiConnector = new SkyscannerApiConnector("sh428739766321522266746152871799");
        EventPublisher eventPublisher = new ActiveMQEventPublisher();
        String apiKey = "sh428739766321522266746152871799";

        // Solicitar al usuario que ingrese el código del aeropuerto de origen
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el código del aeropuerto de origen: ");
        String originAirportCode = scanner.nextLine();

        // Solicitar al usuario que ingrese la fecha de salida
        System.out.print("Ingresa la fecha de salida (YYYY-MM-DD): ");
        String outboundDate = scanner.nextLine();

        // Solicitar al usuario que ingrese la fecha de regreso
        System.out.print("Ingresa la fecha de regreso (YYYY-MM-DD): ");
        String inboundDate = scanner.nextLine();

        // Solicitar al usuario que ingrese el código de moneda
        System.out.print("Ingresa el código de moneda (por ejemplo, EUR): ");
        String currency = scanner.nextLine();

        // Solicitar al usuario que ingrese el código del aeropuerto de destino
        System.out.print("Ingresa el código del aeropuerto de destino (por ejemplo, LPA,TFS,TFN): ");
        String destinationAirportCode = scanner.nextLine();

        // Solicitar al usuario que ingrese la lista de piernas de consulta
        System.out.print("Ingresa la lista de piernas de consulta (por ejemplo, LPA,TFS,TFN): ");
        String legList = scanner.nextLine();



// Asegúrate de que la lista de piernas contenga al menos dos piernas válidas separadas por coma
        String[] legs = legList.split(",");
        if (legs.length < 2) {
            throw new IllegalArgumentException("La lista de piernas debe contener al menos dos piernas válidas separadas por coma (origen,destino).");
        }


        // Crear instancia del FlightSensor y comenzar el monitoreo
        FlightSensor flightSensor = new FlightSensor(apiConnector, eventPublisher, apiKey);
        flightSensor.startFlightMonitoring(originAirportCode, destinationAirportCode, outboundDate, inboundDate, legList, currency);

        // Cerrar el scanner
        scanner.close();
    }
}