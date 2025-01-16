package com.example.backend_tenpo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "commerce")
    private String commerce;

    @Column(name = "user")
    private String user;

    @Column(name = "date")
    private Date date;

    public Transaction() {
    }

    public Transaction(int amount, String commerce, String user, Date date) {
        this.amount = amount;
        this.commerce = commerce;
        this.user = user;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount() {
        this.amount = amount;
    }

    public String getCommerce() {
        return commerce;
    }
    public void setCommerce() {
        this.commerce = commerce;
    }
    public String getUser() {
        return user;
    }
    public void setUser() {
        this.user = user;
    }
    public Date getDate() {
        return date;
    }
    public void setDate() {
        this.date = date;
    }
}
