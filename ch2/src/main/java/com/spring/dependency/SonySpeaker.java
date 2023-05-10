package com.spring.dependency;

public class SonySpeaker implements Speaker {

	public SonySpeaker() {
		System.out.println("SonySpeaker 인스턴스 생성");
	}
	
	@Override
	public void volumnUp() {
		System.out.println("SonySpeaker Volumn Up");
	}

	@Override
	public void volumnDown() {
		System.out.println("SonySpeaker Volumn Down");
	}

}
