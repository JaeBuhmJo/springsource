package stream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx4 {

	public static void main(String[] args) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			List<Student> stuList = new ArrayList<Student>();
			stuList.add(new Student("홍길동", 99));
			stuList.add(new Student("고길동", 89));
			stuList.add(new Student("김길동", 79));
			stuList.add(new Student("박길동", 69));
			stuList.add(new Student("김지원", 75));

			List<Integer> pointList = stuList.stream().map(k -> k.getPoint()).collect(Collectors.toList());
			pointList.forEach(k -> System.out.println(k));

			// 새로운 리스트에 과일명을 대문자로 변경한 것을 수집하고
			List<String> fruits = Arrays.asList("melon", "apple", "banana", "grape");

			List<String> fruitsCap = fruits.stream().map(String::toUpperCase).collect(Collectors.toList());
			fruitsCap.forEach(System.out::println);

		}
	}

}
