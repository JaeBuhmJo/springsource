import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int n = Integer.parseInt(br.readLine());

			Paper paper = new Paper(n);

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					paper.get(i, j, Integer.parseInt(st.nextToken()));
				}
			}

			// 재귀 분할정복 수행

			bw.write(paper.getWhiteCount() + "\n" + paper.getBlueCount());
		}
	}
}

class Paper {
	int[][] paper;
	int whiteCount;
	int blueCount;

	public Paper(int n) {
		this.paper = new int[n][n];
		this.whiteCount = 0;
		this.blueCount = 0;
	}

	public void get(int i, int j, int n) {
		paper[i][j] = n;
	}

	// 모두 1만 들어있다 라는걸 어떻게 확인할건지 - 그냥 루프 돌아도 그냥저냥 될거같기도
	public void splitPaper() {
		int color = isSingleColor(0, 0, 0, 0);
		if (color == 2) {
			// 4번의 재귀호출
			splitPaper();
			splitPaper();
			splitPaper();
			splitPaper();
		} else {
			if (color == 1) {
				whiteCount++;
			} else {
				blueCount++;
			}
		}
	}

	public int isSingleColor(int up, int down, int left, int right) {
		// 단일 컬러 할당된 수 리턴. / 혼합색이면 2를 리턴
		int color = 2;
		for (int i = up; i <= down; i++) {
			for (int j = left; j <= right; j++) {
				if (color == 2) {
					color = paper[i][j] == 1 ? 1 : 0;
				}
				if (paper[i][j] != color) {
					return 2;
				}
			}
		}
		return color;
	}

	public int getWhiteCount() {
		return whiteCount;
	}

	public int getBlueCount() {
		return blueCount;
	}
}
