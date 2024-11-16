package com.youable.aop_example.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "owner_name")
    private String ownerName;

    private String password;

    private BigDecimal balance;

    @Builder
    public Account(
            String accountNumber,
            String ownerName,
            String password,
            BigDecimal balance
    ) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.password = password;
        this.balance = balance;
    }

    public void addBalance(BigDecimal amount) {
         balance = balance.add(amount);
    }

    public void subtractBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}
