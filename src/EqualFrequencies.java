import java.io.*;
import java.util.*;

public class EqualFrequencies {
    /*
      Lets fix the unknown information: lets say the equal frequency is x. Then N (mod x) = 0. This implies
      for the common frequency we only have to try the divisors of N which is <=  2* sqrt(N). For each fixed
      divisor, we can try answering the question. Lets do examples to help us think clearly.
      Amount of letters needed = A = N/x <= 26 (if not obv skip)
      N = 36, x = 4, A = 36/4 = 9, H =6 (for case 1)
      Call the amount of letters we have in our String S,  H.
      We do some cases A > H, this means the amount of letters we have is less than the amount of letters we need.
      This means the frequency of some letters is > i...if they were all less than < i, then max(frequency) (which is less than i)
      times H is less than A * i. So for the ones under we can store what we need in a sum S, and then deal with the ones over
      optimally (should be ez to code)
       a over int oist_from_x = over - x over - Math.min(dist_x, S) S  - Math.min(dist_x, S)
       b over
       c over
       d under x- under
       e under x - under  S
       f under x - under

      A <= H...
      H = 11
      a over
      b over
      c over
      d under sum up x - under for all unders
      e under
      f over
      g under
      h under
      i under
      j over
      k under

     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            char[] s = st.nextToken().toCharArray();
            HashMap<Character, Integer> freq_s = new HashMap<>();
            for (int i = 0; i < N; i++) {
                freq_s.put(s[i], freq_s.getOrDefault(s[i], 0) + 1);
            }
            int ans = Integer.MAX_VALUE;
            int amountHave = freq_s.size();
            for (int frequency = 1; frequency <= N; frequency++) {
                //for a fixed frequency it has to be a divisor of n
                if (N % frequency != 0 || N / frequency > 26)
                    continue;//if freq is not a divisor, then it obv can't work or if we need more than 26 letters
                int curr = 0;
                int amount_needed = N / frequency;
                if (amountHave >= amount_needed) {
                    //in this case we have more letters used than we need, then at least amoount_needed are under x
                    for (char key : freq_s.keySet()) {
                        if (freq_s.get(key) < frequency) {
                            curr += frequency - freq_s.get(key);//basically if any frequency is under, which obv there will be
                            //ten we knw we need freq - key changes to the string to make this work...they come form the over ones
                        }
                    }
                } else {
                    //in this case we have less letters used than we need, so obv some of them have more than frequency... just take the extras from the overs and it;ll work nicely
                    for (char key : freq_s.keySet()) {
                        if (freq_s.get(key) > frequency) {
                            curr += freq_s.get(key) - frequency;
                        }
                    }
                }
                ans = Math.min(ans, curr);
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
