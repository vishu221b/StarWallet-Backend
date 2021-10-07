package com.StarWallet.StarWallet.controller;

import com.StarWallet.StarWallet.constants.ErrorConstants;
import com.StarWallet.StarWallet.enums.WalletType;
import com.StarWallet.StarWallet.model.entity.Wallet;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceAlreadyExistsException;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceNotFoundException;
import com.StarWallet.StarWallet.model.request.CreateWallet;
import com.StarWallet.StarWallet.service.UserService;
import com.StarWallet.StarWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public List<Wallet> getAllWallets(@PathVariable int userId){
        try {
            return walletService.getAllWalletsForUser(userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new StarWalletResourceNotFoundException(ErrorConstants.WALLET_NOT_FOUND_ERROR, new Date().getTime());
        }
    }

    @PostMapping("/{walletType}/create/")
    public Wallet createWallet(@RequestBody CreateWallet wallet, @PathVariable int walletType){
        WalletType walletType1 = WalletType.getById(walletType);
        if(null!=walletType1){
            wallet.setWalletType(walletType1);
        }else{
            throw new StarWalletResourceNotFoundException(ErrorConstants.INVALID_WALLET_TYPE_ERROR, new Date().getTime());
        }
        try {
            return walletService.createNewWallet(wallet);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            throw new StarWalletResourceAlreadyExistsException(ErrorConstants.USER_ALREADY_EXISTS_ERROR, new Date().getTime());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
