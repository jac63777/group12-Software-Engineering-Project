package com.example.movieapp.controller;

import com.example.movieapp.model.Customer;
import com.example.movieapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            return ResponseEntity.ok().body("{\"message\": \"Customer created successfully\", \"customer\": " + createdCustomer + "}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

     @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        try {
            Customer customer = customerService.updateCustomer(id, updatedCustomer);
            return ResponseEntity.ok().body("{\"message\": \"Customer updated successfully\", \"customer\": " + customer + "}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable int id) {
        try {
            customerService.deleteCustomerById(id);
            return ResponseEntity.ok().body("{\"message\": \"Customer with ID " + id + " deleted successfully\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<?> deleteCustomerByEmail(@PathVariable String email) {
        try {
            customerService.deleteCustomerByEmail(email);
            return ResponseEntity.ok().body("{\"message\": \"Customer with email " + email + " deleted successfully\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
