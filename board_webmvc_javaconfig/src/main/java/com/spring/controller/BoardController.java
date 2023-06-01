package com.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AttachFileDTO;
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
	public void listGet(@ModelAttribute("cri") Criteria cri, Model model) {
		// 도메인 객체로 받은 값들은 forward 페이지 jsp 까지 값이 전달된다. => 앞 자를 소문자로만 바꾼 객체명으로
		// 도메인 객체에 @ModelAttribute("이름") 부여 하면 자동 설명되는 소문자 이름을 '대체'. 기존 이름 사용 불가
		log.info("전체 리스트 요청");
		log.info("type : " + Arrays.toString(cri.getTypeArr()));
		List<BoardDTO> list = service.getList(cri);
		int total = service.getTotalCnt(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageDTO", new PageDTO(cri, total));
	}

	@GetMapping("/register")
	public void registerGet() {
		log.info("글 쓰기 폼 요청");
	}

	@PostMapping("/register")
	public String registerPost(BoardDTO boardDTO, RedirectAttributes rttr, Criteria cri) {
		log.info("글 쓰기 등록 요청 " + boardDTO);
		// 첨부파일 확인
//		if(boardDTO.getAttachList()!=null) {
//			boardDTO.getAttachList().forEach(attach -> log.info(attach.toString()));
//		}
		if (service.insert(boardDTO)) {
			log.info("글 번호 : " + boardDTO.getBno());
			rttr.addFlashAttribute("result", boardDTO.getBno());
			return "redirect:/board/list";
		}
		return "/board/register";
	}

	@GetMapping({ "/read", "/modify" })
	public void readGet(int bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("내용 조회 " + bno);
		BoardDTO boardDTO = service.getRow(bno);
		model.addAttribute("dto", boardDTO);
	}

	@PostMapping("/modify")
	public String modifyPost(BoardDTO boardDTO, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		// modify에 post form 에는 cri 변수가 없다. 근데 getmodify와 postmodify가 경로가 일치하면,
		// getmodify에 사용된 정보들은 postmodify에도 스프링이 자동으로 넘겨준다.
		log.info("내용 수정 " + cri);
		service.update(boardDTO);
		rttr.addFlashAttribute("result", "수정이 완료되었습니다");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}

	@GetMapping("/remove")
	public String removeGet(int bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		// 폴더에서 첨부 파일 제거
		deleteAttachFiles(service.getAttachList(bno));
		service.delete(bno);

		rttr.addFlashAttribute("result", "삭제가 완료되었습니다");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
		
		
	}

	// 첨부파일 가져오기(/getAttachList + GET + bno)
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno) {
		log.info("첨부파일 목록 요청" + bno);
		List<AttachFileDTO> list = service.getAttachList(bno);
		if (list != null) {
			return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private void deleteAttachFiles(List<AttachFileDTO> attachList) {
		log.info("첨부 파일 폴더에서 제거");
		if (attachList == null || attachList.size() <= 0) {
			return;
		}
		for (AttachFileDTO dto : attachList) {
			// 파일 경로
			Path path = Paths.get("c:\\upload\\" + dto.getUploadPath() + "\\" + dto.getUuid() + "_" + dto.getFileName());
			try {
				Files.deleteIfExists(path);

				if (Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\upload\\" + dto.getUploadPath() + "\\s_" + dto.getUuid() + "_" + dto.getFileName());
					Files.deleteIfExists(thumb);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
