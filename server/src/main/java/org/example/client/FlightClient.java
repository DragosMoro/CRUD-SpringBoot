package org.example.client;

import org.example.model.Flight;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class FlightClient {
    public static final String URL = "http://localhost:8080/flights";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Flight[] getFlights() {
        return execute(() -> restTemplate.getForObject(URL, Flight[].class));
    }

    public Flight getFlightById(Integer id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%d", URL, id), Flight.class));
    }

    public Flight addFlight(Flight flight) {
        return execute(() -> restTemplate.postForObject(URL, flight, Flight.class));
    }

    public void updateFlight(Integer id, Flight flight) {
        execute(() -> {
            restTemplate.put(String.format("%s/%d", URL, id), flight);
            return null;
        });
    }

    public void deleteFlight(Integer id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%d", URL, id));
            return null;
        });
    }
}