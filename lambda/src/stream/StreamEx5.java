package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

//최종연산
//count(), max(), min(), average(), findFirst(), sum()

public class StreamEx5 {

	public static void main(String[] args) {

		IntStream stream1 = Arrays.stream(new int[] { 1, 4, 6, 8, 9 });
		System.out.println(stream1.filter(k -> k % 2 == 0).count());
		// stream has already been operated upon or closed
		// 스트림은 일회용. 한 번 사용하면 닫혀서 재 사용 불가.
		stream1 = Arrays.stream(new int[] { 1, 4, 6, 8, 9 });
		System.out.println(stream1.filter(k -> k % 2 == 0).sum());

	}

}
