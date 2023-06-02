import java.io.*;
import java.util.*;


public class MagicTriplesEasy {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            //intuition fix the middle element...which can range from 1 to 10^6
            //for a fix middle element a, then we need to go through the divisors of a^2
            //only sqrt(a^2) divisors so it works fast enuff
            //once we go thru the set divisors we need the frequencies and solution is obv
            //question was easy bc i had upsolved boss can count, which is a much harder version of this like type of problem
            int N = Integer.parseInt(st.nextToken());
            int[] a = new int [N];
            int[] freq = new int[1000001];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                a[i] = Integer.parseInt(st.nextToken());
                freq[a[i]]++;
            }
            int ans = 0;
            for(int i = 0; i<N; i++){
                ans += Math.max(0, (freq[a[i]] - 1) * (freq[a[i]] - 2));
                for(int b = 2; a[i] * b * b <= 1000000; b++){
                    ans += freq[a[i] * b] * freq[a[i] * b * b];
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