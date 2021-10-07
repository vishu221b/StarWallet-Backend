package com.StarWallet.StarWallet.model.exceptions;

import lombok.Data;
import lombok.Getter;

@Data
public class StarWalletResourceNotFoundException extends RuntimeException{

    private long epoch;

    public StarWalletResourceNotFoundException(String message, long epoch){
        super(message);
        this.epoch=epoch;
    }

}
