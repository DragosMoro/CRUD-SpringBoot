package org.example.repository;

import org.example.model.Flight;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class FlightRepository implements IFlightRepository<Integer>{
    private JdbcUtils dbUtils;

    @Autowired
    public FlightRepository(Properties properties) {
        dbUtils=new JdbcUtils(properties);
    }
    @Override
    public void add(Flight elem) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStnt = con.prepareStatement("insert into Flights(destination, departure_date,departure_time,airport, no_seats) values (?, ?, ?, ?, ?)")) {
            preStnt.setString(1, elem.getDestination());
            preStnt.setString(2, elem.getDepartureDate());
            preStnt.setString(3, elem.getDepartureTime());
            preStnt.setString(4, elem.getAirport());
            preStnt.setInt(5, elem.getNoOfSeats());
            int result = preStnt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error DB" + ex);
        }


    }


    @Override
    public void update(Integer flight_id, Flight elem) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStnt = con.prepareStatement("update Flights set destination=?, departure_date=?,departure_time=?,airport=?, no_seats=? where flight_id=?")) {
            preStnt.setString(1, elem.getDestination());
            preStnt.setString(2, elem.getDepartureDate());
            preStnt.setString(3, elem.getDepartureTime());
            preStnt.setString(4, elem.getAirport());
            preStnt.setInt(5, elem.getNoOfSeats());
            preStnt.setInt(6, (Integer) flight_id);
            int result = preStnt.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB" + ex);
        }

    }

    @Override
    public void delete(Integer id) {

        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStnt = con.prepareStatement("delete from Flights where flight_id=?")) {
            preStnt.setObject(1, id);
            int result = preStnt.executeUpdate();

        } catch (SQLException ex) {

            System.err.println("Error DB" + ex);
        }

    }

    @Override
    public Flight findAfterId(Integer flight_id) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Flights where flight_id = ?")) {
            preStmt.setInt(1, flight_id);
            try(ResultSet result = preStmt.executeQuery()){
                if(result.next()) {
                    int id_flight = result.getInt("flight_id");
                    String destination = result.getString("destination");
                    String departureDate = result.getString("departure_date");
                    String departureTime = result.getString("departure_time");
                    String airport = result.getString("airport");
                    int noOfSeats = result.getInt("no_seats");
                    Flight flight = new Flight(destination,departureDate,departureTime,airport,noOfSeats);
                    flight.setId(id_flight);
                    return flight;
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }

    {

    }
        return null;
    }


    @Override
    public Iterable<Flight> getAll() {
        Connection con = dbUtils.getConnection();
        List<Flight> flights = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Flights")){
            try(ResultSet result = preStmt.executeQuery()){
                while(result.next()){
                    int id_flight = result.getInt("flight_id");
                    String destination = result.getString("destination");
                    String departureDate = result.getString("departure_date");
                    String departureTime = result.getString("departure_time");
                    String airport = result.getString("airport");
                    int noOfSeats = result.getInt("no_seats");
                    Flight flight = new Flight(destination,departureDate,departureTime,airport,noOfSeats);
                    flight.setId(id_flight);
                    flights.add(flight);

                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB" + ex);
        }
        return flights;
    }

}
