package com.StarWallet.StarWallet.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewTransaction {
    private String payeeWalletId;
    private String receiverWalletId;
    private int paymentMode;
    private int transactionType;
    private long amount;
    private int currencyType;
}
