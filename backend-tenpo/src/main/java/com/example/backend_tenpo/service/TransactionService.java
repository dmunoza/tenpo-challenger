package com.example.backend_tenpo.service;

import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionInterface {

    private RepositoryInterface repository;

    @Autowired
    public TransactionService(RepositoryInterface theTransactionRepository) {
        repository = theTransactionRepository;
    }

    @Override
    public List<Transaction> getTransaction() {
        return repository.findAll();
    }

    @Override
    public String postTransaction() {
        return "post";
    }

    @Override
    public String putTransaction() {
        return "put";
    }

    @Override
    public String deleteTransaction() {
        return "delete";
    }
}
