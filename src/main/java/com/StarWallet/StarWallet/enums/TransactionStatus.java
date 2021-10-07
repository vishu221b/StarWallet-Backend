package com.StarWallet.StarWallet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransactionStatus {
    COMPLETED(1),
    PENDING(2),
    REFUNDED(3),
    ABORTED(4),
    FAILED(5),
    INITIATED(6);

    private int statusId;
}
