import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Container, Row, Col, Form, Button, ListGroup, ListGroupItem } from 'react-bootstrap';

const FlightsManager = () => {
    const [flights, setFlights] = useState([]);
    const [newFlight, setNewFlight] = useState({});
    const [aircrafts, setAircrafts] = useState([]);

    useEffect(() => {
        const fetchAircrafts = async () => {
            const response = await api.get('/aircrafts/getAllAircrafts');
            setAircrafts(response.data);
        };

        fetchAircrafts();
    }, []);

    const handleInputChange = (event) => {
        setNewFlight({
            ...newFlight,
            [event.target.name]: event.target.value
        });
    };

    useEffect(() => {
        const fetchFlights = async () => {
            const response = await api.get('/flights/getAllFlightInfo');
            setFlights(response.data);
        };

        fetchFlights();
    }, []);

    const handleCreateFlight = async () => {
        //if any of the fields dont exits, alert the user
        if (!newFlight.flightID || !newFlight.origin || !newFlight.destination || !newFlight.departureDate || !newFlight.flightNumber || !newFlight.aircraftID) {
            alert('Please fill out all fields');
            return;
        }
        //if the flightID or flightNumber already exists, alert the user, note there is no getFlightbyID endpoint so we have to get all flights and filter
        const filteredFlight = flights.filter((flight) => flight.flightID === newFlight.flightID || flight.flightNumber === newFlight.flightNumber);
        console.log(filteredFlight);
        if (filteredFlight.length > 0) {
            alert('Flight ID already exists');
            return;
        }

        //crate the new flight
        const response = await api.post('/flights/createFlight', newFlight);
        console.log(response.data);
        //fetch the new flights
        const response2 = await api.get('/flights/getAllFlightInfo');
        setFlights(response2.data);
    };

    const handleDeleteFlight = async (flightID) => {
        const response = await api.delete(`/flights/deleteFlight/${flightID}`);

        //fetch the new flights
        const response2 = await api.get('/flights/getAllFlightInfo');
        setFlights(response2.data);
    };

    return (
        <Container>
            <Row>
                <Col>
                    <h2>Flights Manager</h2>
                    <ListGroup>
                        {flights.map(flight => (
                            <ListGroupItem key={flight.flightID}>
                                <Button variant="danger" onClick={() => handleDeleteFlight(flight.flightID)}>Delete</Button>
                                <p class="text-end">{flight.flightNumber} {flight.origin} to {flight.destination} on {flight.departureDate}</p>
                            </ListGroupItem>
                        ))}
                    </ListGroup>
                </Col>
                <Col>
                    <h3>Create New Flight</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>Flight ID:</Form.Label>
                            <Form.Control type="text" name="flightID" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Origin:</Form.Label>
                            <Form.Control type="text" name="origin" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Destination:</Form.Label>
                            <Form.Control type="text" name="destination" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Departure Date:</Form.Label>
                            <Form.Control type="date" name="departureDate" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Flight Number:</Form.Label>
                            <Form.Control type="text" name="flightNumber" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Aircraft ID:</Form.Label>
                            <Form.Select name="aircraftID" onChange={handleInputChange}>
                                <option>Select an aircraft</option>
                                {aircrafts.map(aircraft => (
                                    <option key={aircraft.aircraftID} value={aircraft.aircraftID}>
                                        {aircraft.aircraftModel} - ID: {aircraft.aircraftID}
                                    </option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Button variant="primary" onClick={handleCreateFlight}>Create</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default FlightsManager;