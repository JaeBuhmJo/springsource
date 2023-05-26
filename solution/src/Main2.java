import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int targetCnt = Integer.parseInt(st.nextToken());

			int[] arr = new int[length];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			new MergeSort2(arr, targetCnt).solution();
			System.out.println(Arrays.toString(arr));
		}
	}
}

class MergeSort2 {
	int saveCnt;
	int targetCnt;
	int answer;
	int[] arr;
	int[] temp;

	public MergeSort2(int[] arr, int targetCnt) {
		this.targetCnt = targetCnt;
		this.arr = arr;
		this.saveCnt = 0;
		this.temp = new int[arr.length];
		this.answer = -1;
	}

	public void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}

	public void merge(int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = left;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = arr[i++];
		}
		while (j <= right) {
			temp[k++] = arr[j++];
		}
		i = left;
		k = left;
		while (i <= right) {
			arr[i++] = temp[k++];
			saveCnt++;
			if (saveCnt == targetCnt) {
				answer = temp[k - 1];
			}
		}
	}

	public void solution() {
		mergeSort(0, arr.length - 1);
		System.out.println(answer);
	}
}
