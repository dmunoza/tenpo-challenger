package com.example.backend_tenpo.service;

import com.example.backend_tenpo.entity.Transaction;

import java.util.List;

public interface TransactionInterface {
    List<Transaction> getTransaction();
    String postTransaction();
    String putTransaction();
    String deleteTransaction();
}
