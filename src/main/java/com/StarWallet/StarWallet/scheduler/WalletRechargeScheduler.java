package com.StarWallet.StarWallet.scheduler;

import com.StarWallet.StarWallet.constants.RegexConstants;
import com.StarWallet.StarWallet.enums.RechargeStatus;
import com.StarWallet.StarWallet.enums.TransactionStatus;
import com.StarWallet.StarWallet.model.entity.Transaction;
import com.StarWallet.StarWallet.model.entity.WalletRecharge;
import com.StarWallet.StarWallet.repository.TransactionRepository;
import com.StarWallet.StarWallet.repository.WalletRechargeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Slf4j
@Component
public class WalletRechargeScheduler {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRechargeRepository walletRechargeRepository;

    @Scheduled(cron = RegexConstants.WALLET_RECHARGE_CRON_EVERY_HOUR_FIRST)
    @Transactional
    public void completePendingRecharges(){
        log.info("Starting Complete pending recharge scheduler");
        Collection<WalletRecharge> walletRecharges = walletRechargeRepository.findPendingRecharges();
        if(null!=walletRecharges && walletRecharges.size()>0){
            log.info(String.format("Updating %s wallet recharges.", walletRecharges.size()));
            walletRecharges.forEach(wr -> {
                Collection<Transaction> transactions = transactionRepository.findByParentTransactionId(wr.getTransaction());
                if(transactions.size() > 0 &&
                        transactions
                                .stream()
                                .anyMatch(x -> x.getTransactionStatus().equals(TransactionStatus.COMPLETED))){
                    wr.setRechargeStatus(RechargeStatus.SUCCESSFUL);
                }
            });
            log.info(String.format("Updated %s wallet recharges.", walletRecharges.size()));
        }
        log.info("Stopping Complete pending recharge scheduler");
    }

}
