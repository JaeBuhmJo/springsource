import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int saveCnt;
	static int targetCnt;
	static int[] temp;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			targetCnt = Integer.parseInt(st.nextToken());

			int[] arr = new int[length];
			temp = new int[length];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			mergeSort(arr, 0, length - 1);
			if (targetCnt > saveCnt) {
				System.out.println(-1);
			}
		}
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	public static void merge(int[] arr, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = left; // for temp
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
				System.out.println(temp[k - 1]);
			}
		}
	}
}
