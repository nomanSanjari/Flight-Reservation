// src/components/SearchFlights.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axiosConfig';

const SearchFlights = () => {
    const [flights, setFlights] = useState([]);
    const [selectedFlight, setSelectedFlight] = useState(null);
    const [departureCity, setDepartureCity] = useState('');
    const [arrivalCity, setArrivalCity] = useState('');
    const navigate = useNavigate();

    const fetchFlights = async () => {
        try {
            const response = await api.get('/flights/getAllFlightInfo');
            setFlights(response.data);
            console.log(response.data);
        }
        catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        fetchFlights();
    }, []);

    //search through the flights array and return the flights that match the departure and arrival city, if blank return all flights
    const searchFlights = async () => {
        try {
            const response = await api.get('/flights/getAllFlightInfo');
            let flights = response.data;
            if (departureCity !== '') {
                flights = flights.filter((flight) => flight.origin === departureCity);
            }
            if (arrivalCity !== '') {
                flights = flights.filter((flight) => flight.destination === arrivalCity);
            }
            setFlights(flights);
        }
        catch (error) {
            console.log(error);
        }
    }

    const handleFlightClick = (flight) => {
        //check if the user is signed in, if not redirect to login page, if so redirect to the flight booking page, passing the flight as a parameter
        if (sessionStorage.getItem('isAuthenticated') === 'true') {
            setSelectedFlight(flight);
            navigate('/booking/detail', { state: { flight: flight } });
        }
        else {
            navigate('/login');
        }
    }

    return (
        <div>
            <div class="container-lg bg-primary-subtle rounded-4">
                <h2 class="text-center p-5">Search Flights</h2>
                <div class="row p-2">
                    <div class="col text-center">
                        <div class="row"><label class="form-label" >Departure City:</label></div>
                        <input class="form-text" type="text" value={departureCity} onChange={(e) => setDepartureCity(e.target.value)} />
                    </div>

                    <div class="col text-center">
                        <div class="row"><label class="form-label">Arrival City:</label></div>
                        <input class="form-text" type="text" value={arrivalCity} onChange={(e) => setArrivalCity(e.target.value)} />
                    </div>
                </div>
                <div class="row p-5">
                    <div class="col text-center">
                        <button type="button" class="btn btn-primary" onClick={searchFlights}>Search</button>
                    </div>
                </div>


            </div>
            <div class="container-lg bg-warning-subtle rounded-4 p-5 mt-4" style={{ overflowY: 'scroll', maxHeight: '600px' }}>
                {flights.map((flight) => (
                    <div class="row p-3" key={flight.flightID} onClick={() => handleFlightClick(flight)}>
                        <button class="btn btn-warning"><span>&#9992;</span> From: {flight.origin} To: {flight.destination} {flight.departureDate}</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default SearchFlights;