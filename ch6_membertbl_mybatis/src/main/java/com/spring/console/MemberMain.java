package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

public class MemberMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		MemberService service = (MemberService) ctx.getBean("member");
		
		
		System.out.println(service.insertMember(new MemberDTO("dong1234!","dong1234!","동길동","여","dong1234!@gmail.com"))?"추가 성공":"추가 실패");
		
		System.out.println(service.deleteMember("hong123" , "hong123!")?"삭제 성공":"삭제 실패");
		
		System.out.println(service.getRow("hong1234!", "hong1234!"));
		
		System.out.println(service.updateMember(new MemberDTO("hong1234!","hong1234!","홍길동","남","hong1234!@gmail.com"))?"수정 성공":"수정 실패");
	
		List<MemberDTO> list = service.getRows();
		for (MemberDTO memberDTO : list) {
			System.out.println(memberDTO);
		}
	}

}
