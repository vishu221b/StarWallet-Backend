package com.StarWallet.StarWallet.scheduler;

import com.StarWallet.StarWallet.constants.RegexConstants;
import com.StarWallet.StarWallet.enums.TransactionStatus;
import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.repository.TransactionRepository;
import com.StarWallet.StarWallet.repository.WalletRechargeRepository;
import com.StarWallet.StarWallet.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Component
public class TransactionScheduler {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletRechargeRepository walletRechargeRepository;

    @Scheduled(cron = RegexConstants.TRANSACTION_CRON_EVERY_HOUR_TOP)
    @Transactional
    public void completeAllTransactions(){
        log.info("Starting transactions scheduler.");
        Collection<Transaction> incompleteTransactions = transactionRepository.findIncompleteActiveTransactions();
        Collection<Transaction> newTransactionsToSave = new ArrayList<>();
        if(null!=incompleteTransactions && incompleteTransactions.size() > 0){
            log.info(String.format("Modifying %s incomplete transactions.", incompleteTransactions.size()));
            incompleteTransactions.forEach(
                    txn -> {
                        Transaction transaction = new Transaction();
                        transaction.setTransactionType(txn.getTransactionType());
                        transaction.setParentTransactionId(txn);
                        transaction.setTransactionContextId(txn.getTransactionContextId());
                        transaction.setAmount(txn.getAmount());
                        transaction.setPaymentMode(txn.getPaymentMode());
                        transaction.setCurrencyType(txn.getCurrencyType());
                        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
                        transaction.setWallet(txn.getWallet());
                        newTransactionsToSave.add(transaction);
                    });
            transactionRepository.saveAll(newTransactionsToSave);
            incompleteTransactions.forEach(x->x.setIsActive(Boolean.FALSE));
            log.info("Completed updating transactions.");
        }
        log.info("Stopping transactions scheduler.");
    }
}
