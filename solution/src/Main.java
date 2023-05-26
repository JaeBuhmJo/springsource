import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int n = Integer.parseInt(br.readLine());
			char[][] arr = new char[n][n];
			for (char[] cs : arr) {
				Arrays.fill(cs, '*');
			}

			// 상향식 반복으로 별 지우면 된다.
			int cnt = 1;
			int gap = 3;
			while(cnt<=n/3) {
				for (int i = 0; i < arr.length; i+=gap) {
					for (int j = 0; j < arr.length; j+=gap) {
						
					}
				}
			}
		}
	}
}
