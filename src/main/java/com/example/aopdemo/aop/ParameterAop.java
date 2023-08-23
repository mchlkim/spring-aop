package com.example.aopdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.aopdemo.controller..*.*(..))")
    private void cut() {
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("Method : " + method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            System.out.println("type : " + obj.getClass().getSimpleName() + " value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturing(JoinPoint joinPoint, Object returnObj) {
        System.out.println("Return Object");
        System.out.println(returnObj);
    }
}
