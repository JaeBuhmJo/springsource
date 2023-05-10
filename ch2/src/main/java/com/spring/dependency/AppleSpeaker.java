package com.spring.dependency;

public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("AppleSpeaker 인스턴스 생성");
	}
	
	@Override
	public void volumnUp() {
		System.out.println("AppleSpeaker Volumn Up");
	}

	@Override
	public void volumnDown() {
		System.out.println("AppleSpeaker Volumn Down");
	}

}
