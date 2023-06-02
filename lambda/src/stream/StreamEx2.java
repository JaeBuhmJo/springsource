package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx2 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("바둑", "바나나", "포도", "딸기", "바질", "강아지");

		// "바"로 시작하는 요소를 새로운 리스트에 추가 후 출력
		ArrayList<String> baList = new ArrayList<String>();

		for (String string : list) {
			if (string.charAt(0) == '바') {
				baList.add(string);
			}
		}
		for (String string : baList) {
			System.out.println(string);
		}

		list.stream().filter(str -> str.startsWith("바")).forEach(str -> System.out.println(str));

		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("홍길동", 99));
		stuList.add(new Student("고길동", 89));
		stuList.add(new Student("김길동", 79));
		stuList.add(new Student("박길동", 69));
		stuList.add(new Student("김지원", 75));

		for (Student student : stuList) {
			if (student.getName().startsWith("김")) {
				System.out.println(student.getName());
			}
		}

		stuList.stream().filter(stu -> stu.getName().startsWith("김")).forEach(stu -> System.out.println(stu.getName()));
		stuList.stream().filter(stu -> stu.getName().startsWith("김")).forEach(System.out::println);
		List<String> list2 = Arrays.asList("바둑", "바둑", "바나나", "포도", "딸기", "바질", "강아지");
		list2.stream().distinct().forEach(k -> System.out.print(k + " "));

		Stream<File> stream = Stream.of(new File("d:\\test1.txt"), new File("d:\\test2.txt"), new File("d:\\test3.txt"),
				new File("d:\\test1.java"), new File("d:\\test1.bak"), new File("d:\\test"));

		// 파일 확장자 추출한 후 중복을 제거하고 출력하기
		// 파일명 추출 => 확장자 추출 => 확장자만 모음 => 중복제거 => 출력
		stream.map(k -> k.getName()).peek(k->System.out.println("필터"+k)).map(k -> k.substring(k.indexOf(".") + 1)).distinct()
				.forEach(k -> System.out.println(k));

	}
}
