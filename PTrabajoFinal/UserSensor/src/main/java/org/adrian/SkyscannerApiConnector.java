package org.adrian;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.adrian.Metodos.Country;
import org.adrian.Metodos.Currency;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SkyscannerApiConnector {
    private String apiKey;
    private static final String SKYSCANNER_API_BASE_URL = "https://partners.api.skyscanner.net/apiservices";
    private static final String LIVE_PRICES_ENDPOINT_V3 = "/v3/flights/live/search/create";
    private static final String BROWSE_QUOTES_ENDPOINT = "/browsequotes/v1.0";

    private List<Metodos.Country> countries = Arrays.asList(
            new Metodos.Country("AD", "Andorra"),
            new Metodos.Country("AE", "United Arab Emirates")
            // ... Agregar más países según sea necesario
    );

    private List<Metodos.Currency> currencies = Arrays.asList(
            new Metodos.Currency("USD", "United States Dollar"),
            new Metodos.Currency("EUR", "Euro")
            // ... Agregar más divisas según sea necesario
    );

    public SkyscannerApiConnector(String apiKey) {
        this.apiKey = apiKey;
    }

    public String createSession(String apiKey, String originAirportCode, String destinationAirportCode, String outboundDate, String inboundDate, String legList, int numberOfAdults, String cabinClass) throws IOException {
        String createSessionUrl = SKYSCANNER_API_BASE_URL + LIVE_PRICES_ENDPOINT_V3;

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(createSessionUrl);

        httpPost.addHeader("x-api-key", apiKey);
        httpPost.addHeader("Content-Type", "application/json");

        // Construir la consulta para la API de Skyscanner
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

        // Log de la consulta
        System.out.println("Consulta a Skyscanner: " + queryObject.toString());

        // Convertir el objeto JSON a String
        String requestBody = queryObject.toString();

        // Crear sesión en Skyscanner
        HttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            String sessionLocation = response.getFirstHeader("Location").getValue();
            // Log de la respuesta
            System.out.println("Respuesta de Skyscanner: " + EntityUtils.toString(response.getEntity()));

            return sessionLocation.substring(sessionLocation.lastIndexOf("/") + 1);
        } else {
            System.err.println("Error al crear la sesión. Código de estado: " +
                    response.getStatusLine().getStatusCode());
            System.err.println("Mensaje de error: " + EntityUtils.toString(response.getEntity()));
            return null;
        }
    }



    public String getFlightPrices(String sessionToken, String apiKey) throws IOException {
        String pollUrl = SKYSCANNER_API_BASE_URL + LIVE_PRICES_ENDPOINT_V3 + "/poll/" + sessionToken;

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(pollUrl);

        httpGet.addHeader("x-api-key", this.apiKey);

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity());
        } else {
            System.err.println("Error al obtener datos de precios de vuelos. Código de estado: " +
                    response.getStatusLine().getStatusCode());
            return null;
        }
    }

    public String pollResults(String sessionToken) throws IOException {
        String pollResultsUrl = SKYSCANNER_API_BASE_URL + LIVE_PRICES_ENDPOINT_V3 + "/poll/" + sessionToken;

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(pollResultsUrl);

        httpPost.addHeader("x-api-key", apiKey);

        HttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity());
        } else {
            System.err.println("Error al obtener los resultados de vuelo. Código de estado: " +
                    response.getStatusLine().getStatusCode());
            return null;
        }
    }

    public String getFlightQuotes(String country, String currency, String locale,
                                  String originPlace, String destinationPlace,
                                  String outboundPartialDate, String inboundPartialDate) throws IOException {
        String countryCode = getCountryCode(country);
        String currencyCode = getCurrencyCode(currency);

        if (countryCode == null || currencyCode == null) {
            System.err.println("Error: Códigos no válidos para país, divisa o lugar.");
            return null;
        }

        String browseQuotesUrl = String.format("%s%s/%s/%s/%s/%s/%s/%s",
                SKYSCANNER_API_BASE_URL, BROWSE_QUOTES_ENDPOINT,
                country, currency, locale,
                originPlace, destinationPlace,
                outboundPartialDate, inboundPartialDate);

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(browseQuotesUrl);

        httpGet.addHeader("x-api-key", this.apiKey);
        httpGet.addHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity());
        } else {
            System.err.println("Error al obtener datos de precios de vuelos. Código de estado: " +
                    response.getStatusLine().getStatusCode());
            return null;
        }
    }

    private String getCountryCode(String countryName) {
        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(countryName)) {
                return country.getCode();
            }
        }
        return null;
    }

    private String getCurrencyCode(String currencyName) {
        for (Currency currency : currencies) {
            if (currency.getName().equalsIgnoreCase(currencyName)) {
                return currency.getCode();
            }
        }
        return null;
    }
}