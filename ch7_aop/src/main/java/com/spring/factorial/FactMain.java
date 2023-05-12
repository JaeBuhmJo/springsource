package com.spring.factorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// AOP : 횡단관심사 프레임워크가 처리

// 주된관심사 : factorial 구하기
// 횡단관심사 : 누가 더 빠를까? 시간이 얼마나 걸리는지

public class FactMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		Calculator cal = (Calculator) ctx.getBean("rec");
		System.out.println("====================");
		System.out.println("rec 10! = "+cal.factorial(10));
		
		cal = (Calculator) ctx.getBean("forc");
		System.out.println("====================");
		System.out.println("forc 10! = "+cal.factorial(10));
		
		cal = (Calculator) ctx.getBean("dpfac");
		System.out.println("====================");
		System.out.println("dpfac 10! = "+cal.factorial(10));
	}

}
