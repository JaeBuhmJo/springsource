package com.spring.basic;

public class TvMain {
	public static void main(String[] args) {
//		LgTV lgTV = new LgTV();
//		lgTV.turnOn();
//		lgTV.soundUp();
//		lgTV.soundDown();
//		lgTV.turnOff();
		
		TV samsungTV = new LgTV();
		samsungTV.powerOn();
		samsungTV.volumeUp();
		samsungTV.volumeDown();
		samsungTV.powerOff();

	}
}
