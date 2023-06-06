import java.io.*;
import java.util.*;


public class MexString {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        ArrayList<String> strs =  new ArrayList<>();
        for(int i = 0; i<26; i++) strs.add("" + (char) (i + 97));
        for(int i = 0; i<26; i++){
            for(int j = 0; j<26;j++){
                strs.add("" + (char)(i + 97) + (char)(j +97));
            }
        }
        for(int i = 0; i<26; i++){
            for(int j = 0; j<26; j++){
                for(int k = 0; k<26; k++){
                    strs.add("" + (char)(i + 97) + (char)(j +97) + (char)(k + 97));
                }
            }
        }
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            //only that many possible strings...no way the answer is more than length 3...max amount of substrings of length 3
            //is 997...there are way more theoretical possible length 3 substrings in the alphabet ingeneral
            //complexity 26^3 for each one check if it's the string .contains is O(N) 10^3 * 26^3....getting close but should work...if this is slow i have plans to optimize
            //instead of doing .contains we should probably just go through all the substrings first and add them into hashset faster and less risk for penalty
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i<N; i++){
                set.add("" + S.charAt(i));
                if(i + 1<N){
                    set.add("" + S.charAt(i) + S.charAt(i + 1));
                }
                if(i + 2 < N){
                    set.add("" +S.charAt(i) + S.charAt(i + 1) + S.charAt(i + 2));
                }
            }
            String ans = "";
            for(int i = 0; i<strs.size(); i++){
                if(!set.contains(strs.get(i))){
                    ans = strs.get(i);
                    break;
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