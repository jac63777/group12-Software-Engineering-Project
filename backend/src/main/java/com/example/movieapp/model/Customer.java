package com.example.movieapp.model;

import jakarta.persistence.*;
import java.util.List;
import com.example.movieapp.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer extends User {

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_subscriber", nullable = false)
    private boolean isSubscriber;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = true)
    private Address address; // Optional field

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // ✅ Prevents serialization error when returning JSON
    private List<PaymentCard> paymentCards;

    public Customer() {}

    public Customer(String firstName, String lastName, String email, String passwordHash,
                    Status status, boolean isSubscriber, Address address) {
        super(firstName, lastName, email, passwordHash);
        this.status = status;
        this.isSubscriber = isSubscriber;
        this.address = address;
    }

    // ✅ Ensuring maximum of 3 payment cards
    public boolean addPaymentCard(PaymentCard card) {
        if (paymentCards.size() >= 3) {
            return false; // Exceeds limit
        }
        paymentCards.add(card);
        return true;
    }

    // ✅ Getters & Setters
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    public void setSubscriber(boolean subscriber) {
        isSubscriber = subscriber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PaymentCard> getPaymentCards() {
        return paymentCards;
    }

    public void setPaymentCards(List<PaymentCard> paymentCards) {
        this.paymentCards = paymentCards;
    }
}
