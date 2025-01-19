package com.example.backend_tenpo.controller;


import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.excepton.ResourceNotFoundException;
import com.example.backend_tenpo.service.TransactionInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/transaction")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private TransactionInterface transactionService;

        @Autowired
        public TransactionController(TransactionInterface thetransactionService){
            this.transactionService = thetransactionService;
        }

        @GetMapping("/findAll")
        public List<Transaction> findAll(){
            try {
                log.info("Buscando transacciónes");
                return transactionService.findAllTransaction();
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener todas las transacciones", e);
            }
        }

        @GetMapping("/findById")
        public Transaction findById(@RequestHeader UUID id){
            try {
                log.info("Buscando transacción con id: " + id);
                return transactionService.findTransaction(id);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException("Transacción no encontrada con id: " + id);
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener la transacción con id: " + id, e);
            }
        }

        @PostMapping("/")
        public String save(@RequestBody Transaction transaction){
            try {
                return transactionService.save(transaction);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la transacción", e);
            }
        }

        @PutMapping("/")
        public Transaction update(@RequestBody Transaction transaction){
            try {
                return transactionService.update(transaction);
            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar la transacción", e);
            }
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable UUID id){
            try {
                return transactionService.delete(id);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException("Transacción no encontrada con id: " + id);
            } catch (Exception e) {
                throw new RuntimeException("Error al eliminar la transacción con id: " + id, e);
            }
        }
}
