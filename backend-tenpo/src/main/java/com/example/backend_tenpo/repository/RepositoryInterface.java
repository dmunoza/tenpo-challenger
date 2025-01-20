package com.example.backend_tenpo.repository;

import com.example.backend_tenpo.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface {
    List<Transaction> findAll();
    Transaction findById(UUID id);
    String save(Transaction transaction);
    Transaction update(Transaction transaction);
    String deleteById(UUID id);
    long countTransactionsByUser(String clientId);
}
