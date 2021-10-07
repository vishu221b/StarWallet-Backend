package com.StarWallet.StarWallet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum CurrencyType {
    INR(1),
    USD(2),
    JPN(3);
    private int modeId;
}
