package com.example.backend_tenpo.service;

import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements TransactionInterface {

    private RepositoryInterface repository;

    @Autowired
    public TransactionService(RepositoryInterface theTransactionRepository) {
        repository = theTransactionRepository;
    }

    public List<Transaction> findAllTransaction() {
        return repository.findAll();
    }

    public Transaction findTransaction(UUID id) {
        return repository.findById(id);
    }

    public String save(Transaction transaction) {
        Transaction response = repository.saveOrUpdate(transaction);
        if (response != null) {
            return "Saved";
        }
        return "Not saved";
    }

    public Transaction update(Transaction transaction) {
        return repository.saveOrUpdate(transaction);
    }

    public String delete(UUID id) {
        return repository.deleteById(id);
    }
}
