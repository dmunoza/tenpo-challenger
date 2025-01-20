package com.example.backend_tenpo.service;

import com.example.backend_tenpo.dto.TransactionDto;
import com.example.backend_tenpo.dto.TransactionUpdateDto;
import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.excepton.ResourceNotFoundException;
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

    public String save(TransactionDto transaction) {
        try {
            long countTransaction = repository.countTransactionsByUser(transaction.getUser());
            if (countTransaction >= 100) {
                return "User has reached the limit of transactions";
            }
            Transaction entity = new Transaction();
            entity.setId(UUID.randomUUID());
            entity.setAmount(transaction.getAmount());
            entity.setCommerce(transaction.getCommerce());
            entity.setDate(transaction.getDateTransaction());
            entity.setUser(transaction.getUser());

            String response = repository.save(entity);
            if (response.equals("Saved")) {
                return "Saved";
            }
            return "Not saved";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Transaction update(TransactionUpdateDto transaction) {
        Transaction existingTransaction = repository.findById(transaction.getId());
        System.out.println("UPDATE TRANSACTION");

        if (existingTransaction == null) {
            throw new ResourceNotFoundException("Transaccion no encontrada id: " + transaction.getId());
        }
        boolean updated = false;
        System.out.println(transaction);

        if (transaction.getAmount() != null) {
            existingTransaction.setAmount(transaction.getAmount());
        }

        if (transaction.getCommerce() != null) {
            existingTransaction.setCommerce(transaction.getCommerce());
        }

        if (transaction.getUser() != null) {
            existingTransaction.setUser(transaction.getUser());
        }

        if (transaction.getDateTransaction() != null) {
            existingTransaction.setDate(transaction.getDateTransaction());
        }
        return repository.update(existingTransaction);
    }

    public String delete(UUID id) {
        return repository.deleteById(id);
    }
}
