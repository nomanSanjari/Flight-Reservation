import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Container, Row, Col, Form, Button, ListGroup, ListGroupItem } from 'react-bootstrap';

const CrewManager = () => {
    const [crews, setCrews] = useState([]);
    const [newCrew, setNewCrew] = useState({});
    const [flights, setFlights] = useState([]);

    useEffect(() => {
        const fetchFlights = async () => {
            const response = await api.get('/flights/getAllFlightInfo');
            setFlights(response.data);
        };

        fetchFlights();
    }, []);

    useEffect(() => {
        const fetchCrews = async () => {
            const response = await api.get('/crew/getAllCrews');
            setCrews(response.data);
        };

        fetchCrews();
    }, []);

    const handleInputChange = (event) => {
        setNewCrew({
            ...newCrew,
            [event.target.name]: event.target.value
        });
    };

    const handleCreateCrew = async () => {
        //if any of the fields dont exits, alert the user
        if (!newCrew.crewID || !newCrew.flightID || !newCrew.crewName) {
            alert('Please fill out all fields');
            return;
        }
        //if the crewID already exists, alert the user
        const crew = await api.get(`/crew/${newCrew.crewID}`);
        if (crew.data) {
            alert('Crew ID already exists');
            return;
        }
        const response = await api.post('/crew/createCrew', newCrew);
        //fetch the new crews
        const response2 = await api.get('/crew/getAllCrews');
        setCrews(response2.data);
    };

    const handleDeleteCrew = async (crewId) => {
        console.log(crewId);
        const response = await api.delete(`/crew/${crewId}`);
        //fetch the new crews
        const response2 = await api.get('/crew/getAllCrews');
        setCrews(response2.data);
    };

    return (
        <Container>
            <Row>
                <Col>
                    <h2>Crew Manager</h2>
                    <ListGroup>
                        {crews.map(crew => (
                            <ListGroupItem key={crew.crewID}>
                                <Button variant="danger" onClick={() => handleDeleteCrew(crew.crewID)}>Delete</Button>
                                <p class="text-end">{crew.crewID} - {crew.flightID} - {crew.crewName}</p>
                            </ListGroupItem>
                        ))}
                    </ListGroup>
                </Col>
                <Col>
                    <h3>Create New Crew</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>Crew ID:</Form.Label>
                            <Form.Control type="number" name="crewID" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Flight ID:</Form.Label>
                            <Form.Select name="flightID" onChange={handleInputChange}>
                                <option>Select a flight</option>
                                {flights.map(flight => (
                                    <option key={flight.flightID} value={flight.flightID}>
                                        {flight.flightNumber}
                                    </option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Crew Name:</Form.Label>
                            <Form.Control type="text" name="crewName" onChange={handleInputChange} />
                        </Form.Group>
                        <Button variant="primary" onClick={handleCreateCrew}>Create</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default CrewManager;