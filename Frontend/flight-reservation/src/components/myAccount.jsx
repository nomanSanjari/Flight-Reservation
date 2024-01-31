//create page to view information about the user
import React, { useState, useEffect } from 'react';
import { Button, Modal } from 'react-bootstrap';
import api from '../api/axiosConfig';
import AirlineAgentMenu from './AirlineAgentMenu'; // Import the AirlineAgentMenu component
import parseUser from './ParseUser';

const MyAccount = () => {
    const [user, setUser] = useState(null);
    const [userBookings, setUserBookings] = useState(null);
    const [showCancelModal, setShowCancelModal] = useState(false); // New state variable for showing the cancel modal
    const [bookingToCancel, setBookingToCancel] = useState(null); // New state variable for the booking to cancel

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await api.get(`/users/${sessionStorage.getItem('userID')}`);
                const user = response.data;
                //user is formatted like this, note this is one long string:
                //Optional[userID -> firstName -> lastName -> address -> email -> type]
                //convert user to an array
                //define the array
                const userArray = parseUser(user);
                console.log(user);
                console.log(userArray);
                setUser(userArray);
            } catch (error) {
                console.log(error);
            }
        }
        fetchUser();
    }, []);

    useEffect(() => {
        const fetchUserBookings = async () => {
            //fetch all the bookings, then filter them by the user's ID
            try {
                const response = await api.get('/bookings/getAllBookings');
                const bookings = response.data;
                console.log(bookings);
                const userBookings = bookings.filter((booking) => booking.userID === parseInt(sessionStorage.getItem('userID')));
                console.log(userBookings);
                setUserBookings(userBookings);
            } catch (error) {
                console.log(error);
            }
        }
        fetchUserBookings();
    }
        , []);

    const handleOpenCancelModal = (bookingId) => {
        console.log(bookingId);
        setBookingToCancel(bookingId);
        setShowCancelModal(true);
    }

    const handleCancelBooking = async () => {
        try {
            await api.delete(`/bookings/${bookingToCancel}`);
            setUserBookings(userBookings.filter(booking => booking.bookingID !== bookingToCancel));
            setShowCancelModal(false);

            sendCancellationEmail(bookingToCancel);
        } catch (error) {
            console.log(error);
        }
    }

    const sendCancellationEmail = async (bookingId) => {
        try {
            const response = await api.post('/mail', {
                to: user?.[3], // The user's email address
                subject: 'Booking Cancellation Confirmation',
                body: `Your booking with ID ${bookingId} has been successfully cancelled.`
            });

            console.log(response.data);
            console.log('Email sent successfully')

            alert('Booking cancelled successfully');
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div class="container-lg bg-primary-subtle rounded-4">
            <h2 class="text-center p-5">My Account</h2>
            <div class="row p-2">
                <div class="col">
                    <h3>My Information</h3>
                    <div class="row">
                        <div class="col">
                            <p>Name: {user?.[1]}</p>
                            <p>Email: {user?.[3]}</p>
                            <p>Address: {user?.[2]}</p>
                            <p>Status: {user?.[4]}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row p-2">
                <div class="col">
                    <h3>My Bookings</h3>
                    <div class="row">
                        <div class="col">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Flight Number</th>
                                        <th scope="col">Seat Number</th>
                                        <th scope="col">Insurance</th>
                                        <th scope="col">Amount Paid</th>
                                        <th scope="col">Cancelled</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {userBookings?.map((booking) => (
                                        <tr>
                                            <td>{booking.flightID}</td>
                                            <td>{booking.seatNumber}</td>
                                            <td>{booking.insuranceSelected ? "Yes" : "No"}</td>
                                            <td>{booking.paymentAmount}</td>
                                            <td>{booking.cancelled ? "Yes" : "No"}</td>
                                            <td>
                                                <Button variant="danger" onClick={() => handleOpenCancelModal(booking.bookingID)}>Cancel</Button>
                                            </td>

                                            <Modal show={showCancelModal} onHide={() => setShowCancelModal(false)}>
                                                <Modal.Header closeButton>
                                                    <Modal.Title>Cancel Booking</Modal.Title>
                                                </Modal.Header>
                                                <Modal.Body>
                                                    Are you sure you want to cancel this booking?
                                                </Modal.Body>
                                                <Modal.Footer>
                                                    <Button variant="secondary" onClick={() => setShowCancelModal(false)}>No</Button>
                                                    <Button variant="danger" onClick={handleCancelBooking}>Yes</Button>
                                                </Modal.Footer>
                                            </Modal>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            {user?.[4] === 'AirlineAgent' && <AirlineAgentMenu />}
        </div>
    );
}

export default MyAccount;