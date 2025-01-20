package com.example.backend_tenpo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class TransactionDto {
    @NotNull()
    private int amount;

    @NotNull()
    private String commerce;

    @NotNull()
    private String user;

    @NotNull()
    @PastOrPresent
    private LocalDateTime dateTransaction;

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

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime date) {
        this.dateTransaction = date;
    }
}
