import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static int[] solution; // 해답을 저장할 배열
	private static boolean[] used; // 사용한 숫자를 표시하기 위한 배열

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			solution = new int[m];
			used = new boolean[n + 1];
			backTracking(0, bw);
		}
	}

	public static void backTracking(int depth, BufferedWriter bw) throws IOException {
		if (depth == m) {
			for (Integer integer : solution) {
				bw.write((integer + " "));
			}
			bw.newLine();
			return;
		}
		for (int i = 1; i <= n; i++) {
			solution[depth] = i;
			backTracking(depth + 1, bw);
		}
	}
}
