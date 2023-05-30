import java.io.*;
import java.util.*;

public class BossCounts{
  //div 2 D not ez fs, upsolved 
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
            for(int i = 0;i<N; i++) a[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++) b[i] = Integer.parseInt(st.nextToken());
            int SQRMAX = (int) Math.sqrt(2 * N);
            int[][] freq = new int[SQRMAX + 1][N + 1];
            ArrayList<Integer>[] equal_min = new ArrayList[N + 1];
            for(int i = 1; i<= SQRMAX; i++) equal_min[i] = new ArrayList<>();
            for(int i = 0; i<N; i++){
                if(a[i] <= SQRMAX)
                freq[a[i]][b[i]]++;
            }
            for(int i = 0; i<N; i++){
                if(a[i] <= SQRMAX){
                    equal_min[a[i]].add(b[i]);
                }
            }
            //pw.println(SQRMAX);
            long ans = 0;//sqrt(2 * N) max
            for(int min = 1; min <= SQRMAX;  min++){
                //by observation min(a_i, a_j) is bounded by 2 * sqrt(2 * N)
                for(int i = 0; i<N; i++){
                    //this bound allows you now to linearly go thru the pairs
                    if(a[i] <= min) continue;//skip this bc min has to be smaller by def, we will deal with duplicates seperaly cuz annoying
                    //this is very easy, you know that the product is now fixed
                    int product = min * a[i];
                    if(product > 2 * N) continue;//assert this bound on the product
                    int other_b = product - b[i];//the other b that corresponds to min is derived by equation
                    //pw.println(product + " "+ other_b);
                    if(other_b > N || other_b <= 0) continue;
                    ans += freq[min][other_b];
                }
            }
            //deal with equal values for a_i and a_j seperately
            for(int i = 1; i<= SQRMAX; i++){
                ArrayList<Integer> bs = equal_min[i];//get list of bs
                if(bs.size() == 0) continue;
                //pw.println(bs.size());
                int[] count = new int[N + 1];//freq array
                //easier to count frequency as u go
                for(int j = 0; j<bs.size(); j++){
                    int need = i * i - bs.get(j);//need this and invalidate if it's out of bounds
                    if(need <= 0 || need > N) {count[bs.get(j)]++;continue;}
                    //pw.println("we got here "+ need);
                    ans += count[need];
                    count[bs.get(j)]++;//keep track of frequency as u go on
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
