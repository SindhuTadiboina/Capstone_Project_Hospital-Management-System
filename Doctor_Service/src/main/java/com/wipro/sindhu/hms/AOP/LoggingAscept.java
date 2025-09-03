package com.wipro.sindhu.hms.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAscept {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAscept.class);

    // before any controller method in your controllers package
    @Before("execution(* com.wipro.sindhu.hms.controller..*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("ENTER: {}.{}() with args = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    // after returning from controller methods
    @AfterReturning(pointcut = "execution(* com.wipro.sindhu.hms.controller..*(..))", returning = "result")
    public void logAfterController(JoinPoint joinPoint, Object result) {
        log.info("EXIT: {}.{}() returned = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    // log exceptions thrown by controller methods
    @AfterThrowing(pointcut = "execution(* com.wipro.sindhu.hms.controller..*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("EXCEPTION in {}.{}(): {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage(), ex);
    }

}
