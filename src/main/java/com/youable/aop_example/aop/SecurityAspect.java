package com.youable.aop_example.aop;

import com.youable.aop_example.dto.TransactionRequest;
import com.youable.aop_example.model.Account;
import com.youable.aop_example.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityAspect {
    private final AccountRepository accountRepository;
    @Before("execution(* com.youable.aop_example.service.BankService.withdrawal(..))")
    public void checkSecurity(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        TransactionRequest transaction = (TransactionRequest) args[0];
       log.warn("Large amount withdrawal detected!");
       Account account = accountRepository.findByAccountNumberAndOwnerNameAndPassword(transaction.getAccountNumber(), transaction.getOwnerName(), transaction.getPassword())
               .orElseThrow(() -> new RuntimeException("fail authorization"));

    }
}