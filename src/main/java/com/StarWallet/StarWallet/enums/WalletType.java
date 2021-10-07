package com.StarWallet.StarWallet.enums;

import lombok.Getter;

@Getter
public enum WalletType {
    PERSONAL(1),
    BUSINESS(2);

    private int id;

    WalletType(int id){
        this.id=id;
    }

    public static WalletType getById(int id){
        for(WalletType w: WalletType.values()){
            if(w.getId()==id){
                return w;
            }
        }
        return null;
    }
}
