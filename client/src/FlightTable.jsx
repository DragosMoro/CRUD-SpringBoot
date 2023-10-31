/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React from 'react';
import './FlightApp.css'

function FlightRow({flight, deleteFunc, setFlightToUpdate}) {
    function handleDelete(event){
        console.log('delete button pentru '+flight.id);
        deleteFunc(flight.id);
    }

    function handleUpdate(event) {
        console.log('update button pentru ' + flight.id);
        setFlightToUpdate(flight);
    }

    return (
        <tr>
            <td>{flight.destination}</td>
            <td>{flight.departureDate}</td>
            <td>{flight.departureTime}</td>
            <td>{flight.airport}</td>
            <td>{flight.noOfSeats}</td>
            <td>
                <button onClick={handleUpdate}>Update</button>
                <button onClick={handleDelete} style={{ marginLeft: '5px', gap:"2px" }}>Delete</button>
            </td>
        </tr>
    );
}

export default function FlightTable({flights, deleteFunc, setFlightToUpdate}) {
    console.log("In FlightTable");
    console.log(flights);
    let rows = [];
    flights.forEach(function(flight) {
        rows.push(<FlightRow flight={flight}  key={flight.id} deleteFunc={deleteFunc} setFlightToUpdate={setFlightToUpdate} />);
    });
    return (
        <div className="FlightTable">
            <table className="center">
                <thead>
                <tr>
                    <th>Destination</th>
                    <th>Departure Date</th>
                    <th>Departure Time</th>
                    <th>Airport</th>
                    <th>Number of Seats</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>{rows}</tbody>
            </table>
        </div>
    );
}
