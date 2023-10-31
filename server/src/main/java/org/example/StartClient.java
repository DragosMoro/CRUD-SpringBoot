package org.example;

import org.example.client.FlightClient;
import org.example.model.Flight;
import org.springframework.web.client.RestClientException;

public class StartClient {
    private final static FlightClient flightClient=new FlightClient();

    public static void main(String[] args) {
        Flight flight=new Flight("Bucharest",  "01/07/2023", "10:00","Henri Coanda", 100);

        try{
            show(()-> System.out.println(flightClient.addFlight(flight)));
            show(()->{
                Flight[] res=flightClient.getFlights();
                for(Flight f:res){
                    System.out.println(f.getId()+": "+f.getDestination());
                }
            });
            show(()->{
                var flightt=flightClient.getFlightById(1);
                if(flightt != null)
                    System.out.println("Found flight:"+ flightt.getId()+": "+flightt.getDestination());
            });
            show(()->{
                Flight[] res=flightClient.getFlights();
                Integer id=res[res.length-1].getId();
                flight.setNoOfSeats(99);
                flight.setId(id);
                flightClient.updateFlight(flight.getId(), flight);
                Flight flightFound=flightClient.getFlightById(id);
                if (flightFound != null && flightFound.getNoOfSeats()==99)
                    System.out.println("Flight updated successfully with no of seats: " + flightFound.getNoOfSeats());
            });
            show(()->{
                Flight[] res=flightClient.getFlights();
                Integer id=res[res.length-1].getId();
                System.out.println("Deleting flight with id "+id);
                flightClient.deleteFlight(id);
                res=flightClient.getFlights();
                if (res.length > 0 && res[res.length-1].getId()!=id)
                    System.out.println("Flight deleted successfully");

            });

        }
        catch (RestClientException ex){
            System.out.println("Error: "+ex.getMessage());
        }

    }
    private static void show(Runnable task){
        try{
            task.run();
        }catch(Exception ex){
            System.out.println("Service exception"+ ex);
        }
    }

}
