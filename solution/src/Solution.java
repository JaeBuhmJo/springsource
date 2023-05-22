import java.util.Arrays;

class Solution {
	public int solution(int m, int n, int[][] puddles) {
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], 1);
		}

		for (int k = 0; k < puddles.length; k++) {
			dp[puddles[k][1]][puddles[k][0]] = 0;
		}

		dp[1][0] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= m; j++) {
				if (dp[i][j] == 0) {
					continue;
				}
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
			}
		}
		return dp[n][m];
	}
}