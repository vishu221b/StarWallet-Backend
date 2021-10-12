package com.StarWallet.StarWallet.repository;

import com.StarWallet.StarWallet.model.entity.WalletRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRechargeRepository extends JpaRepository<WalletRecharge, Long> {

    @Query(value = "select r.* from wallet_recharge_details r where r.is_active=1 and r.recharge_status in ('PENDING')",
    nativeQuery = true)
    List<WalletRecharge> findPendingRecharges();
}
