package com.example.backend_tenpo.service;

import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindTransaction() {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(id)).thenReturn(transaction);

        Transaction result = transactionService.findTransaction(id);

        assertEquals(transaction, result);
        verify(transactionRepository, times(1)).findById(id);
    }
}