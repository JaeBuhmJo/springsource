package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEach2 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "B", "C", "D", "E");

//		list.stream().forEach(null);
		// .forEach는 Consumer를 내포
		list.forEach(k -> System.out.println(k));
		list.forEach(System.out::println);

		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("홍길동", 99));
		stuList.add(new Student("고길동", 99));
		stuList.add(new Student("김길동", 99));
		stuList.add(new Student("박길동", 99));

		stuList.forEach(k -> System.out.println(k.getName() + " " + k.getPoint()));

		// Map
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < 7; i++) {
			map.put(String.valueOf((char) ('A' + i)), 10 * (i + 1));
		}

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		map.forEach((k, v) -> System.out.println(k + " : " + v));

		// key 값이 E 일 때 추가로 Hello, E 출력문을 추가
		map.forEach((k, v) -> {
			System.out.println(k + " : " + v);
			if (k.equals("E")) {
				System.out.println("Hello, " + k);
			}
		});
	}

}
