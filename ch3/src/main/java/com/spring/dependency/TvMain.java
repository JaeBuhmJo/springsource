package com.spring.dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		//TV tv = (TV) ctx.getBean("samsungTV"); // Component => 클래스 앞자리만 소문자로 바꿔서 id를 지정
		TV tv = (TV) ctx.getBean("tv"); // Component("tv") => 자동 생성 id를 지정한 문자열로 지정 
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
