package com.spring.basic;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean msg = new MessageBeanEn();
		msg.sayHello("홍길동");
		new MessageBeanKo().sayHello("성춘향");
	}

}
