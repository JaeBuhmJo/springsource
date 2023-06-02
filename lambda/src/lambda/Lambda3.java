package lambda;

// 람다식은 어디에 포함해서 쓸것인가?
// @FunctionalInterface
// 필수 요소는 아니지만, 하나의 추상 메소드만 정의 되도록 컴파일 단계에서 처리
@FunctionalInterface 
public interface Lambda3 {
	public void method(int x);
	// public void method2();
}
