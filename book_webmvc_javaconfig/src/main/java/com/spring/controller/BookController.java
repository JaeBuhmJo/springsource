package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BookDTO;
import com.spring.domain.SearchDTO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService service;

//	// insert.jsp 보여주기 =>
//	@GetMapping("/insert")
//	public String insertForm() {
//		log.info("도서 추가 폼 요청");
//		return "/product/insert";
//	}
//
//	@PostMapping("/insert")
//	public String insertPost(BookDTO dto) {
//		log.info("도서 추가 요청");
//		try {
//			boolean insertFlag = service.insert(dto);
//			if (insertFlag) {
//				return "redirect:/book/list";
//			} else {
//				return "/product/insert";
//			}
//		} catch (Exception e) {
//			System.out.println("오류");
//			return "/product/insert";
//		}
//	}
//
//	@GetMapping("/list")
//	public String listForm(Model model) {
//		log.info("도서 정보 목록 요청");
//		List<BookDTO> list = service.getList();
//		model.addAttribute("list", list); // == request.setAttribute()
//		return "/product/list"; // 기본 forward 방식 이동
//	}
//
//	@GetMapping("/read")
//	public String readGet(int code, Model model) {
//		log.info("requesting read code : " + code);
//		BookDTO dto = service.get(code);
//		model.addAttribute("dto",dto);
//		return "/product/read";
//	}

	// insert.jsp 보여주기 =>
	@GetMapping("/insert")
	public void insertForm() {
		log.info("도서 추가 폼 요청");
	}

	@PostMapping("/insert")
	public String insertPost(BookDTO dto) {
		log.info("도서 추가 요청");
		try {
			boolean insertFlag = service.insert(dto);
			if (insertFlag) {
				return "redirect:/book/list";
			} else {
				return "/book/insert";
			}
		} catch (Exception e) {
			System.out.println("오류");
			return "/book/insert";
		}
	}

	@GetMapping("/list")
	public void listForm(Model model) {
		log.info("도서 정보 목록 요청");
		List<BookDTO> list = service.getList();
		model.addAttribute("list", list); // == request.setAttribute()
	}

	@GetMapping({ "/read", "/modify" }) // read로 들어오거나 modify로 들어오는 거 둘 다 컨트롤 연결
	public void readGet(int code, Model model) {
		log.info("도서 정보 조회 요청 : " + code);
		BookDTO dto = service.get(code);
		model.addAttribute("dto", dto);
		// ViewResolver 동작 -> /WEB-INF/views/들어온경로.jsp
	}

	@PostMapping("modify")
	public String updatePost(BookDTO dto, RedirectAttributes rttr) {
		log.info("도서 정보 수정 " + dto);
		service.update(dto);
		rttr.addAttribute("code", dto.getCode());
		return "redirect:/book/read";
	}

	@GetMapping("remove")
	public String removeGet(int code) {
		log.info("도서 삭제 요청, code : " + code);
		service.delete(code);
		return "redirect:/book/list";
	}

//	@GetMapping("search")
//	public String searchGet( String criteria, String keyword, Model model) {
//		log.info("도서 정보 검색 "+criteria+keyword);
//		List<BookDTO > list = service.getSearchList(criteria, keyword);
//		model.addAttribute("list",list);
//		
//		return "/book/list";
//	}

	@GetMapping("search")
	public String searchGet(SearchDTO search, Model model) {
		log.info("도서 정보 검색 " + search);
		List<BookDTO> list = service.getSearchList(search.getCriteria(), search.getKeyword());
		model.addAttribute("list", list);
		model.addAttribute("searchDTO", search);
		return "/book/list";
	}

}
