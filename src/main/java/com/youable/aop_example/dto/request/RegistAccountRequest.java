package com.youable.aop_example.dto.request;

import com.youable.aop_example.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
