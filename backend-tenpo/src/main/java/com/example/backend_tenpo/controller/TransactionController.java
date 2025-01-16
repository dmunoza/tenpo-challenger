package com.example.backend_tenpo.controller;


import com.example.backend_tenpo.service.TransactionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/transaction")
public class TransactionController {

        private TransactionInterface transactionService;

        @Autowired
        public TransactionController(TransactionInterface thetransactionService){
            this.transactionService = thetransactionService;
        }

        @RequestMapping("/get")
        public String getTransaction(){
            return transactionService.getTransaction();
        }

        @RequestMapping("/post")
        public String postTransaction(){
            return transactionService.postTransaction();
        }

        @RequestMapping("/put")
        public String putTransaction(){
            return transactionService.putTransaction();
        }

        @RequestMapping("/delete")
        public String deleteTransaction(){
            return transactionService.deleteTransaction();
        }
}
