package com.StarWallet.StarWallet.model.entity;

import com.StarWallet.StarWallet.enums.WalletType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @OneToMany(targetEntity = Transaction.class, mappedBy = "payerWallet")
    List<Transaction> paid;

    @JsonIgnore
    @OneToMany(targetEntity = Transaction.class, mappedBy = "receiverWallet")
    List<Transaction> received;

    @Column(name="wallet_type")
    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    @Column(name = "balance_amount")
    private Long balanceAmount;

    @JsonIgnore
    @OneToMany(mappedBy = "wallet")
    private Set<WalletRecharge> walletRecharge;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.setBalanceAmount(0L);
    }

}
