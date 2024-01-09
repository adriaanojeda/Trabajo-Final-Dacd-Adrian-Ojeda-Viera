package org.adrian;

import com.google.gson.Gson;
import org.adrian.model.FlightInfo;

import javax.jms.JMSException;
import javax.jms.Message;
import org.adrian.Subscriber;

import java.util.ArrayList;
import java.util.List;
import org.adrian.model.FlightInfo;
import org.adrian.model.Weather;
import com.google.gson.JsonSyntaxException;


public class BusinessUnit implements Subscriber {
    private static final String FLIGHT_TOPIC = "flightPrices";
    private static final String WEATHER_TOPIC = "prediction.Weather";
    private List<FlightInfo> flightInfoList = new ArrayList<>();
    private List<Weather> weatherInfoList = new ArrayList<>();
    private DataMartBuilder dataMartBuilder = new DataMartBuilder();


    @Override
    public void subscribeToTopic() throws JMSException {
        // Suscribirse a los tópicos relevantes
        Subscriber flightSubscriber = new AMQTopicSubscriber("business-unit-flight", FLIGHT_TOPIC);
        Subscriber weatherSubscriber = new AMQTopicSubscriber("business-unit-weather", WEATHER_TOPIC);

        flightSubscriber.subscribeToTopic();
        weatherSubscriber.subscribeToTopic();
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message.getJMSDestination().toString().contains(FLIGHT_TOPIC)) {
                // Procesar evento de vuelo
                String flightData = message.getBody(String.class);
                processFlightEvent(flightData);
            } else if (message.getJMSDestination().toString().contains(WEATHER_TOPIC)) {
                // Procesar evento meteorológico
                String weatherData = message.getBody(String.class);
                processWeatherEvent(weatherData);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void processFlightEvent(String flightData) {
        try {
            // Convertir el JSON a objetos Java (FlightInfo)
            Gson gson = new Gson();
            FlightInfo flightInfo = gson.fromJson(flightData, FlightInfo.class);

            // Almacenar el objeto FlightInfo en una lista o base de datos
            flightInfoList.add(flightInfo);

            // Llamar a DataMartBuilder cuando tengas suficientes datos de vuelo y meteorología
            if (hasEnoughData()) {
                dataMartBuilder.buildDataMart(flightInfoList, weatherInfoList);
                // Limpiar las listas después de procesar los datos
                flightInfoList.clear();
                weatherInfoList.clear();
            }

        } catch (JsonSyntaxException e) {
            System.err.println("Error al procesar el evento de vuelo: " + e.getMessage());
        }
    }

    private boolean hasEnoughData() {
        // Definir el umbral mínimo de eventos de vuelo y meteorología requeridos
        int minFlightEvents = 5; // Ajusta este valor según tus necesidades
        int minWeatherEvents = 5; // Ajusta este valor según tus necesidades

        // Verificar si se tienen suficientes datos de vuelo y meteorología
        return flightInfoList.size() >= minFlightEvents && weatherInfoList.size() >= minWeatherEvents;
    }



    private void processWeatherEvent(String weatherData) {
        // Lógica para procesar evento meteorológico y almacenar datos
        // Puedes convertir el JSON a objetos Java (por ejemplo, WeatherInfo) usando Gson
        // Ejemplo: WeatherInfo weatherInfo = new Gson().fromJson(weatherData, WeatherInfo.class);
        // Almacena los datos en una lista o base de datos según tus necesidades

        // Luego, llama a DataMartBuilder cuando tengas suficientes datos de vuelo y meteorología
        // Por ejemplo, cuando recibas eventos de ambos tópicos
    }
    public List<FlightInfo> getFlightData() {
        // Lógica para obtener y devolver los datos de vuelo
        // Puedes utilizar la lista flightInfoList u otros datos según tus necesidades
        return flightInfoList;
    }
    public List<Weather> getWeatherData() {
        // Lógica para obtener y devolver los datos meteorológicos
        // Puedes utilizar la lista weatherInfoList u otros datos según tus necesidades
        return weatherInfoList;
    }


}
