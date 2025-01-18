package com.example.backend_tenpo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id()
    @Column(name = "id")
    private String id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "commerce")
    private String commerce;

    @Column(name = "user_tenpo")
    private String user;

    @Column(name = "date_transaction")
    private Date date;

    public Transaction() {
    }

    public Transaction(int amount, String commerce, String user, Date date) {
        this.amount = amount;
        this.commerce = commerce;
        this.user = user;
        this.date = date;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
