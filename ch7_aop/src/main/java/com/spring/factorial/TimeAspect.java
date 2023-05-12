package com.spring.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
	@Around(value = "execution(* com.spring.factorial..*(..))")
	public Object measure(ProceedingJoinPoint pjp) {
		long start = System.nanoTime();
		Object obj = null;
		try {
			obj = pjp.proceed(); // 주된 관심사 처리하는 부분
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			long end = System.nanoTime();
			System.out.println("걸린 시간 : " + (end - start));
		}
		return obj;
	}
}
