package com.example.movieapp.controller;

import com.example.movieapp.model.Admin;
import com.example.movieapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create an admin
    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        try {
            Admin createdAdmin = adminService.createAdmin(admin);
            return ResponseEntity.ok().body("{\"message\": \"Admin created successfully\", \"admin\": " + createdAdmin + "}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // Get all admins
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    // Get an admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable int id) {
        try {
            Admin admin = adminService.getAdminById(id);
            return ResponseEntity.ok(admin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // Get an admin by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAdminByEmail(@PathVariable String email) {
        try {
            Admin admin = adminService.getAdminByEmail(email);
            return ResponseEntity.ok(admin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
        try {
            Admin admin = adminService.updateAdmin(id, updatedAdmin);
            return ResponseEntity.ok(admin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage()); // Return error message if update fails
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdminById(@PathVariable int id) {
        try {
            adminService.deleteAdminById(id);
            return ResponseEntity.ok().body("{\"message\": \"Admin with ID " + id + " deleted successfully\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<?> deleteAdminByEmail(@PathVariable String email) {
        try {
            adminService.deleteAdminByEmail(email);
            return ResponseEntity.ok().body("{\"message\": \"Admin with email " + email + " deleted successfully\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}