package com.spring.dependency;

public class TvMain {
	String str = "String";

	public static void main(String[] args) {
//		TV tv = new SamsungTV(new SonySpeaker());
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		SamsungTV tv = new SamsungTV();
		
		tv.setSpeaker(new SonySpeaker());
		
		
//		TvMain obj = new TvMain();
//		obj.test();

	}

//	public void test() {
//		System.out.println(str);
//		System.out.println(str.length());
//	}
}
