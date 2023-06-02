import java.io.*;
import java.util.*;

public class DiceCombinations {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long[] dp = new long[N + 1];//define dp[i] as the number of ways to get to i
        for(int i = 1; i<= Math.min(N, 6); i++) dp[i] = 1;
        for(int i = 2; i<=N; i++){
            //transition is ez take all the number of ways to get to i - 1, then just roll a 1, i - 2 then just roll a 2, etc..
            for(int sub = 1; sub <= 6; sub++){
                if(i - sub > 0){
                    dp[i] = add(dp[i], dp[i - sub]);
                }
            }
        }
        pw.println(dp[N]);
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
