package com.example.backend_tenpo.repository;

import com.example.backend_tenpo.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
    public Transaction findById(UUID id) {
        //TypedQuery<Transaction> theQuery = entityManager.createQuery("from Transaction where id = :id", Transaction.class);
        //theQuery.setParameter("id", id);
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public Transaction saveOrUpdate(Transaction transaction) {
        return entityManager.merge(transaction);
    }

    @Override
    public String deleteById(UUID id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        if (transaction != null) {
            entityManager.remove(transaction);
            return "Deleted";
        }
        return "Not found";
    }
}
