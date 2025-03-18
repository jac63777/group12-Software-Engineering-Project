package com.example.movieapp.service;

import com.example.movieapp.model.Customer;
import com.example.movieapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ✅ Retrieve all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ✅ Retrieve a single customer by ID
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    // ✅ Save a new customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // ✅ Delete a customer by ID
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}

