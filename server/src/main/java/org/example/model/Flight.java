package org.example.model;

public class Flight extends Entity<Integer> {
    private String destination;
    private String departureDate;
    private String departureTime;
    private String airport;
    private int noOfSeats;

    public Flight( String destination, String departureDate, String departureTime, String airport, int noOfSeats) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.airport = airport;
        this.noOfSeats = noOfSeats;
    }
    public Flight(){

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", airport='" + airport + '\'' +
                ", noOfSeats=" + noOfSeats +
                '}';
    }
}
