package com.youable.aop_example.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youable.aop_example.dto.request.BaseRequest;
import com.youable.aop_example.dto.request.RegistAccountRequest;
import com.youable.aop_example.dto.response.BaseResponse;
import com.youable.aop_example.encryption.AESUtil;
import com.youable.aop_example.encryption.DecryptRequest;
import com.youable.aop_example.encryption.EncryptResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class EncryptionAspect {
    private final ObjectMapper objectMapper;

    @Around("@annotation(DecryptRequest)")
    public Object decryptRequest(ProceedingJoinPoint joinPoint, DecryptRequest decryptRequest) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args.length > 0 && args[0] instanceof BaseRequest request) {
            String decryptedJson = AESUtil.decrypt(request.getData());
            request.setData(decryptedJson);
        }
        return joinPoint.proceed(args);
    }

    @Around("@annotation(EncryptResponse)")
    public Object encryptResponse(ProceedingJoinPoint joinPoint, EncryptResponse encryptResponse) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof ResponseEntity<?> responseEntity) {
            Object body = responseEntity.getBody();

            if (body instanceof BaseResponse baseResponse) {
                String dataJson = objectMapper.writeValueAsString(baseResponse.getData());
                String encryptedData = AESUtil.encrypt(dataJson);

                BaseResponse encryptedResponse = BaseResponse.builder()
                        .code(baseResponse.getCode())
                        .msg(baseResponse.getMsg())
                        .data(encryptedData)
                        .build();

                return ResponseEntity.ok(encryptedResponse);
            }
        }

        return result;
    }
}
