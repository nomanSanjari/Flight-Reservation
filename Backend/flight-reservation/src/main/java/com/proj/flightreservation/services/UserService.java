// src/main/java/com/sample/example/service/UserService.java
package com.proj.flightreservation.services;

import com.proj.flightreservation.models.*;
import com.proj.flightreservation.repositories.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    @Autowired
    private FlightAttendantRepository flightAttendantRepository;
    @Autowired
    private GuestUserRepository guestUserRepository;
    @Autowired
    private AirlineAgentRepository airlineAgentRepository;
    @Autowired
    private AdminRepository adminRepository;

    ////Authentication for Registered Users
//    public void loginRegisteredUser(HttpServletResponse httpServletResponse, String email, String password) {
//        User user = userRepository.findByUserEmail(email);
//        boolean valid = registeredUserRepository.checkPasswordForUser(user.getUserID(), password);
//
//        Cookie cookie;
//
//        if (valid) {
//            cookie = new Cookie("token", "true");
//        }
//        else {
//            cookie = new Cookie("token", "false");
//        }
//        httpServletResponse.addCookie(cookie);
//    }

    public Map<String, String> loginRegisteredUser(HttpServletResponse httpServletResponse, String email, String password) {
        Map<String, String> response = new HashMap<>();

        User user = userRepository.findByUserEmail(email);

        if (user != null) {
            boolean valid = registeredUserRepository.checkPasswordForUser(user.getUserID(), password);

            Cookie cookie;

            if (valid) {
                cookie = new Cookie("token", "true");
                response.put("userID", String.valueOf(user.getUserID()));
            } else {
                cookie = new Cookie("token", "false");
            }

            httpServletResponse.addCookie(cookie);
        } else {
            // Handle the case when the user is not found by email
            response.put("error", "User not found");
        }

        return response;
    }


    //Authentication for Flight Attendants
//    public void loginFlightAttendant(HttpServletResponse httpServletResponse, String email, String password) {
//        User user = userRepository.findByUserEmail(email);
//        boolean valid = flightAttendantRepository.checkPasswordForUser(user.getUserID(), password);
//
//        Cookie cookie;
//
//        if (valid) {
//            cookie = new Cookie("token", "true");
//        }
//        else {
//            cookie = new Cookie("token", "false");
//        }
//        httpServletResponse.addCookie(cookie);
//    }

    public Map<String, String> loginFlightAttendant(HttpServletResponse httpServletResponse, String email, String password) {
        Map<String, String> response = new HashMap<>();

        User user = userRepository.findByUserEmail(email);

        if (user != null) {
            boolean valid = flightAttendantRepository.checkPasswordForUser(user.getUserID(), password);

            Cookie cookie;

            if (valid) {
                cookie = new Cookie("token", "true");
                response.put("userID", String.valueOf(user.getUserID()));
            } else {
                cookie = new Cookie("token", "false");
            }

            httpServletResponse.addCookie(cookie);
        } else {
            // Handle the case when the user is not found by email
            response.put("error", "User not found");
        }

        return response;
    }
    //Authentication for Airline Agents
//    public void loginAirlineAgent(HttpServletResponse httpServletResponse, String email, String password) {
//        User user = userRepository.findByUserEmail(email);
//        boolean valid = airlineAgentRepository.checkPasswordForUser(user.getUserID(), password);
//
//        Cookie cookie;
//
//        if (valid) {
//            cookie = new Cookie("token", "true");
//        }
//        else {
//            cookie = new Cookie("token", "false");
//        }
//        httpServletResponse.addCookie(cookie);
//    }

    public Map<String, String> loginAirlineAgent(HttpServletResponse httpServletResponse, String email, String password) {
        Map<String, String> response = new HashMap<>();

        User user = userRepository.findByUserEmail(email);

        if (user != null) {
            boolean valid = airlineAgentRepository.checkPasswordForUser(user.getUserID(), password);

            Cookie cookie;

            if (valid) {
                cookie = new Cookie("token", "true");
                response.put("userID", String.valueOf(user.getUserID()));
            } else {
                cookie = new Cookie("token", "false");
            }

            httpServletResponse.addCookie(cookie);
        } else {
            // Handle the case when the user is not found by email
            response.put("error", "User not found");
        }

        return response;
    }

    //Authentication for Admins
//    public void loginAdmin(HttpServletResponse httpServletResponse, String email, String password) {
//        User user = userRepository.findByUserEmail(email);
//        boolean valid = adminRepository.checkPasswordForUser(user.getUserID(), password);
//
//        Cookie cookie;
//
//        if (valid) {
//            cookie = new Cookie("token", "true");
//        }
//        else {
//            cookie = new Cookie("token", "false");
//        }
//        httpServletResponse.addCookie(cookie);
//    }

    public Map<String, String> loginAdmin(HttpServletResponse httpServletResponse, String email, String password) {
        Map<String, String> response = new HashMap<>();

        User user = userRepository.findByUserEmail(email);

        if (user != null) {
            boolean valid = adminRepository.checkPasswordForUser(user.getUserID(), password);

            Cookie cookie;

            if (valid) {
                cookie = new Cookie("token", "true");
                response.put("userID", String.valueOf(user.getUserID()));
            } else {
                cookie = new Cookie("token", "false");
            }

            httpServletResponse.addCookie(cookie);
        } else {
            // Handle the case when the user is not found by email
            response.put("error", "User not found");
        }

        return response;
    }
    public User createRegisteredUser(User user, String password, String creditCard, boolean monthlyPromotionNews, boolean airportLoungeDiscount, int companionTicketCount) {
        User savedUser = userRepository.save(user);

        // Check if the user is of type 'Registered'
        if ("Registered".equals(savedUser.getUserType())) {
            RegisteredUser registeredUser = new RegisteredUser();
            registeredUser.setUserID(savedUser.getUserID());
            registeredUser.setPwd_RegisteredUser(password);
            // Set other fields as needed

            registeredUser.setCreditCardNumber(creditCard);
            registeredUser.setMonthlyPromotionNews(monthlyPromotionNews);
            registeredUser.setAirportLoungeDiscount(airportLoungeDiscount);
            registeredUser.setCompanionTicketCount(companionTicketCount);

            registeredUserRepository.save(registeredUser);
        }

        return savedUser;
    }
    public User createFlightAttendant(User user, String password) {
        User savedUser = userRepository.save(user);

        // Check if the user is of type 'Registered'
        if ("FlightAttendant".equals(savedUser.getUserType())) {
            FlightAttendant flightAttendant = new FlightAttendant();
            flightAttendant.setUserID(savedUser.getUserID());
            flightAttendant.setPassword(password);

            flightAttendantRepository.save(flightAttendant);
        }

        return savedUser;
    }
    public User createAirlineAgent(User user, String password) {
        User savedUser = userRepository.save(user);

        // Check if the user is of type 'Registered'
        if ("AirlineAgent".equals(savedUser.getUserType())) {
            AirlineAgent airlineAgent = new AirlineAgent();
            airlineAgent.setUserID(savedUser.getUserID());
            airlineAgent.setPassword(password);

            airlineAgentRepository.save(airlineAgent);
        }

        return savedUser;
    }
    public User createAdmin(User user, String password) {
        User savedUser = userRepository.save(user);

        // Check if the user is of type 'Registered'
        if ("Admin".equals(savedUser.getUserType())) {
            Admin admin = new Admin();
            admin.setUserID(savedUser.getUserID());
            admin.setPwdAdmin(password);

            adminRepository.save(admin);
        }

        return savedUser;
    }
    public User createGuestUser(User user) {
        User savedUser = userRepository.save(user);

        // Check if the user is of type 'Guest'
        if ("Guest".equals(savedUser.getUserType())) {
            GuestUser guestUser = new GuestUser();
            guestUser.setUserID(savedUser.getUserID());

            guestUserRepository.save(guestUser);
        }

        return savedUser;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

//    public void deleteUser(int id) {
//        // Check if the user exists
//        Optional<User> optionalUser = userRepository.findById(id);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            // Delete associated records first (e.g., registeredUser records)
//            registeredUserRepository.deleteById(id);
//
//            // Then delete the user
//            userRepository.deleteById(id);
//        }
    public void deleteRegisteredUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User savedUser = optionalUser.get();

            if ("Registered".equalsIgnoreCase(savedUser.getUserType())) {
                // Delete from RegisteredUser table
                registeredUserRepository.deleteById(id);
            }

            // Delete from User table
            userRepository.deleteById(id);
        }
    }

    public void deleteFlightAttendant(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User savedUser = optionalUser.get();

            if ("FlightAttendant".equalsIgnoreCase(savedUser.getUserType())) {
                // Delete from Flight Attendant table
                flightAttendantRepository.deleteById(id);
            }

            // Delete from User table
            userRepository.deleteById(id);
        }
    }

    public void deleteAirlineAgent(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User savedUser = optionalUser.get();

            if ("AirlineAgent".equalsIgnoreCase(savedUser.getUserType())) {
                // Delete from Airline Agent table
                airlineAgentRepository.deleteById(id);
            }

            // Delete from User table
            userRepository.deleteById(id);
        }
    }
    public void deleteAdmin(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User savedUser = optionalUser.get();

            if ("Admin".equalsIgnoreCase(savedUser.getUserType())) {
                // Delete from Admin table
                adminRepository.deleteById(id);
            }

            // Delete from User table
            userRepository.deleteById(id);
        }
    }
    public void deleteGuestUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User savedUser = optionalUser.get();

            if ("Guest".equalsIgnoreCase(savedUser.getUserType())) {
                // Delete from Guest User table
                guestUserRepository.deleteById(id);
            }

            // Delete from User table
            userRepository.deleteById(id);
        }
    }


}
