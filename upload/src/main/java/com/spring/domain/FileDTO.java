package com.spring.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	private String name;
	//type 무조건 MultipartFile로 가야함 - 파일 요소
	//file 태그에 multiple 달고 여러개 허용할거면, DTO에서 MultipartFile을 배열로 선언
	private MultipartFile[] file;
}
