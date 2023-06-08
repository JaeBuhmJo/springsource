import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		//입력 받아오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//스페이스바 기준으로 입력값 나눠받기
		//apple
		//banana
		//melon
		String line1 = br.readLine();
		String line2 = br.readLine();
		String line3 = br.readLine();
		
		System.out.println(line1+" "+line2);
		System.out.println(line2);
		System.out.println(line3);
	}
}