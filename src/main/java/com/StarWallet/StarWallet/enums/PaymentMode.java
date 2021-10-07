package com.StarWallet.StarWallet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PaymentMode {
    CREDIT_CARD(1),
    DEBIT_CARD(2),
    NET_BANKING(3),
    UPI(4),
    THIRD_PARTY_WALLET(5),
    STAR_WALLET(6);

    private int modeId;
}
