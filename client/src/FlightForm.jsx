/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import React, { useEffect } from 'react';
import { useState } from 'react';

export default function FlightForm({ addFunc, submitUpdateFunc, selectedFlight, isUpdating }) {

    const [destination, setDestination] = useState('');
    const [departureDate, setDepartureDate] = useState('');
    const [departureTime, setDepartureTime] = useState('');
    const [airport, setAirport] = useState('');
    const [noOfSeats, setNoOfSeats] = useState('');

    useEffect(() => {
        if (isUpdating && selectedFlight) {
            setDestination(selectedFlight.destination);
            setDepartureDate(selectedFlight.departureDate);
            setDepartureTime(selectedFlight.departureTime);
            setAirport(selectedFlight.airport);
            setNoOfSeats(selectedFlight.noOfSeats);
        }
    }, [isUpdating, selectedFlight])
    function handleSubmit(event) {
        try {
            let flight = {
                destination: destination,
                departureDate: departureDate,
                departureTime: departureTime,
                airport: airport,
                noOfSeats: noOfSeats
            }
            console.log('A flight was submitted: ');
            console.log(flight);
    
            if (isUpdating) {
                if (selectedFlight) {
                    flight.id = selectedFlight.id;
                    submitUpdateFunc(flight, flight.id).then(() => {

                        setDestination('');
                        setDepartureDate('');
                        setDepartureTime('');
                        setAirport('');
                        setNoOfSeats('');
                    });
                } else {
                    console.error('No flight selected for update');
                }
            } else {
                addFunc(flight);
            }
    
            event.preventDefault();
        } catch (error) {
            console.error('Error during form submission: ', error);
        }
    }
    

    return (
        <form onSubmit={handleSubmit} style={{ padding: '10px' }}>
            <label>
                Destination: 
                <input type="text" value={destination} onChange={e => setDestination(e.target.value)} style={{ marginLeft: '10px' }} />
            </label><br />
    
            <label>
                Departure Date: 
                <input type="text" value={departureDate} onChange={e => setDepartureDate(e.target.value)} style={{ marginLeft: '10px' }} />
            </label><br />
    
            <label>
                Departure Time: 
                <input type="text" value={departureTime} onChange={e => setDepartureTime(e.target.value)} style={{ marginLeft: '10px' }} />
            </label><br />
    
            <label>
                Airport: 
                <input type="text" value={airport} onChange={e => setAirport(e.target.value)} style={{ marginLeft: '10px' }} />
            </label><br />
    
            <label >
                Number of Seats: 
                <input type="number" value={noOfSeats} onChange={e => setNoOfSeats(e.target.value)} style={{ marginLeft: '10px'}} />
            </label><br />
    
            <input type="submit" value={isUpdating ? "Update Flight" : "Add Flight"} />
        </form>
    );
    
}
