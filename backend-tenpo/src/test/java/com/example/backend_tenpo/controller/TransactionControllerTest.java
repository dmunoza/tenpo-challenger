package com.example.backend_tenpo.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.backend_tenpo.controller.TransactionController;
import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.excepton.CustomExceptionHandler;
import com.example.backend_tenpo.service.TransactionInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TransactionControllerTest {

    private static final Logger log = LoggerFactory.getLogger(TransactionControllerTest.class);
    private MockMvc mockMvc;

    @Mock
    private TransactionInterface transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        when(transactionService.findAllTransaction()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/transaction/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    public void testFindById() throws Exception {
        UUID id = UUID.fromString("3c56e1c9-19f8-4569-9133-d76b5f5eef59");
        when(transactionService.findTransaction(id)).thenReturn(new Transaction());

        mockMvc.perform(get("/transaction/findById")
                        .header("id", id.toString()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isNotEmpty());

    }

    @Test
    public void testSave() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setAmount(2100);
        transaction.setCommerce("Coto");
        transaction.setUser("Juan");
        LocalDate date = LocalDate.parse("2025-01-01");
        transaction.setDate(date);

        when(transactionService.save(any(Transaction.class))).thenReturn("Transaction saved successfully");

        mockMvc.perform(post("/transaction/")
                        .contentType("application/json")
                        .content("{\"id\":\"" + transaction.getId() + "\", \"amount\":2100}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction saved successfully"));

        verify(transactionService, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testUpdate() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setAmount(3100);
        transaction.setCommerce("Coto");
        transaction.setUser("Juan");
        LocalDate date = LocalDate.parse("2025-01-01");
        transaction.setDate(date);

        when(transactionService.update(any(Transaction.class))).thenReturn(transaction);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        log.info(objectMapper.writeValueAsString(transaction));

        mockMvc.perform(put("/transaction/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transaction.getId().toString()))
                .andExpect(jsonPath("$.amount").value(3100))
                .andExpect(jsonPath("$.commerce").value("Coto"))
                .andExpect(jsonPath("$.user").value("Juan"))
                .andExpect(jsonPath("$.date").value("2025-01-01"));

        verify(transactionService, times(1)).update(any(Transaction.class));
    }
}
