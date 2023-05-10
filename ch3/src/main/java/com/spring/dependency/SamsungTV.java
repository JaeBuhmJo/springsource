package com.spring.dependency;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv") // SamsungTV 객체가 생성됨
public class SamsungTV implements TV {
	
	@Inject
//	@Autowired // 주입(스프링 컨테이너가 관리하는 빈 중에서 하나가 주입됨
	@Qualifier("apple")
	private Speaker speaker;

	public SamsungTV() {
		System.out.println("SamsungTV 인스턴스 생성 - default 생성자");
	}
	
	public SamsungTV(Speaker speaker) {
		super();
		this.speaker = speaker;
		System.out.println("SamsungTV 인스턴스 생성 - 인자 생성자");
	}
	
	public void setSpeaker(Speaker speaker) {
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
