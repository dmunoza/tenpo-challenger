package com.example.backend_tenpo.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionUpdateDto {
    @NotNull()
    private UUID id;

    private String commerce;
    private String user;
    private Integer amount;
    private LocalDateTime dateTransaction;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }
    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}
