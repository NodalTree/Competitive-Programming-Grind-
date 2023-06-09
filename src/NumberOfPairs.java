import java.io.*;
import java.util.*;
import java.awt.Point;

public class ProbA{
	//had some difficulties with eclipse so this shouldve been faster in contest
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            int l = fs.nextInt();
            int r = fs.nextInt();
            int[] a = fs.readArray(N);
            ruffleSort(a);
            HashMap<Integer, Integer> map_first = new HashMap<>();
            HashMap<Integer, Integer> map_last = new HashMap<>();
            TreeSet<Integer> set = new TreeSet<>();
            long ans = 0;
            for(int i = 0; i<N; i++) {
            	if(a[i] > r) continue;
                int min = l - a[i];
                int max = r - a[i];
                //has to gain at least min but no more than max, so ceiling(min) and floor(max)
                int first = -1;
                int last = -1;
                //if(i == 2) pw.println(min + " "+ max + " "+ a[i]);
                if(set.ceiling(min) != null) {
                	first = map_first.get(set.ceiling(min));
                }
                if(set.floor(max) != null) {
                	last = map_last.get(set.floor(max));
                }
                if(first != -1 && last != -1) {
                	ans += (last - first + 1);
                }
                set.add(a[i]);
                if(map_first.get(a[i]) == null) {
            		map_first.put(a[i], i);
            	}
            	map_last.put(a[i], i);
            }
            pw.println(ans);
        }
        pw.close();
    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

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

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

}
