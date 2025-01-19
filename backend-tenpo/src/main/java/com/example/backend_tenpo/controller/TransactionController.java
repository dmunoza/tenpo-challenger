package com.example.backend_tenpo.controller;


import com.example.backend_tenpo.entity.Transaction;
import com.example.backend_tenpo.excepton.ResourceNotFoundException;
import com.example.backend_tenpo.service.TransactionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/transaction")
public class TransactionController {

        private TransactionInterface transactionService;

        @Autowired
        public TransactionController(TransactionInterface thetransactionService){
            this.transactionService = thetransactionService;
        }

        @GetMapping("/findAll")
        public List<Transaction> findAll(){
            try {
                return transactionService.findAllTransaction();
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener todas las transacciones", e);
            }
        }

        @GetMapping("/findById/{id}")
        public Transaction findById(@RequestParam String id){
            try {
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
        public String delete(@RequestParam String id){
            try {
                return transactionService.delete(id);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException("Transacción no encontrada con id: " + id);
            } catch (Exception e) {
                throw new RuntimeException("Error al eliminar la transacción con id: " + id, e);
            }
        }
}
