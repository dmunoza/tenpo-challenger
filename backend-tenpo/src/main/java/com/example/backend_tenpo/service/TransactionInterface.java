package com.example.backend_tenpo.service;

import com.example.backend_tenpo.dto.TransactionDto;
import com.example.backend_tenpo.dto.TransactionUpdateDto;
import com.example.backend_tenpo.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionInterface {
    List<Transaction> findAllTransaction();
    Transaction findTransaction(UUID id);
    String save(TransactionDto transaction);
    Transaction update(TransactionUpdateDto transaction);
    String delete(UUID id);
}
