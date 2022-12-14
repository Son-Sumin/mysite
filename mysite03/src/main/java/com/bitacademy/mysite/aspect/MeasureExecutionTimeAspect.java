package com.bitacademy.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))") // *..*; 상위 모든 것
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		/* before */
		StopWatch sw = new StopWatch();
		sw.start();
		
		/* PointCut Method 실행 */
		Object result = pjp.proceed();
		
		/* after */
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		System.out.println("[Excution Time][" + taskName +"] " + totalTime + "mills");
		
		return result;
	}
}
