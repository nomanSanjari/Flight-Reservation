import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import api from '../api/axiosConfig';

const AirlineAgentLogin = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    }

    const handleLogin = async () => {
        try {
            const response = await api.post('/users/loginAirlineAgent', { email, password });
            if (response.data.userID) {
                sessionStorage.setItem('userID', response.data.userID);
                sessionStorage.setItem('isAuthenticated', true);
                sessionStorage.setItem('email', email);
                sessionStorage.setItem('type', 'AirlineAgent')
                window.location.href = '/';
            }
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <Form className="container-fluid" onSubmit={handleLogin}>
            <Form.Group className="row justify-content-lg-center">
                <Form.Label className="col col-sm-2">Email:</Form.Label>
                <Form.Control className="col col-sm-2" type="email" value={email} onChange={handleEmailChange} />
            </Form.Group>
            <Form.Group className="row justify-content-lg-center">
                <Form.Label className="col col-sm-2">Password:</Form.Label>
                <Form.Control className="col col-sm-2" type="password" value={password} onChange={handlePasswordChange} />
            </Form.Group>
            <Form.Group className="row justify-content-md-center p-4">
                <Button className="col col-sm-2" variant="secondary" type="submit">Login</Button>
            </Form.Group>
        </Form>
    );
};

export default AirlineAgentLogin;