import java.io.*;
import java.util.*;

public class RunningMiles{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] a = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<=N; i++) a[i] = Integer.parseInt(st.nextToken());
            //after some thinking, i reached the observaton that l and r must be two of the beauty values or else we can improve the answer
            //then what we can do is fix the middle element and find two elements, one before such that a[i] + i is maximal
            //and one after such that a[i] - i
            //why? say (where front and back are ends) front has value x, then back has value z...then the middle has value y
            //then the contribution is x + y + z -(ind(z) - ind(x))  (x + ind(x)) + y + (z - ind(z))
            int[] p_max = new int[N + 1];
            int[] s_max = new int[N + 2];
            Arrays.fill(p_max, Integer.MIN_VALUE);
            Arrays.fill(s_max, Integer.MIN_VALUE);
            for(int i = 1; i<=N; i++){
                p_max[i] = Math.max(p_max[i - 1], a[i] + i);
            }
            for(int i = N; i >= 1; i--){
                s_max[i] = Math.max(s_max[i + 1], a[i] - i);
            }
            long ans = 0;
            for(int i = 2; i<N; i++){
                ans = Math.max(ans, a[i] + p_max[i - 1] + s_max[i + 1]);
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
