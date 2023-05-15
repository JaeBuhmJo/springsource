package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.BookDTO;
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

	@GetMapping("/read")
	public void readGet(int code, Model model) {
		log.info("requesting read code : " + code);
		BookDTO dto = service.get(code);
		model.addAttribute("dto",dto);
	}
	
}
