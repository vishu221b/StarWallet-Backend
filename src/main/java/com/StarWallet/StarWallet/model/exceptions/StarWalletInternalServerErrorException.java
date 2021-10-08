package com.StarWallet.StarWallet.model.exceptions;

import lombok.Data;

@Data
public class StarWalletInternalServerErrorException extends RuntimeException{

    private long epoch;

    public StarWalletInternalServerErrorException(String message, Long epoch){
        super(message);
        this.epoch=epoch;
    }

}
