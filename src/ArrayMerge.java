import java.io.*;
import java.util.*;

public class ProbB{
     https://youtu.be/_0OAo8y7AtI (this is my livesolve)
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            int[] b = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++) a[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++) b[i] = Integer.parseInt(st.nextToken());
            int[] freq_a = new int[2 * N + 1];
            int[] freq_b = new int[2 * N + 1];
            freq_a[a[0]] = 1;
            freq_b[b[0]] = 1;
            int ans1 = 1;
            int cur = 1;
            for(int i = 1; i<N; i++){
                if(a[i] == a[i - 1]) cur++;
                else cur = 1;
                freq_a[a[i]] = Math.max(freq_a[a[i]], cur);

            }
            int ans2 = 1;
            int cur2 = 1;
            for(int i = 1; i<N; i++){
                if(b[i] == b[i - 1]) cur2++;
                else cur2 = 1;
                freq_b[b[i]] = Math.max(freq_b[b[i]], cur2);
            }
            int ans = 0;
            for(int i = 0; i<=2 * N; i++){
                ans = Math.max(freq_a[i] + freq_b[i], ans);
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
