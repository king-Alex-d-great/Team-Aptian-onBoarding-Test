package com.girlcoder.technicaltest.respository;

import com.girlcoder.technicaltest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Boolean existsByTransactionReference(String transactionReference);
}
