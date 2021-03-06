package com.StarWallet.StarWallet.service;

import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.model.request.CreateNewTransaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> createTransactions(CreateNewTransaction createNewTransaction);

    Transaction markTransactionInactive(Long transactionId);

    List<Transaction> getAllTransactionsForWallet(int walletId);

}
