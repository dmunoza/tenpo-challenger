package com.example.backend_tenpo.controller;


import com.example.backend_tenpo.entity.Transaction;
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
            return transactionService.findAllTransaction();
        }

        @GetMapping("/findById/{id}")
        public Transaction findById(@RequestParam String id){
            return transactionService.findTransaction(id);
        }

        @PostMapping("/")
        public String save(@RequestBody Transaction transaction){
            return transactionService.save(transaction);
        }

        @PutMapping("/")
        public Transaction update(@RequestBody Transaction transaction){
            return transactionService.update(transaction);
        }

        @DeleteMapping("/{id}")
        public String delete(@RequestParam String id){
            return transactionService.delete(id);
        }
}
