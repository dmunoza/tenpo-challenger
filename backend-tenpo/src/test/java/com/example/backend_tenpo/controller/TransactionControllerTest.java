package com.example.backend_tenpo.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.backend_tenpo.dto.TransactionDto;
import com.example.backend_tenpo.dto.TransactionUpdateDto;
import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.excepton.CustomExceptionHandler;
import com.example.backend_tenpo.excepton.ResourceNotFoundException;
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
import java.time.LocalDate;
import java.util.Collections;
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
        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(2100);
        transaction.setCommerce("Coto");
        transaction.setUser("Juan");
        LocalDate date = LocalDate.parse("2025-01-01");
        transaction.setDateTransaction(date.atStartOfDay());

        when(transactionService.save(any(TransactionDto.class))).thenReturn("Transaction saved successfully");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mockMvc.perform(post("/transaction/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction saved successfully"));

        verify(transactionService, times(1)).save(any(TransactionDto.class));
    }

    @Test
    public void testUpdate() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setAmount(3100);
        transaction.setCommerce("Coto");
        transaction.setUser("Juan");
        LocalDate date = LocalDate.parse("2025-01-01");
        System.out.println(date.atStartOfDay());
        transaction.setDateTransaction(date.atStartOfDay());

        when(transactionService.update(any(TransactionUpdateDto.class))).thenReturn(transaction);

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
                .andExpect(jsonPath("$.user").value("Juan"));

        verify(transactionService, times(1)).update(any(TransactionUpdateDto.class));
    }
}
