package com.youable.aop_example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youable.aop_example.dto.request.TransactionRequest;
import com.youable.aop_example.dto.request.RegistAccountRequest;
import com.youable.aop_example.dto.response.BaseResponse;
import com.youable.aop_example.dto.response.RegistAccountResponse;
import com.youable.aop_example.encryption.AESUtil;
import com.youable.aop_example.model.Transaction;
import com.youable.aop_example.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/bank")
public class BankV0RestController {
    private final BankService bankService;

    @PostMapping("/regist")
    public ResponseEntity<BaseResponse> regist(
            @RequestBody RegistAccountRequest request
    ) {
        RegistAccountResponse registAccountResponse = bankService.registAccount(request);
        BaseResponse response = BaseResponse.builder()
                .code("00000")
                .msg("success")
                .data(registAccountResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(
            @RequestBody TransactionRequest request
    ) {
        // 예금
        Transaction result = bankService.deposit(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Transaction> withdrawal(
            @RequestBody TransactionRequest request
    ) {
        // 출금
        Transaction result = bankService.withdrawal(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/encrypt/test")
    public ResponseEntity<String> test() throws Exception {
        RegistAccountRequest request = RegistAccountRequest.builder()
                .ownerName("user1")
                .password("2017")
                .amount(BigDecimal.valueOf(10000.0))
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(request);
        String encryptString = AESUtil.encrypt(jsonString);
//        String plainTest = AESUtil.decrypt(encryptString);
        return ResponseEntity.ok().body(encryptString);
    }

    @PostMapping("/decrypt/test")
    public ResponseEntity<String> decrypt(
            @RequestParam(name = "data") String encryptString
    ) throws Exception {
        String plainTest = AESUtil.decrypt(encryptString);
        return ResponseEntity.ok().body(plainTest);
    }
}
