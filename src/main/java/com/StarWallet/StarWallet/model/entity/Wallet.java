package com.StarWallet.StarWallet.model.entity;

import com.StarWallet.StarWallet.enums.WalletType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "wallet_id", unique = true)
    private String walletId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @JsonIgnore
    @OneToMany(targetEntity = Transaction.class, mappedBy = "wallet")
    List<Transaction> transactions;

    @Column(name="wallet_type")
    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    @Column(name = "balance_amount")
    private Long balanceAmount;

    @JsonIgnore
    @OneToMany(mappedBy = "wallet")
    private List<WalletRecharge> walletRecharge;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.setBalanceAmount(0L);
    }

}
