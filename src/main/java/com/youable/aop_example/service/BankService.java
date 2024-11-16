package com.youable.aop_example.service;

import com.youable.aop_example.common.UniqueValueGenerator;
import com.youable.aop_example.dto.TransactionRequest;
import com.youable.aop_example.dto.RegistAccountRequest;
import com.youable.aop_example.model.Account;
import com.youable.aop_example.model.Transaction;
import com.youable.aop_example.repository.AccountRepository;
import com.youable.aop_example.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public String registAccount(
            RegistAccountRequest request
    ) {
        String accountNumber = UniqueValueGenerator.getAccountNumber();
        Account account = request.of(accountNumber, request);
        accountRepository.save(account);
        return accountNumber;
    }

    public Transaction deposit(TransactionRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("account not found"));
        account.addBalance(request.getAmount());
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(request.getAmount())
                .transactionType("DEPOSIT")
                .build();

        transactionRepository.save(transaction);
        accountRepository.save(account);
        return transaction;
    }

    public Transaction withdrawal(TransactionRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("account not found"));
        account.subtractBalance(request.getAmount());
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(request.getAmount())
                .transactionType("WITHDRAWAL")
                .build();

        transactionRepository.save(transaction);
        accountRepository.save(account);
        return transaction;
    }
}
