package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;
import com.spring.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService reService;

	// MediaType을 쓰는 이유는, 컨버터가 여러개일 경우 이 메소드의 반환 타입을 명확히 구분하기 위함
	@GetMapping(value = "/{rno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno) {
		log.info("댓글 조회 요청 : " + rno);
		return  reService.read(rno)!=null?
				new ResponseEntity<ReplyDTO>(reService.read(rno), HttpStatus.OK):
				new ResponseEntity<ReplyDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// http://localhost:8080/replies/new + POST + 입력데이터(json)
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyDTO replyDTO){
		log.info("댓글 삽입"+replyDTO);
		return reService.insert(replyDTO)?
				new ResponseEntity<String>("success",HttpStatus.OK):
				new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	  
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> select(@PathVariable("bno") int bno, @PathVariable("page") int page){
		log.info("댓글 조회 "+bno);
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<ReplyPageDTO>(reService.getList(cri, bno), HttpStatus.OK);
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO, @PathVariable("rno")int rno){
		replyDTO.setRno(rno);
		log.info("댓글 수정 " + replyDTO);
		return reService.update(replyDTO)? 
				new ResponseEntity<String>("success",HttpStatus.OK):
				new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// http://localhost:8080/replies/rno + DELETE
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){   
		log.info("댓글 삭제 "+rno);
		return reService.delete(rno)?
				new ResponseEntity<String>("success",HttpStatus.OK):
				new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
