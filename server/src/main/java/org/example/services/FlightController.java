package org.example.services;

import org.example.model.Flight;
import org.example.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = (List<Flight>) flightRepository.getAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer id) {
        Flight flight = flightRepository.findAfterId(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        flightRepository.add(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id, @RequestBody Flight updatedFlight) {
        if(id == null) {
            System.out.println("ID is undefined");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        flightRepository.update(id, updatedFlight);
        Flight flight = flightRepository.findAfterId(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer id) {
        flightRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

