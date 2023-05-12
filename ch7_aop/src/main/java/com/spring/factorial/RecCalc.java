package com.spring.factorial;

import org.springframework.stereotype.Component;

@Component("rec")
public class RecCalc implements Calculator {

	@Override
	public long factorial(long num) {
		if(num==1) {
			return 1;
		}
		return num*factorial(num-1);
	}

}
