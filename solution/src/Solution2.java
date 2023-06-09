class Solution2 {
	boolean[] visited;
	int max = 0;
	int depth = 0;

	public int solution(int k, int[][] dungeons) {
		visited = new boolean[dungeons.length];
		dfs(0, k, 0, dungeons);
		return max;
	}

	public void dfs(int node, int k, int count, int[][] dungeons) {
		if (depth == dungeons.length) {
			max = Math.max(max, count);
			return;
		}
		for (int i = 0; i < dungeons.length; i++) {
			if (!visited[i]) {
				depth++;
				visited[i] = true;
				if (k >= dungeons[i][0]) {
					dfs(i, k - dungeons[i][1], count + 1, dungeons);
				} else {
					dfs(i, k, count, dungeons);
				}
				visited[i] = false;
				depth--;
			}
		}
	}
}