package com.henry.xi.spring.boot;

import com.henry.xi.spring.boot.annotation.RequestLimit;
import com.henry.xi.spring.boot.exception.SimpleException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SimpleAOP {
    @Before("within(@com.henry.xi.spring.boot.service *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws SimpleException {

        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                System.out.println(args.toString());
            }
        } catch (Exception e) {

        }
    }
}