import java.io.*;
import java.util.*;
public class AlmostIncreasing {
    //https://codeforces.com/contest/1817/problem/A
    //once we come up with the intuition to understand this problem in terms of decreasing subarrays (liek the partition part)
    //everything else falls into place
    //how do we come up with this intuition?...well just think about what ranges are causing you truble, decreasing obv
    //so split the array into those "trouble ranges" and try to fix the one by one
    //then the whole idea of picking min(len(subarray), 2) is obvious ...u think think of picking 2 at first and prove that it should always work (greedy)
    //after this we can't obviously simulate it so we cleverly apply prefix sums to find ig the "inner elements" whch all
    //share the same property
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] a = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        int[] inner = new int[N + 1];//inner[i] = (a[i + 1] <= a[i] && a[i] <= a[i - 1]) ? 1 : 0;
        //first one cannot be an inner obv
        for(int i = 2; i + 1 <= N; i++){
            inner[i] = (a[i + 1] <= a[i] && a[i] <= a[i - 1]) ? 1 : 0;
        }
        int[] ps = new int[N + 1];
        for(int i = 1; i<=N; i++){
            ps[i] = ps[i - 1] + inner[i];
        }
        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if(l == r) pw.println(1);
            else pw.println((r - l + 1 ) - (ps[r - 1] - ps[l]));//r - 1 bc we don't want the inner element of r to be counted if it is one
            //and l because we don't want the element of l to be counted for an inner element, so it's l + 1 to r - 1, then thats the prefix sum obv
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
