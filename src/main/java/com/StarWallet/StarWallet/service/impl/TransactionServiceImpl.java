package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.constants.ErrorConstants;
import com.StarWallet.StarWallet.enums.CurrencyType;
import com.StarWallet.StarWallet.enums.PaymentMode;
import com.StarWallet.StarWallet.enums.TransactionType;
import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.exceptions.StarWalletInvalidRequestException;
import com.StarWallet.StarWallet.model.request.CreateNewTransaction;
import com.StarWallet.StarWallet.repository.TransactionRepository;
import com.StarWallet.StarWallet.service.TransactionService;
import com.StarWallet.StarWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    WalletService walletService;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> createTransactions(CreateNewTransaction createNewTransaction) {
        List<Transaction> response = new ArrayList<>();
        Transaction payeeTransaction = new Transaction();
        Transaction receiversTransaction = new Transaction();
        Wallet payeeWallet = walletService.getByWalletId(createNewTransaction.getPayeeWalletId());
        Wallet receiverWallet = walletService.getByWalletId(createNewTransaction.getReceiverWalletId());
        if(null==receiverWallet){
            throw new StarWalletInvalidRequestException(ErrorConstants.RECEIVER_WALLET_CANNOT_BE_EMPTY, System.currentTimeMillis());
        }
        Long amount = createNewTransaction.getAmount();
        long balanceDifference = 0L;
        if(null!=payeeWallet){
            balanceDifference = payeeWallet.getBalanceAmount() - amount;
            if(balanceDifference < 0){
                throw new StarWalletInvalidRequestException(ErrorConstants.INSUFFICIENT_WALLET_BALANCE_ERROR, System.currentTimeMillis());
            }
        }
        for(CurrencyType t: CurrencyType.values()){
            if(t.getModeId() == createNewTransaction.getCurrencyType()){
                payeeTransaction.setCurrencyType(t);
                receiversTransaction.setCurrencyType(t);
            }
        }
        for(PaymentMode p: PaymentMode.values()){
            if(p.getModeId() == createNewTransaction.getPaymentMode()){
                payeeTransaction.setPaymentMode(p);
                receiversTransaction.setPaymentMode(p);
            }
        }
        payeeTransaction.setAmount(amount);
        receiversTransaction.setAmount(amount);

        String transactionContext = UUID.randomUUID().toString();
        payeeTransaction.setTransactionContextId(transactionContext);
        receiversTransaction.setTransactionContextId(transactionContext);

        if(null!=payeeWallet){
            payeeTransaction.setWallet(payeeWallet);
            payeeTransaction.setTransactionType(TransactionType.DEBIT);
            payeeTransaction = transactionRepository.save(payeeTransaction);
        }

        receiversTransaction.setWallet(receiverWallet);
        receiversTransaction.setTransactionType(TransactionType.CREDIT);
        receiversTransaction = transactionRepository.save(receiversTransaction);

        if(null!=payeeWallet){
            walletService.updateWalletBalance(payeeWallet, balanceDifference);
            response.add(payeeTransaction);
        }
        walletService.updateWalletBalance(receiverWallet, receiverWallet.getBalanceAmount() + amount);
        response.add(receiversTransaction);
        return response;
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
