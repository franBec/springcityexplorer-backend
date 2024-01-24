package dev.pollito.springcityexplorer.aspect;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Pointcut("execution(* com.weatherstack.api.*.*(..))")
  public void weatherstackApiMethodsPointcut() {}

  @Pointcut("execution(public * dev.pollito.springcityexplorer.controller.*Controller.*(..))")
  public void controllerPublicMethodsPointcut() {}

  @Before("weatherstackApiMethodsPointcut()")
  public void logBeforeApiMethods(JoinPoint joinPoint) {
    log.info(
        "["
            + joinPoint.getSignature().toShortString()
            + "] ----> Args: "
            + Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(pointcut = "weatherstackApiMethodsPointcut()", returning = "result")
  public void logAfterReturningApiMethods(JoinPoint joinPoint, Object result) {
    log.info("[" + joinPoint.getSignature().toShortString() + "] <---- Response: " + result);
  }

  @Before("controllerPublicMethodsPointcut()")
  public void logBeforeControllerPublicMethods(JoinPoint joinPoint){
    log.info(
            "["
                    + joinPoint.getSignature().toShortString()
                    + "] ----> Args: "
                    + Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(pointcut = "controllerPublicMethodsPointcut()", returning = "result")
  public void logAfterControllerPublicMethods(JoinPoint joinPoint, Object result) {
    log.info("[" + joinPoint.getSignature().toShortString() + "] <---- Response: " + result);
  }
}
