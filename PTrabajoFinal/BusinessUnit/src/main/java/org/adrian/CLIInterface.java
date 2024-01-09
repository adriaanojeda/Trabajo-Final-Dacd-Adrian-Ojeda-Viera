package org.adrian;

import org.adrian.model.Weather;

import java.util.Scanner;
import java.util.List;
import org.adrian.model.FlightInfo;

public class CLIInterface {
    private static final BusinessUnit businessUnit = new BusinessUnit();

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de recomendación de vuelos y clima.");
        System.out.println("-----------------------------------------------");

        try {
            businessUnit.subscribeToTopic(); // Iniciar suscripción a tópicos relevantes
            waitForUserInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void waitForUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            System.out.print("Ingrese su elección (0 para salir): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Mostrar recomendaciones de vuelos y clima
                    displayRecommendations();
                    break;
                case 2:
                    // Otras opciones si las hay
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\nMenú de opciones:");
        System.out.println("1. Mostrar recomendaciones de vuelos y clima");
        System.out.println("2. Otras opciones (si las hay)");
        System.out.println("0. Salir");
    }

    private static void displayRecommendations() {
        // Obtener datos de vuelo y clima desde la BusinessUnit (Datamart)
        List<FlightInfo> flightData = businessUnit.getFlightData();
        List<Weather> weatherData = businessUnit.getWeatherData();

        // Verificar si hay datos disponibles
        if (flightData.isEmpty() || weatherData.isEmpty()) {
            System.out.println("Lo siento, no hay suficientes datos disponibles para hacer recomendaciones.");
            return;
        }

        // Realizar lógica para mostrar recomendaciones basadas en los datos del datamart
        System.out.println("Recomendaciones de vuelos y clima:");

        for (FlightInfo flight : flightData) {
            for (Weather weather : weatherData) {
                if (flight.getLocation().equals(weather.getLocation())) {
                    // Aquí agregas tu lógica adicional para decidir si mostrar la recomendación

// Ejemplo: Mostrar la recomendación si la temperatura es agradable (entre 20°C y 30°C) y no hay lluvia
                    if (weather.getTemperature() >= 20 && weather.getTemperature() <= 30 && weather.getPrecipitation() == 0) {
                        System.out.println("Vuelo de " + flight.getLocation() + " a " + flight.getDestination() +
                                " con clima " + weather.getWeatherDescription() +
                                " y temperatura de " + weather.getTemperature() + "°C. Sin lluvia.");
                    }


                    // Otros criterios de recomendación según tus necesidades

                    // Puedes agregar más condiciones según tus criterios, por ejemplo, si hay poca lluvia, buen clima, etc.
                }
            }
        }

    }
}