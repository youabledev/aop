package com.youable.aop_example.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.youable.aop_example.model.Account;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistAccountRequest {
    private String ownerName;
    private String password;
    private BigDecimal amount;

    public Account of(String accountNumber, RegistAccountRequest request) {
        return Account.builder()
                .accountNumber(accountNumber)
                .ownerName(request.ownerName)
                .password(request.password)
                .balance(request.amount)
                .build();
    }
}
