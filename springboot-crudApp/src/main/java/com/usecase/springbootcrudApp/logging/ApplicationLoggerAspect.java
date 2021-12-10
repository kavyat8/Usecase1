package com.usecase.springbootcrudApp.logging;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private static final Logger LOG = LogManager.getLogger();
		
	@Around("@annotation(ApplicationLogger)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
		LOG.info("Enter: {}",joinPoint.getSignature().getDeclaringTypeName());
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		LOG.info("Exit: {} executed in {}",joinPoint.getSignature().getDeclaringTypeName(),executionTime);
		return proceed;
	}

}
