package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.AttachFileDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
public class UploadAjaxController {

	@GetMapping("/uploadAjax")
	@PreAuthorize("isAuthenticated()")
	public void uploadAjaxGet() {
		log.info("uploadAjax 폼 요청");
	}

	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) { // js에서 append한 파일 이름으로 받아야함
		log.info("upload 요청");
		List<AttachFileDTO> fileList = new ArrayList<>();

		String uploadPath = "c://upload";
		// 폴더 생성
		String uploadFolderPath = getFolder();
		File uploadFullPath = new File(uploadPath, uploadFolderPath);

		if (!uploadFullPath.exists()) {
			uploadFullPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {
			log.info("file name" + multipartFile.getOriginalFilename());
			log.info("file size" + multipartFile.getSize());

			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

			File saveFile = new File(uploadFullPath, fileName);

			// 파일 한 개당 AttachFileDTO 생성
			AttachFileDTO attach = new AttachFileDTO();
			attach.setFileName(multipartFile.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());

			try {
				// 원본 파일 저장
				multipartFile.transferTo(saveFile);

				// 업로드된 파일 타입 체크
				if (checkImageType(saveFile)) {
					attach.setFileType(true);
					// 이미지 파일이라면 썸네일 이미지로 저장
					// 원본 이미지 읽어오기
					BufferedImage origin = ImageIO.read(saveFile);

					// 썸네일 파일명
					File thumbnail = new File(uploadFullPath, "s_" + fileName);

					double ratio = 10;
					int width = (int) (origin.getWidth() / ratio);
					int height = (int) (origin.getWidth() / ratio);

					Thumbnails.of(origin).size(width, height).toFile(thumbnail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileList.add(attach);
		}
		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}

	// 파일 요청 시 파일 보내주기
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("파일 요청 " + fileName);

		File file = new File("c:\\upload\\" + fileName);

		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> result = null;
		try {
			// 서버가 보내는 파일에 대한 타입 지정
			headers.add("content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// HttpServletRequest 객체 : 클라이언트 정보를 가져올 수 있음
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName, @RequestHeader("User-agent") String userAgent) {
		log.info("파일 다운로드 요청 " + fileName);
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		String oriFileName = resource.getFilename();
		String splitUuid = oriFileName.substring(oriFileName.indexOf('_') + 1);

		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();

		String downloadName = null;
		try {
			// ms계열
			if (userAgent.contains("Trident") || userAgent.contains("Edge")) {
				downloadName = URLEncoder.encode(splitUuid, "utf-8").replaceAll("\\+", " ");
			} else {
				downloadName = new String(splitUuid.getBytes("utf-8"), "ISO-8859-1");
			}
			headers.add("Content-Disposition", "attachment;filename=" + downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("파일 제거 요청" + fileName + "타입" + type);
		try {
			// 특수문자에 인코딩이 일어남 - 2023/05/30 -> 2023%5C05%5C30 -> 인코딩된 것을 디코딩 해줘야함
			File file = new File("c:\\upload\\", URLDecoder.decode(fileName, "utf-8"));
			file.delete(); // 파일 삭제
			if (type.equals("image")) {
				file = new File(file.getAbsolutePath().replace("s_", ""));
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// 일반 메소드(파일 타입 확인용)
	private boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath()); // image/jpg 식으로 들어옴
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 일반 메소드(폴더 생성)
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}
