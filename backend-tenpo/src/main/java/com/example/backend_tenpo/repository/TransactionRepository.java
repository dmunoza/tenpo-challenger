package com.example.backend_tenpo.repository;

import com.example.backend_tenpo.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository implements RepositoryInterface {

    private final EntityManager entityManager;

    @Autowired
    public TransactionRepository(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Transaction> findAll() {
        TypedQuery<Transaction> theQuery = entityManager.createQuery("from Transaction", Transaction.class);
        return theQuery.getResultList();
    }

    @Override
    public Transaction findById(String id) {
        return null;
    }

    @Override
    public Transaction updateById(String id, Transaction transaction) {
        return null;
    }

    @Override
    public String deleteById(String id) {
        return "";
    }
}
