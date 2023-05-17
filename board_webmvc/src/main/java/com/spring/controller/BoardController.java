package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.BoardDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping("/list")
	public void listGet(Model model) {
		log.info("전체 리스트 요청");
		List<BoardDTO> list = service.getList();
		model.addAttribute("list", list);
	}

	@GetMapping("/register")
	public void registerGet() {
		log.info("글 쓰기 폼 요청");
	}

	@PostMapping("/register")
	public String registerPost(BoardDTO boardDTO) {
		log.info("글 쓰기 등록 요청 " + boardDTO);
		if (service.register(boardDTO)) {
			return "redirect:/board/list";
		} else {
			return "/board/register";
		}
	}

	@GetMapping("/read")
	public void readGet(int bno, Model model) {
		log.info("내용 조회 " + bno);
		BoardDTO boardDTO = service.getRow(bno);
		model.addAttribute("boardDTO", boardDTO);
	}
}
