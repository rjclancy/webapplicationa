package com.ronanclancy.webapplicationA.aspects;

import com.ronanclancy.webapplicationA.utils.LogStackUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

  @AfterReturning(pointcut = "execution(* com.ronanclancy.webapplicationA.rest*.*.*(..))")
  public void aspectClearStack(JoinPoint joinPoint) {
    LogStackUtils.clearLogStack();
  }
}
