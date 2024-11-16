package com.youable.aop_example.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
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

    @CreatedDate
    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Builder
    public Transaction(
            Account account,
            BigDecimal amount,
            String transactionType
    ) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
    }
}
