package com.StarWallet.StarWallet.repository;

import com.StarWallet.StarWallet.model.entity.WalletRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRechargeRepository extends JpaRepository<WalletRecharge, Long> {
}
