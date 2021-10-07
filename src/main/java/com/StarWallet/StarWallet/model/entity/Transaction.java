package com.StarWallet.StarWallet.model.entity;

import com.StarWallet.StarWallet.enums.CurrencyType;
import com.StarWallet.StarWallet.enums.PaymentMode;
import com.StarWallet.StarWallet.enums.TransactionStatus;
import com.StarWallet.StarWallet.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = Wallet.class)
    private Wallet payerWallet;

    @ManyToOne(targetEntity = Wallet.class)
    private Wallet receiverWallet;

    @OneToOne(mappedBy = "transaction")
    private WalletRecharge walletRechargeDetails;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "parentTransaction")
    private List<Transaction> transactions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_transaction_id", referencedColumnName = "id")
    private Transaction parentTransactionId;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.setTransactionStatus(TransactionStatus.INITIATED);
    }

}
