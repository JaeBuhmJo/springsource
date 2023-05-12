package com.spring.factorial;

import org.springframework.stereotype.Component;

@Component("dpfac")
public class dpCalc implements Calculator {

	@Override
	public long factorial(long num) {
		long[] dp = new long[(int)num+1];
		dp[1]=1;
		dp[2]=2;
		for (int i = 2; i <= num; i++) {
			dp[i] = i*dp[i-1];
		}
		return dp[(int)num];
	}

}
