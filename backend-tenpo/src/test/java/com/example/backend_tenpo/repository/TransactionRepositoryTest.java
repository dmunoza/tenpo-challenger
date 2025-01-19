package com.example.backend_tenpo.repository;

import com.example.backend_tenpo.entity.Transaction;
import jakarta.persistence.EntityManager;
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
}