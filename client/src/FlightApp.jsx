/* eslint-disable no-unused-vars */
import React, {useEffect} from 'react';
import { useState } from 'react';
import FlightTable from './FlightTable.jsx';
import './FlightApp.css'
import FlightForm from "./FlightForm.jsx";
import {GetFlights, DeleteFlight, AddFlight, UpdateFlight} from './utils/rest-calls.js';


export default function FlightApp(){
    const [flights, setFlights] = useState([]);
    const [selectedFlight, setSelectedFlight] = useState(null);
    const [isUpdating, setIsUpdating] = useState(false);

    function addFunc(flight){
        console.log('inside add Func '+flight);
        AddFlight(flight)
            .then(res=> GetFlights())
            .then(flights=>setFlights(flights))
            .catch(error=>console.log('eroare add ',error));
    }

    function deleteFunc(flight){
        console.log('inside deleteFunc '+flight);
        DeleteFlight(flight)
            .then(res=> GetFlights())
            .then(flights=>setFlights(flights))
            .catch(error=>console.log('eroare delete', error));
    }

    function submitUpdateFunc(flight, id) {
        if(!flight || !id) {
            console.error('Flight or ID is undefined', { flight, id });
            return;
        }
        console.log('inside updateFunc '+flight);
        UpdateFlight(flight, id)
            .then(res=> {
                GetFlights().then(flights => setFlights(flights));
                setSelectedFlight(null);
                setIsUpdating(false);
            })
            .catch(error => console.log('eroare update', error));
    }
    function setFlightToUpdate(flight) {
        setSelectedFlight(flight);
        setIsUpdating(true);
    }

    useEffect(()=>{
        console.log('inside useEffect');
        GetFlights().then(flights=>setFlights(flights));
    }, []);

    return (
        <div className="FlightApp">
            <h1> Flight Management App </h1>
            <FlightForm addFunc={addFunc} submitUpdateFunc={submitUpdateFunc} selectedFlight={selectedFlight} isUpdating={isUpdating}/>
            <br/>
            <br/>
            <FlightTable flights={flights} deleteFunc={deleteFunc} setFlightToUpdate={setFlightToUpdate}/>

        </div>
    );
}
