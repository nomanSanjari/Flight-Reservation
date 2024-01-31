import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Container, Row, Col, Form, Button, ListGroup, ListGroupItem } from 'react-bootstrap';

const AirplaneManager = () => {
    const [airplanes, setAirplanes] = useState([]);
    const [newAirplane, setNewAirplane] = useState({
        aircraftModel: '',
        numEconomySeats: '',
        numComfortSeats: '',
        numBusinessSeats: '',
        inUse: false
    });

    const handleInputChange = (event) => {
        setNewAirplane({
            ...newAirplane,
            [event.target.name]: event.target.value
        });
    };

    useEffect(() => {
        const fetchAirplanes = async () => {
            const response = await api.get('/aircrafts/getAllAircrafts');
            setAirplanes(response.data);
        };

        fetchAirplanes();
    }, []);

    const handleCreateAirplane = async () => {
        //if any of the fields dont exits, alert the user
        if (!newAirplane.aircraftModel || !newAirplane.numEconomySeats || !newAirplane.numComfortSeats || !newAirplane.numBusinessSeats) {
            alert('Please fill out all fields');
            return;
        }
        const response = await api.post('/aircrafts/createAircraft', newAirplane);
        console.log(response.data);
        //fetch the new airplanes
        const response2 = await api.get('/aircrafts/getAllAircrafts');
        setAirplanes(response2.data);
    };

    const handleDeleteAirplane = async (aircraftID) => {
        if (!window.confirm('Are you sure you want to delete this airplane?')) {
            return;
        }
        //check if airplane is in use
        const airplane = await api.get(`/aircrafts/${aircraftID}`);
        if (airplane.data.inUse) {
            alert('Cannot delete airplane that is in use');
            return;
        }
        const response = await api.delete(`/aircrafts/${aircraftID}`);
        console.log(response.data);
        //fetch the new airplanes
        const response2 = await api.get('/aircrafts/getAllAircrafts');
        setAirplanes(response2.data);
    };

    return (
        <Container>
            <Row>
                <Col>
                    <h2>Airplanes Manager</h2>
                    <ListGroup>
                        {airplanes.map(airplane => (
                            <ListGroupItem key={airplane.aircraftID}>
                                <Button variant="danger" onClick={() => handleDeleteAirplane(airplane.aircraftID)}>Delete</Button>
                                <p class="text-end">Aircraft Model: {airplane.aircraftModel}</p>
                            </ListGroupItem>
                        ))}
                    </ListGroup>
                </Col>
                <Col>
                    <h3>Create New Airplane</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>Aircraft Model:</Form.Label>
                            <Form.Control type="text" name="aircraftModel" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Number of Economy Seats:</Form.Label>
                            <Form.Control type="number" name="numEconomySeats" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Number of Comfort Seats:</Form.Label>
                            <Form.Control type="number" name="numComfortSeats" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Number of Business Seats:</Form.Label>
                            <Form.Control type="number" name="numBusinessSeats" onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Check type="checkbox" label="In Use" name="inUse" onChange={event => handleInputChange({ target: { name: event.target.name, value: event.target.checked } })} />
                        </Form.Group>
                        <Button variant="primary" onClick={handleCreateAirplane}>Create</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default AirplaneManager;