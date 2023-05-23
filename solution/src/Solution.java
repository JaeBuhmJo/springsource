import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	public static void main(String args[]) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int testcases = Integer.parseInt(br.readLine());
			for (int i = 0; i < testcases; i++) {
				int count = 0;
				int radius = Integer.parseInt(br.readLine());

				for (int j = 1; j <= radius; j++) {
					for (int j2 = 0; j2 <= radius; j2++) {
						if (Math.pow(j, 2) + Math.pow(j2, 2) <= Math.pow(radius, 2)) {
							count++;
						}
					}
				}
				System.out.println("#" + (i + 1) + " " + ((4 * count) + 1));
			}
		}
	}
}