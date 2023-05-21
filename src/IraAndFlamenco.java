import java.io.*;
import java.util.*;


public class IraAndFlamenco {
    //https://codeforces.com/contest/1833/problem/F
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            HashMap<Integer, Integer> freq = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                a[i] = Integer.parseInt(st.nextToken());
                freq.put(a[i], freq.getOrDefault(a[i], 0) + 1);
            }
            a = new int[freq.size() + 1];//resize
            int index = 1;
            for(int key: freq.keySet()){
                a[index++] = key;
            }
            ruffleSort(a);
            long[] pref_prod = new long[a.length];
            Arrays.fill(pref_prod, 1);
            for(int i = 1;i< pref_prod.length; i++){
                pref_prod[i] = mul(pref_prod[i - 1], (long) freq.get(a[i]));
            }
            long ans = 0;
            for(int i = 1; i + M - 1 < a.length; i++){
                if(a[i + M - 1] - a[i] < M){
                    ans = add(ans, div(pref_prod[i + M - 1], pref_prod[i - 1]));
                }
            }
            //pw.println(Arrays.toString(a));
            // pw.println(Arrays.toString(pref_prod));
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