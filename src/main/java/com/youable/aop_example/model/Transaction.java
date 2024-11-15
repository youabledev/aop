package com.youable.aop_example.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private BigDecimal amount;

    @Column(name = "transaction_type")
    private String transactionType; // DEPOSIT, WITHDRAWAL

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    private String description;
}
