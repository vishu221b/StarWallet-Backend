package com.StarWallet.StarWallet.service;

import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.request.CreateWallet;

import java.util.List;

public interface WalletService {

    public void updateWalletBalance(Wallet wallet, Long newBalance);

    public Wallet getByWalletId(String walletId);

    public List<Wallet> getAllWalletsForUser(long userid) throws Exception;

    public Wallet createNewWallet(CreateWallet wallet) throws Exception;
}
