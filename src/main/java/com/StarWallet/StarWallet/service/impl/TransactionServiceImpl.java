package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.enums.CurrencyType;
import com.StarWallet.StarWallet.enums.PaymentMode;
import com.StarWallet.StarWallet.enums.TransactionType;
import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.model.request.CreateNewTransaction;
import com.StarWallet.StarWallet.repository.TransactionRepository;
import com.StarWallet.StarWallet.service.TransactionService;
import com.StarWallet.StarWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    WalletService walletService;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(CreateNewTransaction createNewTransaction) {
        Transaction transaction = new Transaction();
        for(TransactionType t: TransactionType.values()){
            if(t.getId() == createNewTransaction.getTransactionType()){
                transaction.setTransactionType(t);
            }
        }
        for(CurrencyType t: CurrencyType.values()){
            if(t.getModeId() == createNewTransaction.getCurrencyType()){
                transaction.setCurrencyType(t);
            }
        }
        for(PaymentMode p: PaymentMode.values()){
            if(p.getModeId() == createNewTransaction.getPaymentMode()){
                transaction.setPaymentMode(p);
            }
        }
        transaction.setPayerWallet(walletService.getByWalletId(createNewTransaction.getPayeeWalletId()));
        transaction.setReceiverWallet(walletService.getByWalletId(createNewTransaction.getReceiverWalletId()));
        transaction.setAmount(createNewTransaction.getAmount());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction markTransactionInactive(Long transactionId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsForWallet(int walletId) {
        return null;
    }
}
