package app.messages.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

@Aspect
@Component
public class SecurityChecker {
  private static final Logger logger = LoggerFactory.getLogger(SecurityChecker.class);

  @Pointcut("@annotation(SecurityCheck)")
  public void checkMethodSecurity() { }
  
  @Around("checkMethodSecurity()")
  public Object checkSecurity (ProceedingJoinPoint joinPoint) throws Throwable {
    logger.debug("Checking method security...");

    Object result = joinPoint.proceed();
    return result;
  }
}