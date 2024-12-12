package com.youable.aop_example.interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestBodyWrappingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            ReadableRequestBodyWrapper wrapper = new ReadableRequestBodyWrapper((HttpServletRequest) request);
            wrapper.setAttribute("requestBody", wrapper.getRequestBody());
            filterChain.doFilter(wrapper, response);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
        }
    }
}
