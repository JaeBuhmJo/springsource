package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;

public class BookMain {

	public static void main(String[] args) {
		// BookService service = new BookServiceImpl(new BookDAO());

		// 컨테이너 기반 프로젝트 작성
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		// service 호출
		BookService service = (BookService) ctx.getBean("bookService");

		// 도서 추가
		BookDTO insertDto = new BookDTO(1013, "이건 일본어책", "조재범", 700, "");

		if (service.insertBook(insertDto)) {
			System.out.println("입력성공");
		}
		// 도서 삭제
//		if (service.deleteBook(1012)) {
//			System.out.println("삭제성공");
//		}
		// 도서 업데이트
//		BookDTO updateDto = new BookDTO();
//		updateDto.setCode(1011);
//		updateDto.setPrice(80000);
//		//이거 안됨. BindingException
//		if (service.updateBook(90000, 1011)) {
//			System.out.println("수정성공");
//		}
//
//		// 도서 하나 조회
//		BookDTO getDto = service.getbook(1011);
//		System.out.println(getDto);

		// 전체 도서 목록 가져오기
		List<BookDTO> list = service.getBookList();
		for (BookDTO bookDTO : list) {
			System.out.println(bookDTO);
		}
	}
}
