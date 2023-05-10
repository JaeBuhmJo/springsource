package com.spring.dependency;

public class SamsungTV implements TV {
	
	private SonySpeaker speaker;

	public SamsungTV() {
		
	}
	
	public SamsungTV(SonySpeaker speaker) {
		super();
		this.speaker = speaker;
	}
	
	public void setSpeaker(SonySpeaker speaker) {
		this.speaker = speaker;
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 파워 On");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 파워 Off");
	}
	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV - 볼륨 Up");
		speaker.volumnUp();
	}
	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV - 볼륨 Down");
		speaker.volumnDown();
	}
}
