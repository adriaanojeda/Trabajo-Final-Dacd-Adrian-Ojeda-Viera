package org.adrian;

import com.google.gson.Gson;
import org.adrian.model.FlightInfo;
import org.adrian.model.Weather;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;

public class DatalakeBuilder implements Subscriber {
    // Reemplaza con el nombre real del tópico del nuevo sensor
    private static final String NEW_SENSOR_TOPIC = "flightPrices";

    public static void main(String[] args) {
        // Lógica de inicialización o ejecución de tu módulo DatalakeBuilder
        // Puedes llamar a los métodos o clases que sean necesarios para iniciar tu aplicación
        // ...

        // Crear una instancia de DatalakeBuilder y suscribirse al tópico del nuevo sensor
        DatalakeBuilder datalakeBuilder = new DatalakeBuilder();
        try {
            datalakeBuilder.subscribeToTopic();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        System.out.println("DatalakeBuilder iniciado correctamente.");
    }

    @Override
    public void subscribeToTopic() throws JMSException {
        // Suscribirse al tópico del nuevo sensor
        Subscriber newSensorSubscriber = new AMQTopicSubscriber("datalake-builder-new-sensor", NEW_SENSOR_TOPIC);
        newSensorSubscriber.subscribeToTopic();
    }

    @Override
    public void onMessage(Message message) {
        try {
            // Identificar el tipo de evento (usando la propiedad "eventType" del mensaje)
            String eventType = identifyEventType(message);

            // Procesar el evento según su tipo
            switch (eventType) {
                case "flight":
                    processEvent(message.getBody(String.class), "flight");
                    break;
                case "weather":
                    processEvent(message.getBody(String.class), "weather");
                    break;
                default:
                    // Manejar otros tipos de eventos si es necesario
                    System.out.println("Tipo de evento desconocido: " + eventType);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private String identifyEventType(Message message) throws JMSException {
        // Obtener la propiedad "eventType" del mensaje
        String eventType = message.getStringProperty("eventType");

        // Verificar el tipo de evento y devolverlo
        if (eventType != null && !eventType.isEmpty()) {
            return eventType;
        } else {
            // Si no se proporciona la propiedad "eventType", devolver un valor predeterminado
            return "unknown";
        }
    }

    private void processEvent(String eventData, String eventType) {
        // Lógica para procesar eventos y almacenar en el datalake
        // Adaptar según la estructura de tus eventos y requisitos específicos

        // Aquí, puedes tener casos separados para diferentes tipos de eventos
        switch (eventType) {
            case "flight":
                processFlightEvent(eventData);
                break;
            case "weather":
                processWeatherEvent(eventData);
                break;
            default:
                // Manejar otros tipos de eventos si es necesario
                System.out.println("Tipo de evento desconocido: " + eventType);
        }
    }

    private void processFlightEvent(String flightData) {
        // Parsear los datos del evento de vuelo (suponiendo que esté en formato JSON)
        Gson gson = new Gson();
        FlightInfo flightInfo = gson.fromJson(flightData, FlightInfo.class);

        // Lógica para almacenar en el datalake o realizar otras acciones según tus necesidades
        // ...

        System.out.println("Evento de vuelo procesado y almacenado en el datalake.");
    }

    private void processWeatherEvent(String weatherData) {
        // Parsear los datos del evento meteorológico (suponiendo que esté en formato JSON)
        Gson gson = new Gson();
        Weather weatherInfo = gson.fromJson(weatherData, Weather.class);

        // Lógica para almacenar en el datalake o realizar otras acciones según tus necesidades
        // ...

        System.out.println("Evento meteorológico procesado y almacenado en el datalake.");
    }
    private void recommendFlights(List<FlightInfo> flightData, List<Weather> weatherData) {
        // Lógica para comparar datos de vuelo y meteorológicos y mostrar recomendaciones
        for (FlightInfo flight : flightData) {
            for (Weather weather : weatherData) {
                // Verificar si la ubicación y el destino coinciden y agregar más lógica según tus criterios
                if (flight.getLocation().equals(weather.getLocation()) && flight.getDestination().equals(weather.getDestination())) {
                    // Agrega lógica adicional según tus criterios para recomendar vuelos
                    if (isGoodWeather(weather)) {
                        // Muestra la recomendación basada en flight y weather
                        System.out.println("Vuelo de " + flight.getLocation() + " a " + flight.getDestination() +
                                " con clima " + weather.getWeatherDescription() +
                                " y temperatura de " + weather.getTemperature() + "°C. Recomendado para vuelo.");
                    } else {
                        System.out.println("Vuelo de " + flight.getLocation() + " a " + flight.getDestination() +
                                " con clima " + weather.getWeatherDescription() +
                                " y temperatura de " + weather.getTemperature() + "°C. No recomendado para vuelo.");
                    }
                }
            }
        }
    }

    private boolean isGoodWeather(Weather weather) {
        // Agrega lógica según tus criterios para determinar si el clima es bueno para volar
        // Por ejemplo, puedes considerar una temperatura óptima, baja probabilidad de precipitación, etc.
        double temperatureThreshold = 15; // Temperatura óptima
        double precipitationThreshold = 5; // Máxima probabilidad de precipitación aceptable

        return weather.getTemperature() > temperatureThreshold && weather.getPrecipitation() < precipitationThreshold;
    }
}

