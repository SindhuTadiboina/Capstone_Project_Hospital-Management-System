package com.wipro.sindhu.hms.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Logs every method in any class under "controller" package
    @Before("execution(* com.example.demo.controller..*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        log.info("===== Logging Before Method Call =====");
        log.info("Class: {}", joinPoint.getTarget().getClass().getSimpleName());
        log.info("Method: {}", joinPoint.getSignature().getName());
        log.info("======================================");
    }

}
