package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.FileDTO;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("uploadForm 요청");
	}

	// 첨부 파일이 여러개인 경우
	@PostMapping("/uploadForm")
	public void uploadPost(FileDTO fileDTO) {
		log.info("uploadForm 요청" + fileDTO);

		String uploadPath = "C:\\upload";
		for (MultipartFile multipartFile : fileDTO.getFile()) {
			log.info("file Name " + multipartFile.getOriginalFilename());
			log.info("content type  " + multipartFile.getContentType());
			log.info("file size " + multipartFile.getSize());
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

			File saveFile = new File(uploadPath, fileName);
			try {
				// 폴더에 저장
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 첨부 파일이 한 개인 경우
//	@PostMapping("/uploadForm")
//	public void uploadPost(FileDTO fileDTO) {
//		log.info("uploadForm 요청" + fileDTO);
//		log.info("file Name " + fileDTO.getFile().getOriginalFilename());
//		log.info("content type  " + fileDTO.getFile().getContentType());
//		log.info("file size " + fileDTO.getFile().getSize());
//		
//		String uploadPath = "C:\\upload";
//		File saveFile = new File(uploadPath, fileDTO.getFile().getOriginalFilename());
//		try {
//			//폴더에 저장
//			fileDTO.getFile().transferTo(saveFile);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
