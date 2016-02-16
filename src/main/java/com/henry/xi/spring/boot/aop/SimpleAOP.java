package com.henry.xi.spring.boot.aop;

import com.henry.xi.spring.boot.annotation.RequestLimit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleAOP {
    @Before("within(com.henry.xi.spring.boot.service..*) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) {
        //we just print out the args here.
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i].toString());
        }
        System.out.println(limit.count());
    }
}