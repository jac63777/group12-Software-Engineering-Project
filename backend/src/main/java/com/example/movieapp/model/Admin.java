package com.example.movieapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")  // ✅ This will now refer to user_id as PK
public class Admin extends User {

    public Admin() {}

    public Admin(String firstName, String lastName, String email, String passwordHash) {
        super(firstName, lastName, email, passwordHash);
    }
}
