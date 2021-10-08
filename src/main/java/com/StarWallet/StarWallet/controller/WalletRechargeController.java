package com.StarWallet.StarWallet.controller;

import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.entity.WalletRecharge;
import com.StarWallet.StarWallet.model.request.CreateWalletRecharge;
import com.StarWallet.StarWallet.service.WalletRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets/recharge")
public class WalletRechargeController {

    @Autowired
    WalletRechargeService walletRechargeService;

    @PostMapping
    public ResponseEntity<WalletRecharge> rechargeWallet(@RequestBody CreateWalletRecharge createWalletRecharge){
        return new ResponseEntity<>(walletRechargeService.createWalletRecharge(createWalletRecharge), HttpStatus.CREATED);
    }
}
