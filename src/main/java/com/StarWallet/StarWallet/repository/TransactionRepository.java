package com.StarWallet.StarWallet.repository;

import com.StarWallet.StarWallet.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(
            value = "select t.* from transactions t where t.is_active=1 and t.transaction_status not in ('COMPLETED')",
            nativeQuery = true
    )
    List<Transaction> findIncompleteActiveTransactions();
}
