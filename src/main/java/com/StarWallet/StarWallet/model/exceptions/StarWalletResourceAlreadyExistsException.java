package com.StarWallet.StarWallet.model.exceptions;

import lombok.Data;

@Data
public class StarWalletResourceAlreadyExistsException extends RuntimeException{

    private long epoch;

    public StarWalletResourceAlreadyExistsException(String message, Long epoch){
        super(message);
        this.epoch=epoch;
    }

}
