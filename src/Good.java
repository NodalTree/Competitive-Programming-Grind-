import java.io.*;
import java.util.*;

public class ProbA {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
          st = new StringTokenizer(br.readLine());
          int N = Integer.parseInt(st.nextToken());
          int K = Integer.parseInt(st.nextToken());
          pw.println((int) Math.ceil(1.0 * (N - 1)/K) + 1);
          
		}
		pw.close();
	}

	static final int mod = 1_000_000_007;
	static final Random random = new Random();

	static void ruffleSort(int[] a) {
		int n = a.length;// shuffle, then sort
		for (int i = 0; i < n; i++) {
			int oi = random.nextInt(n), temp = a[oi];
			a[oi] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}

	static long add(long a, long b) {
		return (a + b) % mod;
	}

	static long sub(long a, long b) {
		return ((a - b) % mod + mod) % mod;
	}

	static long mul(long a, long b) {
		return (a * b) % mod;
	}

	static long div(long a, long b) {
		return mul(a, exp(b, mod - 2));
	}

	static long exp(long base, long exp) {
		if (exp == 0)
			return 1;
		long half = exp(base, exp / 2);
		if (exp % 2 == 0)
			return mul(half, half);
		return mul(half, mul(half, base));
	}

}
