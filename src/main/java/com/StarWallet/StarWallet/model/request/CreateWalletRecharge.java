package com.StarWallet.StarWallet.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWalletRecharge {
    private Long rechargeAmount;
    private String walletId;
    private int currencyType;
    private int paymentMode;
}
