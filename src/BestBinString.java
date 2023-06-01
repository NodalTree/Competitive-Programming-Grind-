import java.io.*;
import java.util.*;

public class BestBinString{
    //use test cases for each question mark u OBV just put the last thing u saw bc it's always optimal to have long ranges of 1's or 0's
    //like 1000101010 is less optimal than 111111100000000 just basically minimize the amount of non-increainsg blocks you have
    //you do this by keeping the ? the same as the thing u just saw so if u ust saw a 1 make it the ?
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            char[] s = st.nextToken().toCharArray();
            char just_saw = '0';//edge case u all of them are question marks
            for(int i = 0; i<s.length; i++){
                if(s[i] != '?'){
                    just_saw = s[i];//to find first just_saw
                    break;
                }
            }
            for(int i = 0; i<s.length; i++){
                if(s[i] == '?') s[i] = just_saw;
                else just_saw = s[i];
            }
            for(char ch: s) pw.print(""+ ch);
            pw.println();
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
