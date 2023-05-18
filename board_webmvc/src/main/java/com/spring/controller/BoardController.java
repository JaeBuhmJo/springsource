package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping("/list")
	public void listGet(Criteria cri, Model model) {
		log.info("전체 리스트 요청");
		List<BoardDTO> list = service.getList(cri);
		int total = service.getTotalCnt();
		model.addAttribute("list", list);
		model.addAttribute("pageDTO",new PageDTO(cri, total));
	}

	@GetMapping("/register")
	public void registerGet() {
		log.info("글 쓰기 폼 요청");
	}

	@PostMapping("/register")
	public String registerPost(BoardDTO boardDTO, RedirectAttributes rttr) {
		log.info("글 쓰기 등록 요청 " + boardDTO);
		if (service.insert(boardDTO)) {
			log.info("글 번호 : "+boardDTO.getBno());
			rttr.addFlashAttribute("result", boardDTO.getBno());
			return "redirect:/board/list";
		} else {
			return "/board/register";
		}
	}

	@GetMapping({ "/read", "/modify" })
	public void readGet(int bno, Model model) {
		log.info("내용 조회 " + bno);
		BoardDTO boardDTO = service.getRow(bno);
		model.addAttribute("boardDTO", boardDTO);
	}

	@PostMapping("/modify")
	public String modifyPost(BoardDTO boardDTO, RedirectAttributes rttr) {
		service.update(boardDTO);
		rttr.addFlashAttribute("result","수정이 완료되었습니다");
		return "redirect:/board/list";
	}

	@GetMapping("/remove")
	public String removeGet(int bno, RedirectAttributes rttr) {
		service.delete(bno);
		rttr.addFlashAttribute("result","삭제가 완료되었습니다");
		return "redirect:/board/list";
	}
}
