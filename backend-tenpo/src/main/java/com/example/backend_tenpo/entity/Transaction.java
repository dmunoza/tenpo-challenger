package com.example.backend_tenpo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id()
    @Column(name = "id")
    UUID id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "commerce")
    private String commerce;

    @Column(name = "user_tenpo")
    private String user;

    @Column(name = "date_transaction")
    private LocalDateTime date;

    public Transaction() {
    }

    public Transaction(int amount, String commerce, String user, LocalDateTime date) {
        this.amount = amount;
        this.commerce = commerce;
        this.user = user;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCommerce() {
        return commerce;
    }
    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
