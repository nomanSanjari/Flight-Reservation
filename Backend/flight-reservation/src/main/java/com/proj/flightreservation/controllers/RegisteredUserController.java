package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.RegisteredUser;
import com.proj.flightreservation.services.RegisteredUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/registeredUsers")
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    @Autowired
    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping("/getAllRegisteredUsers")
    public String getAllRegisteredUsers() {
        List<RegisteredUser> registeredUsers = registeredUserService.getAllRegisteredUsers();
        return registeredUsers.toString();
    }

    @GetMapping("/{id}")
    public String getRegisteredUserByIdPath(@PathVariable int id) {
        return registeredUserService.getRegisteredUserById(id).toString();
    }

    @GetMapping
    public String getRegisteredUserByIdBody(@RequestBody @NotNull JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();

        return registeredUserService.getRegisteredUserById(id).toString();
    }

}




