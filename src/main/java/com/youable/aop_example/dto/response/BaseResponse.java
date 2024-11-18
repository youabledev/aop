package com.youable.aop_example.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseResponse {
    private String code;
    private String msg;
    private Object data;
}
