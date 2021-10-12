package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.constants.ErrorConstants;
import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceAlreadyExistsException;
import com.StarWallet.StarWallet.model.request.CreateWallet;
import com.StarWallet.StarWallet.repository.WalletRepository;
import com.StarWallet.StarWallet.service.UserService;
import com.StarWallet.StarWallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserService userService;

    @Override
    public void updateWalletBalance(Wallet wallet, Long newBalance) {
        wallet.setBalanceAmount(newBalance);
        walletRepository.save(wallet);
    }

    @Override
    public Wallet getByWalletId(String walletId) {
        return walletRepository.findByWalletId(walletId);
    }

    @Override
    public List<Wallet> getAllWalletsForUser(long userid) throws Exception {
        return walletRepository.findByUser(userService.findById(userid));
    }

    private List<Wallet> getWalletsForUserId(String userId){
        log.info("Received user ids to check wallets for:" + userId);
        List<Wallet> wallets = walletRepository.findByUserIds(userId);
        log.info("Fetched wallets from repository: " + wallets);
        return wallets;
    }

    @Override
    public Wallet createNewWallet(CreateWallet wallet) throws Exception {
        List<Wallet> walletList = getWalletsForUserId(String.valueOf(wallet.getUserId()));
        if(null!=walletList || walletList.size() > 0){
            walletList
                    .forEach(
                            w -> {
                                if(w.getWalletType().equals(wallet.getWalletType())){
                                    throw new StarWalletResourceAlreadyExistsException(
                                            String.format(ErrorConstants.WALLET_WITH_TYPE_ALREADY_EXISTS_ERROR, wallet.getWalletType().name()),
                                            System.currentTimeMillis());
                                }
                            });
        }
        User user = userService.findById(wallet.getUserId());
        Wallet wallet1 = new Wallet();
        wallet1.setWalletType(wallet.getWalletType());
        wallet1.setWalletId(UUID.randomUUID().toString());
        wallet1.setUser(user);
        return walletRepository.save(wallet1);
    }
}
