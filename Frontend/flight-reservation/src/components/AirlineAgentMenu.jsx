import React, { useState, useEffect } from 'react';
import { Table, Button, Modal, Form } from 'react-bootstrap';
import api from '../api/axiosConfig';
import parseUser from './ParseUser';

const AirlineAgentMenu = () => {
    const [allBookings, setAllBookings] = useState([]);

    const [showModal, setShowModal] = useState(false);
    const [subject, setSubject] = useState('');
    const [message, setMessage] = useState('');

    const handleOpenModal = () => {
        setShowModal(true);
    }

    const handleCloseModal = () => {
        setShowModal(false);
    }

    const handleSendEmails = async () => {
        try {
            const usersResponse = await api.get('/users/getAllUsers');
            const registeredUsersResponse = await api.get('/registeredUsers/getAllRegisteredUsers');
            //run parseUser on each user in both arrays
            const users = usersResponse.data.map(user => parseUser(user));
            const registeredUsers = registeredUsersResponse.data.map(user => parseUser(user));
            //combine the two arrays by matching the userIDs which is the first element in each array
            const registeredNewsletterUsers = users.filter(user => registeredUsers.map(registeredUser => registeredUser[0]).includes(user[0]));
            //remove any users that have unsubscribed from the newsletter which is the 5th element in the registeredUsers array
            registeredNewsletterUsers.filter(user => user[5] === 'true');



            registeredNewsletterUsers.forEach(async user => {
                await api.post('/mail', {
                    to: user.email,
                    subject: subject,
                    message: message
                });
            });

            handleCloseModal();
        } catch (error) {
            console.log(error);
        }
    }


    useEffect(() => {
        const fetchAllBookings = async () => {
            try {
                const response = await api.get('/bookings/getAllBookings');
                const bookingsWithSeatAndUserInfo = await Promise.all(response.data.map(async booking => {
                    const seatResponse = await api.get(`/seats/byFlight/${booking.flightID}`);
                    const seatInfo = seatResponse.data.find(seat => seat.seatNumber === booking.seatNumber);
                    const userResponse = await api.get(`/users/${booking.userID}`);
                    const userInfo = parseUser(userResponse.data);
                    console.log(userInfo);
                    return { ...booking, seatInfo, userInfo };
                }));
                setAllBookings(bookingsWithSeatAndUserInfo);
            } catch (error) {
                console.log(error);
            }
        }

        fetchAllBookings();
    }, []);

    return (
        <div class="row p-2">
            <div class="col">
                <h3>All Bookings</h3>
                <div class="row">
                    <div class="col">
                        <Table striped bordered hover>
                            {allBookings.length > 0 ?
                                <thead>
                                    <tr>
                                        <th>Booking ID</th>
                                        <th>Flight ID</th>
                                        <th>Customer ID</th>
                                        <th>Passenger Name</th>
                                        <th>Seat Number</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                : null}

                            <tbody>
                                {allBookings.map((booking) => (
                                    <tr>
                                        <td>{booking.bookingID}</td>
                                        <td>{booking.flightID}</td>
                                        <td>{booking.userID}</td>
                                        <td>{booking.userInfo?.[1]}</td>
                                        <td>{booking.seatNumber}</td>
                                        <td>{booking.seatInfo?.price}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AirlineAgentMenu;