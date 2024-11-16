package com.youable.aop_example.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionRequest {
    private String accountNumber;
    private String password;
    private String ownerName;
    private BigDecimal amount;
}
