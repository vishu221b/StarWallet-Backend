package com.StarWallet.StarWallet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RechargeStatus {
    SUCCESSFUL(1),
    FAILURE(2),
    PENDING(3);

    private int id;

    public static RechargeStatus getStatusById(int id){
        for(RechargeStatus r: RechargeStatus.values()){
            if(r.getId()==id){
                return r;
            }
        }
        return null;
    }

}
