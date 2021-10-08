package com.StarWallet.StarWallet.controller;

import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.model.request.CreateNewTransaction;
import com.StarWallet.StarWallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public List<Transaction> createTransaction(@RequestBody CreateNewTransaction createNewTransaction){
        return transactionService.createTransactions(createNewTransaction);
    }
}
