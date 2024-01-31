// src/components/BookingForm.jsx
import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import api from '../api/axiosConfig';
import { Container, Row, Col, Button, Spinner, Alert, Modal, Form } from 'react-bootstrap';

const BookingForm = () => {
    const location = useLocation();
    const flight = location.state.flight;
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [bookedSeats, setBookedSeats] = useState([]);
    const [seats, setSeats] = useState([]);

    const [isLoading, setIsLoading] = useState(false);

    const [showModal, setShowModal] = useState(false);
    const [creditCard, setCreditCard] = useState('');

    const handleOpenModal = () => {
        setShowModal(true);
    }

    const handleCloseModal = () => {
        setShowModal(false);
    }

    //bookings look like this:
    /*
    {
        "bookingId": int,
        "userID": int,
        "flightID": int,
        "seatNumber": string (ex: "AI"),
        "insuranceSelected": boolean,
        "paymentAmount": double,
        "cancelled": boolean
    }
    */
    //create flight for testing

    useEffect(() => {
        const fetchSeatsAndBookings = async () => {
            try {
                const [bookingsResponse, seatsResponse] = await Promise.all([
                    api.get(`/bookings/getAllBookings`),
                    api.get(`/seats/byFlight/${flight.flightID}`)
                ]);

                const bookings = bookingsResponse.data;
                const flightBookings = bookings.filter((booking) => booking.flightID === flight.flightID);
                const bookedSeats = flightBookings.map((booking) => booking.seatNumber);
                setBookedSeats(bookedSeats);

                setSeats(seatsResponse.data);
            } catch (error) {
                console.log(error);
            }
        }
        fetchSeatsAndBookings();
    }, [flight.flightID]);

    const handleSeatClick = (seatNumber) => {
        if (!bookedSeats.includes(seatNumber)) {
            if (selectedSeats.includes(seatNumber)) {
                setSelectedSeats(selectedSeats.filter(seat => seat !== seatNumber));
                console.log(selectedSeats);
            } else {
                setSelectedSeats([...selectedSeats, seatNumber]);
                console.log(selectedSeats);
            }

        }
    }

    //bookings look like this:
    /*
    {
        "bookingId": int,
        "userID": int,
        "flightID": int,
        "seatNumber": string (ex: "AI"),
        "insuranceSelected": boolean,
        "paymentAmount": double,
        "isCancelled": boolean
    }
    */
    //note that paymentAmount comes from the seat, not the booking
    const handleBooking = async (e) => {
        if (!creditCard) {
            alert('Please enter your credit card information.');
            return;
        }
        //call the handleBooking function
        handleCloseModal();

        e.preventDefault();
        setIsLoading(true); // Start loading
        try {
            //create a booking for each selected seat
            for (const seatNumber of selectedSeats) {
                const response = await api.post('/bookings/createBooking', {
                    userID: parseInt(sessionStorage.getItem('userID')),
                    flightID: flight.flightID,
                    seatNumber: seatNumber,
                    insuranceSelected: false,
                    paymentAmount: seats.find(seat => seat.seatNumber === seatNumber).price,
                    isCancelled: false
                });
                console.log(response);

                //send email confirmation
                const emailResponse = await api.post('/mail', {
                    to: sessionStorage.getItem('email'),
                    subject: 'Flight Booking Confirmation',
                    body: `Your booking for flight ${flight.flightNumber} has been confirmed.`
                });
                console.log(emailResponse);

            }
            //redirect to the my account page
            setIsLoading(false);
            window.location.href = '/myAccount';
        } catch (error) {
            console.log(error);
            setIsLoading(false); // End loading
        }
    }

    const renderSeats = () => {
        // Sort the seats by their seat numbers
        const sortedSeats = [...seats].sort((a, b) => a.seatNumber.localeCompare(b.seatNumber));

        // Group the seats by the number part of the seat number
        const seatRows = sortedSeats.reduce((rows, seat) => {
            const numberPart = seat.seatNumber.match(/\d+/)[0];
            if (!rows[numberPart]) {
                rows[numberPart] = [];
            }
            rows[numberPart].push(seat);
            return rows;
        }, {});

        // For each group, create a row and within each row, create a column for each seat
        return Object.values(seatRows).map(rowSeats => (
            <Row>
                {rowSeats.map(seat => {
                    let seatClass = 'btn btn-secondary btn-sm';
                    if (selectedSeats.includes(seat.seatNumber)) {
                        seatClass = 'btn btn-primary btn-sm';
                    } else if (bookedSeats.includes(seat.seatNumber)) {
                        seatClass = 'btn btn-danger btn-sm';
                    }
                    return (
                        <Col>
                            <button type="button" className={seatClass} onClick={() => handleSeatClick(seat.seatNumber)}>
                                {seat.seatNumber}
                            </button>
                        </Col>
                    );
                })}
            </Row>
        ));
    }

    return (
        <form onSubmit={handleBooking}>

            <div className="container-fluid bg-primary-subtle p-5 rounded-5">
                <h2 className='p-3'>Finalize Booking</h2>
                {/* Render spinner */}
                {isLoading && <Spinner animation="border" />}
                <Row>
                    <Col>
                        <h3>Flight Details</h3>
                        <p>Flight Number: {flight.flightNumber}</p>
                        <p>Origin: {flight.origin}</p>
                        <p>Destination: {flight.destination}</p>
                        <p>Departure Date: {flight.departureDate}</p>
                    </Col>
                    <Col>
                        <h3>Seat Selection</h3>
                        {renderSeats()}
                    </Col>
                </Row>
                <Button onClick={handleOpenModal}>Confirm Booking</Button>
            </div>



            <Modal show={showModal} onHide={handleCloseModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Enter Credit Card Information</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group>
                            <Form.Label>Credit Card Number</Form.Label>
                            <Form.Control type="text" value={creditCard} onChange={e => setCreditCard(e.target.value)} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseModal}>Close</Button>
                    <Button variant="primary" onClick={handleBooking}>Confirm Booking</Button>
                </Modal.Footer>
            </Modal>

        </form>
    );
};

export default BookingForm;