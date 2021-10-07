package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.request.CreateWallet;
import com.StarWallet.StarWallet.repository.WalletRepository;
import com.StarWallet.StarWallet.service.UserService;
import com.StarWallet.StarWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserService userService;

    @Override
    public Wallet getByWalletId(String walletId) {
        return walletRepository.findByWalletId(walletId);
    }

    @Override
    public List<Wallet> getAllWalletsForUser(long userid) throws Exception {
        return walletRepository.findByUser(userService.findById(userid));
    }

    @Override
    public Wallet createNewWallet(CreateWallet wallet) throws Exception {
        Wallet wallet1 = new Wallet();
        wallet1.setWalletType(wallet.getWalletType());
        wallet1.setWalletId(UUID.randomUUID().toString());
        wallet1.setUser(userService.findById(wallet.getUserId()));
        return walletRepository.save(wallet1);
    }
}
