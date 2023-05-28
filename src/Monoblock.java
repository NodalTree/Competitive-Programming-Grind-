import java.io.*;
import java.util.*;

public class MonoBlock {
  //https://codeforces.com/problemset/problem/1715/C
  //video link by me:https://youtu.be/Dj6041c3qJ8
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] a = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) a[i] = Integer.parseInt(st.nextToken());
        long ans = 0;
        for(int i = 1; i + 1<=N; i++){
            if(a[i] != a[i + 1]){
                ans += (long) i * ( N - i);
            }
        }
        ans += (long) (N + 1) * (N)/2;//adding amount of subarrays
       // pw.println("This is orginal answer " + ans);
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            boolean change_left = false;
            if(index - 1 >= 0){
                change_left = a[index - 1] != a[index];
            }
            boolean change_right = false;
            if(index + 1 <= N){
                change_right = a[index + 1] != a[index];
            }
            a[index] = x;//repalce the value
            if(index  - 1 >= 0 &&  a[index - 1] != a[index]){
                if(!change_left) ans += (long) (index - 1) * (N - (index - 1));
            }else if(change_left){
                ans -= (long) (index - 1) * (N - (index - 1));
            }
            if(index  + 1 <= N && a[index + 1] != a[index]){
                if(!change_right) ans += (long) (index) * (N - (index));
            }else if(change_right){
                ans -= (long) (index) * (N - (index));
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
