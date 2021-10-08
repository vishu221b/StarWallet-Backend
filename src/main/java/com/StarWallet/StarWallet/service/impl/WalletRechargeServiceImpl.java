package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.entity.WalletRecharge;
import com.StarWallet.StarWallet.model.request.CreateNewTransaction;
import com.StarWallet.StarWallet.model.request.CreateWalletRecharge;
import com.StarWallet.StarWallet.repository.WalletRechargeRepository;
import com.StarWallet.StarWallet.service.TransactionService;
import com.StarWallet.StarWallet.service.WalletRechargeService;
import com.StarWallet.StarWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletRechargeServiceImpl implements WalletRechargeService {

    @Autowired
    WalletRechargeRepository walletRechargeRepository;

    @Autowired
    WalletService walletService;

    @Autowired
    TransactionService transactionService;

    @Override
    public WalletRecharge createWalletRecharge(CreateWalletRecharge createWalletRecharge) {
        WalletRecharge walletRecharge = new WalletRecharge();
        Wallet wallet = walletService.getByWalletId(createWalletRecharge.getWalletId());
        List<Transaction> rechargeTransaction = transactionService.createTransactions(prepareCreateTransactionRequest(createWalletRecharge));
        walletRecharge.setWallet(wallet);
        walletRecharge.setTransaction(rechargeTransaction.get(0));
        walletRecharge.setRechargeDoneAt(rechargeTransaction.get(0).getCreatedAt());
        walletRechargeRepository.save(walletRecharge);
        return walletRecharge;
    }

    private CreateNewTransaction prepareCreateTransactionRequest(CreateWalletRecharge createWalletRecharge){
        CreateNewTransaction createNewTransaction = new CreateNewTransaction();
        createNewTransaction.setAmount(createWalletRecharge.getRechargeAmount());
        createNewTransaction.setCurrencyType(createWalletRecharge.getCurrencyType());
        createNewTransaction.setPaymentMode(createWalletRecharge.getPaymentMode());
        createNewTransaction.setReceiverWalletId(createWalletRecharge.getWalletId());
        return createNewTransaction;
    }
}
