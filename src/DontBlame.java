import java.io.*;
import java.util.*;

public class DontBlame{
    //numbers are small and notice that the bitwise operator can't increase a number
    //let dp[i][j] represent the number of subsequences with the mask of j, considering the first i elements
    //basic dp prefix
    //we can either take the element, so dp[i][a[i] & j] = dp
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] a = new int[N  + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<= N; i++) a[i] = Integer.parseInt(st.nextToken());
            long[][] dp = new long[N + 1][64];
            for(int i = 1; i<=N; i++){
                for(int j = 0; j<=63; j++){
                    dp[i][j] = add(dp[i][j], dp[i - 1][j]);
                    dp[i][a[i] & j] = add(dp[i][a[i] & j], dp[i - 1][j]);
                }
                dp[i][a[i]] = add(dp[i][a[i]], 1);
            }
            //pw.println((1 << 6));
            long ans = 0;
            for(int i = 0; i<= 63; i++){
                if(Long.bitCount(i) == K){
                    ans = add(ans, dp[N][i]);
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
