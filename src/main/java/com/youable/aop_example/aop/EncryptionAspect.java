package com.youable.aop_example.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youable.aop_example.encryption.AESUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class EncryptionAspect {
    private final ObjectMapper objectMapper;

    @Around("@annotation(DecryptRequest)")
    public Object decryptRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof String encryptString) {
            args[0] = AESUtil.decrypt(encryptString);
        }
        return joinPoint.proceed(args);
    }

    @Around("@annotation(EncryptResponse)")
    public Object encryptResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        String jsonString = objectMapper.writeValueAsString(result);
        return AESUtil.encrypt(jsonString);
    }
}
