package com.example.backend_tenpo.service;

import com.example.backend_tenpo.dto.TransactionDto;
import com.example.backend_tenpo.dto.TransactionUpdateDto;
import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.excepton.ResourceNotFoundException;
import com.example.backend_tenpo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    public void findAllTransaction_ShouldReturnAllTransactions() {
        List<Transaction> transactions = List.of(new Transaction(), new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.findAllTransaction();

        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    public void save_ShouldReturnSaved_WhenTransactionIsValid() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(100);
        transactionDto.setCommerce("Store");
        transactionDto.setUser("User");
        transactionDto.setDateTransaction(LocalDateTime.now());

        when(transactionRepository.countTransactionsByUser(transactionDto.getUser())).thenReturn(0L);
        when(transactionRepository.save(any(Transaction.class))).thenReturn("Saved");

        String result = transactionService.save(transactionDto);

        assertEquals("Saved", result);
        verify(transactionRepository, times(1)).countTransactionsByUser(transactionDto.getUser());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void save_ShouldReturnLimitReached_WhenUserHasMaxTransactions() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(100);
        transactionDto.setCommerce("Store");
        transactionDto.setUser("User");
        transactionDto.setDateTransaction(LocalDateTime.now());

        when(transactionRepository.countTransactionsByUser(transactionDto.getUser())).thenReturn(100L);

        String result = transactionService.save(transactionDto);

        assertEquals("User has reached the limit of transactions", result);
        verify(transactionRepository, times(1)).countTransactionsByUser(transactionDto.getUser());
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    public void update_ShouldReturnUpdatedTransaction_WhenTransactionExists() {
        TransactionUpdateDto transactionUpdateDto = new TransactionUpdateDto();
        transactionUpdateDto.setId(UUID.randomUUID());
        transactionUpdateDto.setAmount(200);
        transactionUpdateDto.setCommerce("New Store");

        Transaction existingTransaction = new Transaction();
        when(transactionRepository.findById(transactionUpdateDto.getId())).thenReturn(existingTransaction);
        when(transactionRepository.update(any(Transaction.class))).thenReturn(existingTransaction);

        Transaction result = transactionService.update(transactionUpdateDto);

        assertEquals(existingTransaction, result);
        verify(transactionRepository, times(1)).findById(transactionUpdateDto.getId());
        verify(transactionRepository, times(1)).update(any(Transaction.class));
    }

    @Test
    public void update_ShouldThrowException_WhenTransactionDoesNotExist() {
        TransactionUpdateDto transactionUpdateDto = new TransactionUpdateDto();
        transactionUpdateDto.setId(UUID.randomUUID());

        when(transactionRepository.findById(transactionUpdateDto.getId())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> transactionService.update(transactionUpdateDto));
        verify(transactionRepository, times(1)).findById(transactionUpdateDto.getId());
        verify(transactionRepository, never()).update(any(Transaction.class));
    }

    @Test
    public void delete_ShouldReturnDeleted_WhenTransactionExists() {
        UUID id = UUID.randomUUID();
        when(transactionRepository.deleteById(id)).thenReturn("Deleted");

        String result = transactionService.delete(id);

        assertEquals("Deleted", result);
        verify(transactionRepository, times(1)).deleteById(id);
    }

    @Test
    public void delete_ShouldReturnNotFound_WhenTransactionDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(transactionRepository.deleteById(id)).thenReturn("Not found");

        String result = transactionService.delete(id);

        assertEquals("Not found", result);
        verify(transactionRepository, times(1)).deleteById(id);
    }
}