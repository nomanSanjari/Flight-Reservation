// src/App.js
import React from 'react';
import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import Home from './components/Home';
import SearchFlights from './components/SearchFlights';
import Login from './components/Login';
import BookingForm from './components/BookingForm';
import MyAccount from './components/myAccount';
import background from './assets/background.jpg'
import AdminPage from './components/AdminPage';

//if user is logged in, show logout button
const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    //check session storage for isAuthenticated
    const isAuthenticated = sessionStorage.getItem('isAuthenticated');
    if (isAuthenticated) {
      setLoggedIn(true);
    }
  }
  );

  //check if user is of type admin, if so, show admin button
  useEffect(() => {
    //check session storage for user type, if admin, show admin button
    if (sessionStorage.getItem('type') === "Admin") {
      setIsAdmin(true);
    }
  }
  );

  const logout = () => {
    //clear all session storage
    sessionStorage.clear();
    setLoggedIn(false);
    //redirect to home page
    window.location.href = "/";
  }

  return (
    <Router>
      <div>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <a class="navbar-brand" to="/">Flight Reservation</a>

            <div class="" id="navbarNav">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <Link class="nav-link" to="/">Home</Link>
                </li>
                <li class="nav-item">
                  <Link class="nav-link" to="/search">Search Flights</Link>
                </li>
                <li class="nav-item">
                  <Link class="nav-link" to={loggedIn ? "/myaccount" : "/login"}>{loggedIn ? "My Account" : "Login"}</Link>
                </li>
                {isAdmin &&
                  <li class="nav-item">
                    <Link class="nav-link" to="/admin">Admin</Link>
                  </li>
                }

                {loggedIn &&
                  <li class="nav-item">
                    <Link class="nav-link" to="/" onClick={logout}>Logout</Link>
                  </li>
                }
              </ul>
            </div>
          </div>
        </nav>

        <div class="container-fluid vh-100 p-5" style={{
          backgroundImage: `url(${background})`,
          backgroundRepeat: 'no-repeat',
          backgroundSize: 'cover',
        }}>
          <Routes>
            <Route path="/search" element={<SearchFlights />} />

            <Route path="/login" element={<Login />} />

            <Route path="/booking/detail" element={<BookingForm />} />

            <Route path="/myaccount" element={<MyAccount />} />

            <Route path="/admin" element={<AdminPage />} />

            <Route path="/" element={<Home />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;