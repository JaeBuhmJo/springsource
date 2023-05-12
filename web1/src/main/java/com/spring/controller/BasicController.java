package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // @Component 자식 어노테이션
@RequestMapping("/sample") // 옵션 - BasicController는 http://localhost:8080/sample/** 요청에 응답하는 컨트롤러
public class BasicController {
	//컨트롤러 안에 일반 메소드 작성 가능
	//일반 메소드는 @RequestMapping 어노테이션을 붙이지 않으면 됨
	
//	@RequestMapping("/basic") // http://localhost:8080/sample/basic + GET
	@GetMapping("/basic")
	public void basic() {
		log.info("basic......요청");
		
		// 컨트롤러 실행 후 (리턴값은 없을 수도 있음) ViewResolver가 실행 됨
		// ViewResolver는 /WEB-INF/views/sample/리턴값or8080이후텍스트.jsp 로 만들어서 결과 리턴
		// [/WEB-INF/views/sample/basic.jsp]
	}
}
