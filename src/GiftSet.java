import java.io.*;
import java.util.*;
import java.awt.Point;

public class GiftSet{
  //https://codeforces.com/contest/1538/problem/G
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        /*you'll notice that there's a lot a symmetry in this problem, so we apply a common "trick" to making thinking easier
        if(a < b) swap(a,b)
        okay, so binary search on answer ob works if k gift sets work then obv k - 1 work: this is really just byh intuition
        and think about if u can do it in k sets then removing 1 would also work lol, so it's extremely intuitve, no need for fomral proof
        so we want to know if n gift sets work...interesting enough you'll notice we easier problem because of how we dealt with a and b
        since a < b, then we know x and y havce to decrease by at least a * n, thus x-= a* n  an y -= a * n
        //now thats out of the way we have a new problem we can decrease x and y by either 0 or the difference
        y - x = diff
        obv to deal with this we should decresase y till it get to 0, then decreacse x till it gets to 0
        ...the 0 is nice because it makes operations indepedent...like we can just focus on an y and we can just focus on an x
        //obv we know we can use as much y as possible and as much x as possible, thus this is optimal
        thus we need x/diff + y/diff >= n
        and this is the check function
        big takeways here is thinking of minimum number and maximum number instead of a and b (common trick)
        then we notice that the numbers have to decrease by at least the min right n times...
        x - a , y - a
        x - 2 * a, y - 2 * a,
        ....................
        x - k * a, y - k * a
        now we have to finish off and decrease this by either 0 or the difference...the reason for this is bc
        go up in the earlier operations we can decrease either one by 0 or the difference, clearly just do
        here it's just easier to think of a new problem where a = 0
        so ig the really cool part was just takin gout that min and noticing a nice thing with a being 0
        dk how to think of that tbh with u, crazy intuition from neal wu when i watched him for the problelm
         */

        int T = fs.nextInt();
        while (T-- > 0) {
          long x = fs.nextLong();
          long y = fs.nextLong();
          long a = fs.nextLong();
          long b = fs.nextLong();
          long min = Math.min(x,y); long max = Math.max(x,y);
          long min2 = Math.min(a,b); long max2 = Math.max(a,b);
          x = min; y = max;
          a = min2; b = max2;
          if(a == b){
              //in this case , then it doesn't matter simply x/a, x is the limiting factor to making gift sets since x < y
              pw.println(x/a);
              continue;
          }
          long l = 0;
          long r = (x + y)/(a + b);
          while(l < r){
              long mid = l + (r - l + 1)/2;
              if(f(mid, x, y, a, b)){
                  l = mid;
              }else{
                  r = mid - 1;
              }
          }
          //no assertion
          pw.println(l);
        }

        pw.close();
    }
    public static boolean  f(long n, long x, long y, long a, long b) {
        long diff = b - a;
        //we know these numbers have to decrease  by at least a * n for it to work so might as well
        x -= a* n;
        y -= a * n;
        if(x < 0 || y < 0) return false;//obv if it can't even do this , then get out of here
        //now we have the same problem but not a and b are (0, diff), which means
        //the max amount of gift sets can make is obv (a + b)/diff...if this is sufficiently large ( >= n), then it works
        return x/diff + y/diff >= n;//dont add liek fractions next time noob
    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

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

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
    static int primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) { factors.add(n); }
        return factors.size();
    }

    public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

}
