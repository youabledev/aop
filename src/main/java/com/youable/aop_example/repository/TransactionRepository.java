package com.youable.aop_example.repository;

import com.youable.aop_example.model.Account;
import com.youable.aop_example.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountOrderByTransactionTimeDesc(Account account);

}
