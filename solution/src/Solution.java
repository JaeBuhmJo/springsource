class Solution {
	boolean[] notPrime;
	boolean[] visited;
	String[] numArr;
	StringBuilder sb;
	int depth;

	public int solution(String numbers) {
		int answer = 0;
		getPrimeMatrix((int) Math.pow(10, numbers.length()));

		// numbers 길이 1~7인 문자열, 0~9까지의 숫자로만 이루어져있음
		// dfs 백트래킹으로 모든 가능한 문자열을 뽑아낸다
		// 결과는 set에 담아서 size로 반환한다.
		numArr = numbers.split("");
		visited = new boolean[numArr.length];
		sb = new StringBuilder();
		depth = 0;
		dfs(0);
		return answer;
	}

	public void dfs(int node) {
		if(depth==numArr.length)
		for (int i = 0; i < numArr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				depth++;
				sb.append(numArr[i]);
				dfs(i);
				visited[i] = false;
				depth--;
				sb.delete(sb.length() - 1, sb.length() + 1);
			}
		}
	}

	public void getPrimeMatrix(int size) {
		boolean[] notPrime = new boolean[size];
		notPrime[0] = true;
		notPrime[1] = true;
		for (int i = 2; i <= Math.sqrt(size); i++) {
			if (!notPrime[i]) {
				for (int j = i * i; j < notPrime.length; j += i) {
					notPrime[j] = true;
				}
			}
		}
	}
}