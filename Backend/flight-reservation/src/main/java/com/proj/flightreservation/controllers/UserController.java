// src/main/java/com/sample/example/controller/UserController.java
package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.User;
import com.proj.flightreservation.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.id.factory.internal.StandardIdentifierGeneratorFactoryInitiator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.toString();
    }

    @GetMapping("/{id}")
    public String getUserByIdPath(@PathVariable int id) {
        return userService.getUserById(id).toString();
    }

    @GetMapping
    public String getUserByIdBody(@RequestBody @NotNull JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();

        return userService.getUserById(id).toString();
    }

//    @PostMapping("/loginRegisteredUser")
//    public void loginRegisteredUser(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
//        String email = jsonNode.get("email").asText();
//        String password = jsonNode.get("password").asText();
//
//        userService.loginRegisteredUser(httpServletResponse, email, password);
//    }

    @PostMapping("/loginRegisteredUser")
    public ResponseEntity<Map<String, String>> loginRegisteredUser(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();

        Map<String, String> loginResult = userService.loginRegisteredUser(httpServletResponse, email, password);

        // You can check if there's an error and handle it accordingly
        if (loginResult.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResult);
        }

        // If successful, return the login result
        return ResponseEntity.ok(loginResult);
    }

//    @PostMapping("/loginFlightAttendant")
//    public void loginFlightAttendant(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
//        String email = jsonNode.get("email").asText();
//        String password = jsonNode.get("password").asText();
//
//        userService.loginFlightAttendant(httpServletResponse, email, password);
//    }

    @PostMapping("/loginFlightAttendant")
    public ResponseEntity<Map<String, String>> loginFlightAttendant(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();

        Map<String, String> loginResult = userService.loginFlightAttendant(httpServletResponse, email, password);

        // You can check if there's an error and handle it accordingly
        if (loginResult.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResult);
        }

        // If successful, return the login result
        return ResponseEntity.ok(loginResult);
    }
//    @PostMapping("/loginAirlineAgent")
//    public void loginAirlineAgent(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
//        String email = jsonNode.get("email").asText();
//        String password = jsonNode.get("password").asText();
//
//        userService.loginAirlineAgent(httpServletResponse, email, password);
//    }

    @PostMapping("/loginAirlineAgent")
    public ResponseEntity<Map<String, String>> loginAirlineAgent(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();

        Map<String, String> loginResult = userService.loginAirlineAgent(httpServletResponse, email, password);

        // You can check if there's an error and handle it accordingly
        if (loginResult.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResult);
        }

        // If successful, return the login result
        return ResponseEntity.ok(loginResult);
    }
//    @PostMapping("/loginAdmin")
//    public void loginAdmin(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
//        String email = jsonNode.get("email").asText();
//        String password = jsonNode.get("password").asText();
//
//        userService.loginAdmin(httpServletResponse, email, password);
//    }
//    We don't need a createUser since we will create different kind of create user methods
//    below, so no need for a generic one

    @PostMapping("/loginAdmin")
    public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody @NotNull JsonNode jsonNode, HttpServletResponse httpServletResponse) {
        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();

        Map<String, String> loginResult = userService.loginAdmin(httpServletResponse, email, password);

        // You can check if there's an error and handle it accordingly
        if (loginResult.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResult);
        }

        // If successful, return the login result
        return ResponseEntity.ok(loginResult);
    }

//    @PostMapping("/createUser")
//    public User createUser(@RequestBody @NotNull JsonNode jsonNode) {
////        int id = jsonNode.get("id").asInt();
//        String username = jsonNode.get("username").asText();
//        String email = jsonNode.get("email").asText();
//        String address = jsonNode.get("address").asText();
//        String type = jsonNode.get("type").asText();
//
//        User user = new User(username, email, address, type);
//
//        return userService.createUser(username);

    @PostMapping("/createRegisteredUser")
    public User createRegisteredUser(@RequestBody @NotNull JsonNode jsonNode) {
        //For generic users at first
        String username = jsonNode.get("username").asText();
        String email = jsonNode.get("email").asText();
        String address = jsonNode.get("address").asText();
        String type = jsonNode.get("type").asText();

        //For registered users
        String password = jsonNode.get("password").asText();
        String creditCard = jsonNode.get("creditCard").asText();
        boolean monthlyPromotionNews = jsonNode.get("monthlyPromotionNews").asBoolean();
        boolean airportLoungeDiscount = jsonNode.get("airportLoungeDiscount").asBoolean();
        int companionTicketCount = jsonNode.get("companionTicketCount").asInt();

        User user = new User(username, address, email, type);

        return userService.createRegisteredUser(user, password, creditCard, monthlyPromotionNews, airportLoungeDiscount, companionTicketCount);
    }

    @PostMapping("/createFlightAttendant")
    public User createFlightAttendant(@RequestBody @NotNull JsonNode jsonNode) {
        //For generic users at first
        String username = jsonNode.get("username").asText();
        String email = jsonNode.get("email").asText();
        String address = jsonNode.get("address").asText();
        String type = jsonNode.get("type").asText();

        //For flight attendants
        String password = jsonNode.get("password").asText();


        User user = new User(username, address, email, type);

        return userService.createFlightAttendant(user, password);
    }
    @PostMapping("/createAirlineAgent")
    public User createAirlineAgent(@RequestBody @NotNull JsonNode jsonNode) {
        //For generic users at first
        String username = jsonNode.get("username").asText();
        String email = jsonNode.get("email").asText();
        String address = jsonNode.get("address").asText();
        String type = jsonNode.get("type").asText();

        //For Airline Agents
        String password = jsonNode.get("password").asText();


        User user = new User(username, address, email, type);

        return userService.createAirlineAgent(user, password);
    }
    @PostMapping("/createAdmin")
    public User createAdmin(@RequestBody @NotNull JsonNode jsonNode) {
        //For generic users at first
        String username = jsonNode.get("username").asText();
        String email = jsonNode.get("email").asText();
        String address = jsonNode.get("address").asText();
        String type = jsonNode.get("type").asText();

        //For Admins
        String password = jsonNode.get("password").asText();

        User user = new User(username, address, email, type);

        return userService.createAdmin(user, password);
    }
    @PostMapping("/createGuestUser")
    public User createGuestUser(@RequestBody @NotNull JsonNode jsonNode) {
        //For generic users at first
        String username = jsonNode.get("username").asText();
        String email = jsonNode.get("email").asText();
        String address = jsonNode.get("address").asText();
        String type = jsonNode.get("type").asText();


        User user = new User(username, address, email, type);

        return userService.createGuestUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteRegisteredUser(@PathVariable int id) {
        userService.deleteRegisteredUser(id);
    }

    @DeleteMapping("/deleteFlightAttendant/{id}")
    public void deleteFlightAttendant(@PathVariable int id) {
        userService.deleteFlightAttendant(id);
    }
    @DeleteMapping("/deleteGuestUser/{id}")
    public void deleteGuestUser(@PathVariable int id) {
        userService.deleteGuestUser(id);
    }
    @DeleteMapping("/deleteAirlineAgent/{id}")
    public void deleteAirlineAgent(@PathVariable int id) {
        userService.deleteAirlineAgent(id);
    }
    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable int id) {
        userService.deleteAdmin(id);
    }

}
