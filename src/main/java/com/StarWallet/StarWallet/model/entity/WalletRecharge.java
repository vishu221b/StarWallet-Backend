package com.StarWallet.StarWallet.model.entity;

import com.StarWallet.StarWallet.enums.RechargeStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "wallet_recharge_details")
@NoArgsConstructor
@AllArgsConstructor
public class WalletRecharge extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private Date rechargeDoneAt;

    @Enumerated(EnumType.STRING)
    private RechargeStatus rechargeStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

}
