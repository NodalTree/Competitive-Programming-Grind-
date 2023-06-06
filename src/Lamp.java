import java.io.*;
import java.util.*;

public class ProbB {
	static class Pair implements Comparable<Pair>{
		public int a, b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int compareTo(Pair other) {
			return other.b - this.b;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
              st = new StringTokenizer(br.readLine());
              //ArrayList<Pair> lamps = new ArrayList<>();
              int N = Integer.parseInt(st.nextToken());
              ArrayList<Integer>[] adj = new ArrayList[N + 1];
              for(int i = 1; i<=N; i++) {
            	  adj[i] = new ArrayList<>();
              }
              for(int i = 0; i<N; i++) {
            	  st = new StringTokenizer(br.readLine());
            	  int a = Integer.parseInt(st.nextToken());
            	  int b = Integer.parseInt(st.nextToken());
            	  adj[a].add(b);
              }
              long ans = 0;
              for(int i = 1; i<=N; i++) {
            	  ArrayList<Integer> lamps = adj[i];
            	  Collections.sort(lamps);
            	  ArrayList<Integer> dumb = new ArrayList<>();
            	  for(int stupid: lamps) dumb.add(stupid);
            	  lamps.clear();
            	  for(int s = dumb.size() - 1; s>= 0; s--) lamps.add(dumb.get(s));
            	  for(int j = 0; j<Math.min(i, lamps.size()); j++) {
            		  ans += lamps.get(j);
            	  }
              }
              pw.println(ans);
           
              
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
