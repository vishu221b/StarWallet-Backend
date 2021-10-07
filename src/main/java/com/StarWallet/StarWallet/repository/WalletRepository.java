package com.StarWallet.StarWallet.repository;

import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findByUser(User user);

    @Query(value = "select * from wallet w join star_wallet_user u on u.id=w.user_id where u.id in (:userIds)", nativeQuery = true)
    List<Wallet> findByUserIds(@Param("userIds") String userIds);

    Wallet findByWalletId(String walletId);
}
