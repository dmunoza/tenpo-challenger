package com.example.backend_tenpo.repository;

import com.example.backend_tenpo.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(entityManager.find(Transaction.class, id)).thenReturn(transaction);

        Transaction result = transactionRepository.findById(id);

        assertEquals(transaction, result);
        verify(entityManager, times(1)).find(Transaction.class, id);
    }
    @Test
    public void save_ShouldPersistTransaction() {
        Transaction transaction = new Transaction();
        doNothing().when(entityManager).persist(transaction);

        String result = transactionRepository.save(transaction);

        assertEquals("Saved", result);
        verify(entityManager, times(1)).persist(transaction);
    }

    @Test
    public void update_ShouldMergeTransaction() {
        Transaction transaction = new Transaction();
        when(entityManager.merge(transaction)).thenReturn(transaction);

        Transaction result = transactionRepository.update(transaction);

        assertEquals(transaction, result);
        verify(entityManager, times(1)).merge(transaction);
    }

    @Test
    public void deleteById_ShouldRemoveTransaction_WhenTransactionExists() {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(entityManager.find(Transaction.class, id)).thenReturn(transaction);
        doNothing().when(entityManager).remove(transaction);

        String result = transactionRepository.deleteById(id);

        assertEquals("Deleted", result);
        verify(entityManager, times(1)).find(Transaction.class, id);
        verify(entityManager, times(1)).remove(transaction);
    }

    @Test
    public void deleteById_ShouldReturnNotFound_WhenTransactionDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(entityManager.find(Transaction.class, id)).thenReturn(null);

        String result = transactionRepository.deleteById(id);

        assertEquals("Not found", result);
        verify(entityManager, times(1)).find(Transaction.class, id);
        verify(entityManager, never()).remove(any(Transaction.class));
    }

    @Test
    public void countTransactionsByUser_ShouldReturnTransactionCount() {
        String clientId = "client123";
        long expectedCount = 5L;
        TypedQuery<Long> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT COUNT(t) FROM Transaction t WHERE t.user = :clientId", Long.class)).thenReturn(query);
        when(query.setParameter("clientId", clientId)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedCount);

        long result = transactionRepository.countTransactionsByUser(clientId);

        assertEquals(expectedCount, result);
        verify(entityManager, times(1)).createQuery("SELECT COUNT(t) FROM Transaction t WHERE t.user = :clientId", Long.class);
        verify(query, times(1)).setParameter("clientId", clientId);
        verify(query, times(1)).getSingleResult();
    }
}