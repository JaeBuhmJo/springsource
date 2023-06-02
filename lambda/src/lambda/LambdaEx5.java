package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* java.util.function에서 제공하는 함수형 인터페이스
 * 1) Supplier<T> T get() : 리턴값은 있고, 매개변수 없음
 * 2) Consumer<T> void accept(T t) : 값을 받아서 사용하는 형태
 * 3) Predicate<T> boolean test(T t) : 값을 받아서 조건식에 대한 결과 반환
 * 4) Function<T, R> R apply(T t) : 값을 받아서 결과를 반환
 * 
  * 매개변수가 2개인 함수형 인터페이스들
 * BiConsuver<T, U>, BiPredicate<T, U>, ...
 */

public class LambdaEx5 {

	public static void main(String[] args) {

		Supplier<Integer> s = () -> (int) (Math.random() * 100) + 1;
		System.out.println(s.get());

		Consumer<Integer> c = i -> System.out.println(i + "");
		c.accept(87);

		Predicate<Integer> p = i -> i > 10;
		System.out.println(p.test(11));
		int a = 9;
		if (p.test(a)) {
			System.out.println(a + "는 10보다 크다");
		} else {
			System.out.println(a + "는 10보다 작다");
		}

		// T : 매개변수 타입, R : 리턴 타입 변수
		Function<Integer, Integer> f = i -> i / 10 * 10;

		System.out.println(f.apply(561));
		System.out.println(apply(279));

	}

	public static int apply(int i) {
		return i / 10 * 10;
	}

}
