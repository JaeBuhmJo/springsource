package com.spring.schedule;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.domain.AttachFileDTO;
import com.spring.mapper.AttachMapper;

@Component
public class TaskTest {

	@Autowired
	private AttachMapper mapper;

//	 * <ul>
//	 * <li>second</li>
//	 * <li>minute</li>
//	 * <li>hour</li>
//	 * <li>day of month</li>
//	 * <li>month</li>
//	 * <li>day of week</li>
//	 * </ul>

	@Scheduled(cron = "0 0 2 * * *")
	public void schedulerTest() {
		// db에서 어제 날짜의 파일 목록 가져오기
		List<AttachFileDTO> oldList = mapper.oldFiles();
		// oldList를 경로의 형태로 변경해야 함
		// 이미지 파일이라면 파일 목록에 썸네일 경로도 추가해야함
//		List<Path> pathList = new ArrayList<Path>();
//		for (AttachFileDTO dto : oldList) {
//			Path path = Paths.get("c:\\upload\\" + dto.getUploadPath() + "\\" + dto.getUuid() + "_" + dto.getFileName());
//			pathList.add(path);
//
//			if (dto.isFileType()) {
//				Path thumb = Paths.get("c:\\upload\\" + dto.getUploadPath() + "\\s_" + dto.getUuid() + "_" + dto.getFileName());
//				pathList.add(thumb);
//			}
//		}
		List<Path> pathList = oldList.stream()
				.map(k -> Paths.get("c:\\upload\\" + k.getUploadPath() + "\\" + k.getUuid() + "_" + k.getFileName()))
				.collect(Collectors.toList());
		oldList.stream().filter(k -> k.isFileType())
				.map(k -> Paths.get("c:\\upload\\" + k.getUploadPath() + "\\s_" + k.getUuid() + "_" + k.getFileName()))
				.forEach(k -> pathList.add(k));

		System.out.println(pathList);

		// 어제날짜 구해서 폴더에 접근한 후 폴더에 있는 파일 목록 가져오기
		String yesterday = getFolderYesterday();

		File targetDir = Paths.get("c:\\upload", yesterday).toFile();
		System.out.println("targetDir " + targetDir);

		// 비교 후 일치하지 않는 파일 삭제
		File[] removeFiles = targetDir.listFiles(f -> pathList.contains(f.toPath()) == false);
		for (File remove : removeFiles) {
			remove.delete();
		}
	}

	private String getFolderYesterday() {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return str.replace("-", File.separator); // 운영체제에 맞는 구분자로 변경 ex)2023-05-31 => 2023/05/31

	}

//	@Scheduled(fixedDelay = 10000)
//	public void schedulerTest2() {
//		System.out.println("10초마다 스케줄링...");
//	}
}
