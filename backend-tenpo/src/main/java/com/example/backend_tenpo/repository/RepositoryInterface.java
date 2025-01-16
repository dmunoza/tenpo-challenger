package com.example.backend_tenpo.repository;

import com.example.backend_tenpo.entity.Transaction;

import java.util.List;

public interface RepositoryInterface {
    List<Transaction> findAll();
    Transaction findById(String id);
    Transaction updateById(String id, Transaction transaction);
    String deleteById(String id);
}
