import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int n = Integer.parseInt(br.readLine());
			int[][] dp = new int[n + 1][10];

			dp[1][0] = 0;
			for (int i = 1; i <= 9; i++) {
				dp[1][i] = 1;
			}

			// 맨 마지막자리 1~8만 => *2
			// 마지막자리 0 => 무조건 1만 가능
			// 마지막자리 9 => 무조건 8만 가능

			for (int i = 2; i <= n; i++) {
				dp[i][0] = dp[i - 1][1];
				for (int j = 1; j <= 8; j++) {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
				}
				dp[i][9] = dp[i - 1][8];
			}
			
			int sum = 0;
			for (int is : dp[n]) {
				sum += is%1000000000;
			}
			bw.write(sum%1000000000 + "");
		}
	}
}