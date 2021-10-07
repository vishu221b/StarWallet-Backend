package com.StarWallet.StarWallet.model.request;

import com.StarWallet.StarWallet.enums.WalletType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWallet {
    private WalletType walletType;
    private long userId;
}
