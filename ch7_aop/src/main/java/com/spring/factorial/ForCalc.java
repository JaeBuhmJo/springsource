package com.spring.factorial;

import org.springframework.stereotype.Component;

@Component("forc")
public class ForCalc implements Calculator {

	@Override
	public long factorial(long num) {
		long fac = 1;
		for (int i = 1; i <= num; i++) {
			fac*=i;
		}
		return fac;
	}
}
