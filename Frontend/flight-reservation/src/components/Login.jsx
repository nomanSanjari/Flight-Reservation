import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import api from '../api/axiosConfig';
import AirlineAgentLogin from './AirlineAgentLogin'; // Import the AirlineAgentLogin component
import AdminLogin from './AdminLogin';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState(''); // New state variable for username
    const [address, setAddress] = useState('');
    const [creditCard, setCreditCard] = useState(''); // New state variable for credit card
    const [newsletter, setNewsletter] = useState(false); // New state variable for newsletter

    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isGuest, setIsGuest] = useState(false); // New state variable for guest login

    const [showGuestModal, setShowGuestModal] = useState(false); // New state variable for showing the modal
    const [showRegistrationModal, setShowRegistrationModal] = useState(false); // New state variable for showing the registration modal

    const [showAirlineAgentLogin, setShowAirlineAgentLogin] = useState(false); // New state variable for showing the airline agent login
    const [showAdminLogin, setShowAdminLogin] = useState(false);

    const handleAirlineAgentLogin = () => {
        setShowAirlineAgentLogin(true); // Show the airline agent login when the "Airline Agent Login" button is clicked
    }

    const handleAdminLogin = () => {
        setShowAdminLogin(true);
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    }

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    }

    const handleAddressChange = (e) => {
        setAddress(e.target.value);
    }

    const handleCreditCardChange = (e) => {
        setCreditCard(e.target.value);
    }

    const handleNewsletterChange = (e) => {
        setNewsletter(e.target.value);
    }

    const handleLogin = async () => {
        try {
            // Send a POST request to the server with the user's email and password
            const response = await api.post('/users/loginRegisteredUser', { email, password });
            console.log(email);
            console.log(password);
            console.log(response);
            //check if response is 200
            //if invalid, the response will be {error: "User not found"}
            if (response.data.error !== "User not found" || response.data.userID) {
                setIsAuthenticated(true);
                setIsGuest(false); // Set isGuest to false on successful login
                console.log('Login successful!');
                sessionStorage.setItem('userID', response.data.userID);
                //set the session storage variable to true
                sessionStorage.setItem('isAuthenticated', true);
                //set the session storage variable for the user's email
                sessionStorage.setItem('email', email);
                sessionStorage.setItem('type', 'Registered')
                //redirect to home page
                window.location.href = '/';
            } else {
                alert("Invalid email or password");
            }
        } catch (error) {
            console.log(error);
        }
    }

    const handleGuestLogin = async () => {
        if (isAuthenticated || isGuest) {
            alert("You are already logged in!");
            return;
        }
        setShowGuestModal(true); // Show the modal when the "Guest Login" button is clicked
    }
    // New function for guest login, if the user is not already a guest in the database, create a new guest user
    // note: have to pull in a list of all guest users from the database and check if the user's email is already in the database as there is no guest login function on backend
    const handleConfirmGuestLogin = async () => {
        try {
            // create a new guest user
            const response = await api.post('/users/createGuestUser', {
                username: email,
                email: email,
                address: address,
                type: 'Guest'
            });
            console.log(response);

            // the response should be the new guest user's ID
            if (response.status === 200) {
                setIsAuthenticated(true);
                setIsGuest(true); // Set isGuest to true on successful login
                console.log('Guest Login successful!');
                sessionStorage.setItem('userID', response.data.userID);
                //set the session storage variable to true
                sessionStorage.setItem('isAuthenticated', true);
                //set the session storage variable for the user's email
                sessionStorage.setItem('email', email);
                sessionStorage.setItem('type', 'Guest')
                //redirect to home page
                window.location.href = '/';
            } else {
                alert(response.data.message);
            }
        } catch (error) {
            console.log(error);
        }
    }

    const handleRegister = async () => {
        if (isAuthenticated) {
            alert("You are already logged in!");
            return;
        }
        setShowRegistrationModal(true); // Show the registration modal when the "Register" button is clicked
    }

    const handleConfirmRegistration = async () => {
        try {
            const response = await api.post('/users/createRegisteredUser', {
                username: username,
                email: email,
                address: address,
                type: 'Registered',
                password: password,
                creditCard: creditCard,
                monthlyPromotionNews: newsletter,
                airportLoungeDiscount: false,
                companionTicketCount: 0
            });

            if (response.status === 200) {
                console.log('Registration successful!');
                sessionStorage.setItem('userID', response.data.userID);
                //set the session storage variable to true
                sessionStorage.setItem('isAuthenticated', true);
                //set the session storage variable for the user's email
                sessionStorage.setItem('email', email);
                sessionStorage.setItem('type', 'Registered')
                //redirect to home page
                window.location.href = '/';
            }
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div>
            <div class="container-fluid bg-primary-subtle rounded-5 p-3">
                <div class="row text-center p-5">
                    <div class="col"><h2>Login</h2></div>

                </div>
                <div class="row text-center">
                    <div class="col">
                        <label>Email:<input type="email" value={email} onChange={handleEmailChange} /></label>
                    </div>

                    <div class="col">
                        <label>Password:<input type="password" value={password} onChange={handlePasswordChange} /></label>
                    </div>

                </div>

                <Modal show={showGuestModal} onHide={() => setShowGuestModal(false)}>
                    <Modal.Header closeButton>
                        <Modal.Title>Guest Login</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group controlId="formEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" value={email} onChange={handleEmailChange} />
                            </Form.Group>
                            <Form.Group controlId="formAddress">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="text" placeholder="Enter address" value={address} onChange={handleAddressChange} />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={() => setShowGuestModal(false)}>Close</Button>
                        <Button variant="primary" onClick={handleConfirmGuestLogin}>Confirm</Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={showRegistrationModal} onHide={() => setShowRegistrationModal(false)}>
                    <Modal.Header closeButton>
                        <Modal.Title>Register</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group controlId="formEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" value={email} onChange={handleEmailChange} />
                            </Form.Group>
                            <Form.Group controlId="formUsername">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="text" placeholder="Enter username" value={username} onChange={handleUsernameChange} />
                            </Form.Group>
                            <Form.Group controlId="formPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Enter password" value={password} onChange={handlePasswordChange} />
                            </Form.Group>
                            <Form.Group controlId="formAddress">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="text" placeholder="Enter address" value={address} onChange={handleAddressChange} />
                            </Form.Group>
                            <Form.Group controlId="formCreditCard">
                                <Form.Label>Credit Card</Form.Label>
                                <Form.Control type="text" placeholder="Enter credit card" value={creditCard} onChange={handleCreditCardChange} />
                            </Form.Group>
                            <Form.Group controlId="formNewsletter">
                                <Form.Check type="checkbox" label="Subscribe to our newsletter" value={newsletter} onChange={handleNewsletterChange} />
                            </Form.Group>

                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={() => setShowRegistrationModal(false)}>Close</Button>
                        <Button variant="primary" onClick={handleConfirmRegistration}>Confirm</Button>
                    </Modal.Footer>
                </Modal>

                <div class="row text-center justify-content-md-center p-5">
                    <div class="col col-sm-2">
                        <button class="btn btn-primary" onClick={handleLogin}>Login</button>
                    </div>
                    <div class="col col-sm-2">
                        <button class="btn btn-warning" onClick={handleGuestLogin}>Guest Login</button> {/* New button for guest login */}
                    </div>
                    <div class="col col-sm-2">
                        <button class="btn btn-secondary" onClick={handleRegister}>Register</button>
                    </div>
                    <div class="col col-sm-2">
                        <button class="btn btn-secondary" onClick={handleAirlineAgentLogin}>Agent Login</button> {/* New button for airline agent login */}
                    </div>
                    <div class="col col-sm-2">
                        <button class="btn btn-secondary" onClick={handleAdminLogin}>Admin Login</button>
                    </div>

                </div>


            </div>


            <Modal show={showAirlineAgentLogin} onHide={() => setShowAirlineAgentLogin(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Airline Agent Login</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AirlineAgentLogin /> {/* Render the AirlineAgentLogin component */}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowAirlineAgentLogin(false)}>Close</Button>
                </Modal.Footer>
            </Modal>

            <Modal show={showAdminLogin} onHide={() => setShowAdminLogin(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Admin Login</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AdminLogin /> {/* Render the AdminLogin component */}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowAdminLogin(false)}>Close</Button>
                </Modal.Footer>
            </Modal>
        </div>


    );
};

export default Login;