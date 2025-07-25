package com.ttb.wongnok.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogHandler {
    @Pointcut("execution(* com.ttb.wongnok.service..*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        // Log before executing any service method
        System.out.println("Before executing: " + joinPoint.getSignature().getName()); 
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowingServiceMethods(JoinPoint joinPoint, Throwable ex) {
        // Log after an exception is thrown in any service method
        System.err.println("Exception in: " + joinPoint.getSignature().getName() + " - " + ex.getMessage());
    }   
}
