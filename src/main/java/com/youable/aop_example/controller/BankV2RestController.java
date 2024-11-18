package com.youable.aop_example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youable.aop_example.dto.request.BaseRequest;
import com.youable.aop_example.dto.request.RegistAccountRequest;
import com.youable.aop_example.dto.request.TransactionRequest;
import com.youable.aop_example.dto.response.BaseResponse;
import com.youable.aop_example.dto.response.RegistAccountResponse;
import com.youable.aop_example.encryption.AESUtil;
import com.youable.aop_example.encryption.DecryptRequest;
import com.youable.aop_example.encryption.EncryptResponse;
import com.youable.aop_example.model.Transaction;
import com.youable.aop_example.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/bank")
public class BankV2RestController {
    private final BankService bankService;
    private final ObjectMapper objectMapper;

    @DecryptRequest
//    @EncryptResponse
    @PostMapping("/regist")
    public ResponseEntity<BaseResponse> regist(
            @RequestBody BaseRequest request
    ) throws Exception {
        RegistAccountRequest registAccountRequest = objectMapper.readValue(request.getData(), RegistAccountRequest.class); // v
        RegistAccountResponse registAccountResponse = bankService.registAccount(registAccountRequest);
        BaseResponse response = BaseResponse.builder()
                .code("00000")
                .msg("success")
                .data(registAccountResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
