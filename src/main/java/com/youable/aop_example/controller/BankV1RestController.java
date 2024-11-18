package com.youable.aop_example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youable.aop_example.dto.request.BaseRequest;
import com.youable.aop_example.dto.response.BaseResponse;
import com.youable.aop_example.dto.request.RegistAccountRequest;
import com.youable.aop_example.dto.request.TransactionRequest;
import com.youable.aop_example.dto.response.RegistAccountResponse;
import com.youable.aop_example.encryption.AESUtil;
import com.youable.aop_example.model.Transaction;
import com.youable.aop_example.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank")
public class BankV1RestController {
    private final BankService bankService;
    private final ObjectMapper objectMapper;

    @PostMapping("/regist")
    public ResponseEntity<BaseResponse> regist(
            @RequestBody BaseRequest request
    ) throws Exception {
        String plaintText = AESUtil.decrypt(request.getData()); // v
        RegistAccountRequest registAccountRequest = objectMapper.readValue(plaintText, RegistAccountRequest.class); // v
        RegistAccountResponse registAccountResponse = bankService.registAccount(registAccountRequest);
        String dataJson = objectMapper.writeValueAsString(registAccountResponse); // v

        BaseResponse response = BaseResponse.builder()
                .code("00000")
                .msg("success")
                .data(AESUtil.encrypt(dataJson))
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
}
