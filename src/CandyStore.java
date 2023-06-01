import java.io.*;
import java.util.*;

public class CandyStore{
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
            //tried to find ext things that i didnt need
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                a[i] = Integer.parseInt(st.nextToken());
                b[i] = Integer.parseInt(st.nextToken());
            }
            int ans = 0;
            for(int i = 0; i<N; i++){
                int gcd = a[i] * b[i];
                int lcm = b[i];
                i++;
                while(i < N){
                    gcd = gcd(Math.max(a[i] * b[i], gcd), Math.min(a[i] * b[i], gcd));
                    lcm = lcm * lcm(Math.max(lcm, b[i]), Math.min(lcm, b[i]));
                    if(gcd % lcm == 0) i++;//we can continue the prefix
                    else break;
                }
                ans++;
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
    static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
    static int lcm(int a, int b){
        return a*b/gcd(a,b);
    }

}