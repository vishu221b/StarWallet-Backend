package com.StarWallet.StarWallet.service;

import com.StarWallet.StarWallet.model.entity.WalletRecharge;
import com.StarWallet.StarWallet.model.request.CreateWalletRecharge;

public interface WalletRechargeService {
    WalletRecharge createWalletRecharge(CreateWalletRecharge createWalletRecharge);
}
