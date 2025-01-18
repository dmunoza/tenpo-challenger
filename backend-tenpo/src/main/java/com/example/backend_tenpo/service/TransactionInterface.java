package com.example.backend_tenpo.service;

import com.example.backend_tenpo.entity.Transaction;

import java.util.List;

public interface TransactionInterface {
    List<Transaction> findAllTransaction();
    Transaction findTransaction(String id);
    String save(Transaction transaction);
    Transaction update(Transaction transaction);
    String delete(String id);
}
