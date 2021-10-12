package com.StarWallet.StarWallet.model.entity;

import com.StarWallet.StarWallet.enums.CurrencyType;
import com.StarWallet.StarWallet.enums.PaymentMode;
import com.StarWallet.StarWallet.enums.TransactionStatus;
import com.StarWallet.StarWallet.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @JsonIgnore
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
    @OneToMany(mappedBy = "parentTransactionId")
    private List<Transaction> childTransactions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_transaction_id", referencedColumnName = "id")
    private Transaction parentTransactionId;

    @Column(name = "transaction_context_id")
    private String transactionContextId;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        if(null==this.getTransactionStatus()){
            this.setTransactionStatus(TransactionStatus.INITIATED);
        }
    }

}
