package com.StarWallet.StarWallet.model.exceptions;

import lombok.Data;

@Data
public class StarWalletInvalidRequestException extends RuntimeException{

    private long epoch;

    public StarWalletInvalidRequestException(String message, Long epoch){
        super(message);
        this.epoch=epoch;
    }

}
