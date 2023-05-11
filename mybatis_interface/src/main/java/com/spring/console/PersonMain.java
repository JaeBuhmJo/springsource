package com.spring.console;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.PersonDTO;
import com.spring.service.PersonService;

public class PersonMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		PersonService service = (PersonService) ctx.getBean("person");

		// 삽입
		PersonDTO insertPerson = new PersonDTO("han123", "한길동");
		System.out.println(service.insertPerson(insertPerson) ? "성공" : "실패");

		// 삭제
		System.out.println(service.deletePerson("kim123") ? "성공" : "실패");

		// 수정
		System.out.println(service.updatePerson(new PersonDTO("park123", "팍길동")) ? "성공" : "실패");

		// 전체
		List<PersonDTO> list = new ArrayList<PersonDTO>();
		list = service.getRows();
		for (PersonDTO personDTO : list) {
			System.out.println(personDTO);
		}

		// 단일조회
		System.out.println(service.getRow("choi123"));
	}
}
