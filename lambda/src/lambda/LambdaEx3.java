package lambda;

public class LambdaEx3 {

	public static void main(String[] args) {
		
		Lambda3 lambda = (x) -> System.out.println(x);
		lambda.method(170);
		
		lambda = (x) ->{
			int i = 10;
			System.out.println(i*x);
		};
		lambda.method(7);
	}

}
