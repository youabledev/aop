package com.youable.aop_example.controller;

import com.youable.aop_example.dto.TransactionRequest;
import com.youable.aop_example.dto.RegistAccountRequest;
import com.youable.aop_example.model.Transaction;
import com.youable.aop_example.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/bank")
public class BankController {
    private final BankService bankService;

    @PostMapping("/regist")
    public ResponseEntity<String> regist(
            @RequestBody RegistAccountRequest request
    ) {
        String accountNumber = bankService.registAccount(request);
        return ResponseEntity.ok(accountNumber);
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
