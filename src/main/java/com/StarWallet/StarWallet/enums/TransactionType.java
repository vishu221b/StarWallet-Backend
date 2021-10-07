package com.StarWallet.StarWallet.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    CREDIT(1),
    DEBIT(2);

    private int id;
    
    TransactionType(int txnType){
        this.id=txnType;
    }

    public static TransactionType getById(int id){
        for(TransactionType t: TransactionType.values()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
}
