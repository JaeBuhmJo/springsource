import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	public static void main(String args[]) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int testcases = Integer.parseInt(br.readLine());

			for (int i = 0; i < testcases; i++) {
				int num1 = 0;
				int num2 = 0;
				int gap = Integer.parseInt(br.readLine());
				for (int j = 4; j <= 1000000000; j++) {
					if ((j >= 4) && (j % 2 == 0) || !isPrime(j)) {
						if (!isPrime(j - gap)) {
							num1 = j;
							num2 = j - gap;
							break;
						}
						if (!isPrime(j + gap)) {
							num1 = j + gap;
							num2 = j;
							break;
						}
					}
				}
				System.out.println("#" + (i + 1) + " " + num1 + " " + num2);
			}
		}
	}

	public static boolean isPrime(int num) {
		if (num < 3) {
			return true;
		}
		for (int i = 3; i <= (int) Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}