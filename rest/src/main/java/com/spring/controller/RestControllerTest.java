package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleDTO;

@RestController
public class RestControllerTest {

	// 일반 컨트롤러에서 return 값은 view 이름
	// Rest 컨트롤러에서 return 값은 데이터

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World"; // 그냥 진짜 Hello World 라는 스트링을 리턴한다. (view resolver 안 돌고)
	}

	// produces : 리턴 값을 특정 형태로 보낼거야
	// 자바 객체 ==> json 형태로 변경해주는 라이브러리가 필요함
	@GetMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
	public SampleDTO sendDto() {
		return new SampleDTO("1234", "홍", "길동");
	}

	@GetMapping(value = "/sends", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SampleDTO> sendList() {
		List<SampleDTO> list = new ArrayList<>();
		list.add(new SampleDTO("1234", "홍", "길동"));
		list.add(new SampleDTO("1235", "동", "길동"));
		list.add(new SampleDTO("1236", "백", "길동"));
		list.add(new SampleDTO("1237", "서", "길동"));
		return list;
	}

	// ResponseEntity : 상태코드(200, 400, 404, 500 같은거)와 데이터 같이 전송
	@GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SampleDTO> check(double height, double weight) {
		SampleDTO dto = new SampleDTO("1234", "" + height, "" + weight);
		ResponseEntity<SampleDTO> entity = null;
		if (height < 160) {
			entity = new ResponseEntity<SampleDTO>(dto, HttpStatus.BAD_REQUEST);
		} else {
			entity = new ResponseEntity<SampleDTO>(dto, HttpStatus.OK);
		}
		return entity;
	}

	//경로에 있는 특정 값을 사용하고 싶다면?
	// http://localhost:8080/product/bags/1001 : bags (카테고리명), 1001(상품 아이디) 
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat,@PathVariable("pid") String pid) {
		return new String[] { 
				"category : " + cat, 
				"ProductId : " + pid 
		};
	}
}
