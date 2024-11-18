package com.youable.aop_example.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegistAccountResponse {
    private String accountNumber;

    public RegistAccountResponse(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
