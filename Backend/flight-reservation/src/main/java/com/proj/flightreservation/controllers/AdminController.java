package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.Admin;
import com.proj.flightreservation.services.AdminService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getAllAdmins")
    public String getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return admins.toString();
    }

    @GetMapping("/{id}")
    public String getAdminById(@PathVariable int id) {
        return adminService.getAdminById(id).toString();
    }

    @GetMapping
    public String getAdminByIdBody(@RequestBody @NotNull JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();

        return adminService.getAdminById(id).toString();
    }

}


