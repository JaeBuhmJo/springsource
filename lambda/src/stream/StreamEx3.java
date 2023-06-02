package stream;

import java.util.Arrays;
import java.util.List;

public class StreamEx3 {
	public static void main(String[] args) {

		//skip(), limit() : 스트림 자르기
		
		List<String> list = Arrays.asList("사과", "딸기", "바나나", "사과", "배", "수박");
		list.stream().skip(2).limit(3).forEach(System.out::println);

	}
}
