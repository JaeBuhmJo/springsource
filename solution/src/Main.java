import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int n = Integer.parseInt(br.readLine());
			int[][] dp = new int[n + 1][4];
			int skip2 = 0;
			int skip1 = 1;
			int get1 = 2;
			int get2 = 3;
			dp[1][get1] = Integer.parseInt(br.readLine());
			for (int i = 2; i <= n; i++) {
				int oz = Integer.parseInt(br.readLine());
				// 연속 스킵이 가능해야한다. 근데 3연스킵은 의미가 없다. 가운데거 하나 먹고 넘어오면 되니까.
				dp[i][skip2] = dp[i - 1][skip1];
				dp[i][skip1] = Math.max(dp[i - 1][get1], dp[i - 1][get2]);
				dp[i][get1] = Math.max(dp[i - 1][skip2], dp[i - 1][skip1]) + oz;
				dp[i][get2] = dp[i - 1][get1] + oz;
			}

			int max = 0;
			for (int q : dp[n]) {
				max = q > max ? q : max;
			}
			bw.write(String.valueOf(max));
		}
	}
}