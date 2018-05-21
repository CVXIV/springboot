package com.example.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Aspect
@Component
public class TimeAspect {
    @Pointcut(""+" execution(* com.example.springboot.controller.*.*(..))")
    private void allMethod(){}

    @Before("allMethod()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(joinPoint.getArgs()));
    }

    @After("allMethod()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("【后置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(joinPoint.getArgs()));
    }
}
