package com.youable.aop_example.dto.request;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class TransactionRequest {
    private String accountNumber;
    private String password;
    private String ownerName;
    private BigDecimal amount;
}
