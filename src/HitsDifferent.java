import java.io.*;
import java.util.*;

public class HitsDifferent{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] a = new int[2024][];
            a[0] = new int[1];
            int num = 1;
            //construct a 2023 array with the pyramid leaf the emptys as 0s
            for(int i = 1; i<= 2023; i++){
                a[i] = new int[i + 1];
                for(int j = 1; j<=i; j++){
                    a[i][j] = num++;
                }
            }
            long[][] ps = new long[2025][];
            ps[0] = new long[1];
            for(int i = 1; i<= 2023; i++){
                ps[i] = new long[i + 1];
                int[] arr = a[i];
                for(int j = 1; j<= i; j++){
                    //1414
                    //if(j == i + 100) break;
                    ps[i][j] = ps[i][j - 1] + (long) arr[j] * arr[j];//prefix "squares" now calculated
                }
            }
            int row = -1;
            int col = -1;
            for(int i = 1; i<= 2023; i++){
                for(int j = 1; j<=i; j++){
                    if(a[i][j] == N){
                        row = i;
                        col = j;//find where N is
                    }
                }
            }
            long ans = 0;
            int other_col = col;
            for(int i = row; i >= 0; i--){
                if(col > i){
                    col = i;
                }
                else if(ps[i][col] == 0){
                    col = i;
                }
                ans += ps[i][col] - ps[i][Math.max(other_col, 1) - 1];
                other_col--;
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
