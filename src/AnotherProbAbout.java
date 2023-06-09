import java.io.*;
import java.util.*;
import java.awt.Point;

public class ProbA{
	//had some difficulties with eclipse so this shouldve been faster in contest
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int T = fs.nextInt();
        while (T-- > 0) {
        	//literally was on eboolean away from AC, anyways neal wu had an easier impl find min by seeing if they are over gcd 
            int a = fs.nextInt();
            int b = fs.nextInt();
            int k = fs.nextInt();
            //wait  the relatively prime check is still wrong because really the minimum it takes is eetting them down to their gcds
            //remeber we said the equal has to be adivsor of the gcd ..ifboth greater than gcd it takes two operatons
            //honestly i like what neal wu did he was like okay well whats the minium operations to make them equal...oh get them down to their gcd we ca do that in 1 if it's already there or not etc..
            //we can handle when both of them are equal thats a dumb case and it's no...otherwise see if we get 1 and the min is greater than 1 everything else is deterined by sum 
            long sum = primeFactors(a) + primeFactors(b);
            int g  = gcd(a,b);
            if(k == 1 && (a == b || a > g && b > g)) {
            	//from sample 
            	pw.println("NO");
            }else if(sum >= k) {
            	pw.println("YES");
            }else {
            	pw.println("NO");
            }
           //ah was very close on this one, prob should've thought of a counter example 
           /*
            * this is tricky, my logic was ALMOST right but i 
            * assumed the amount of operations we can achieve this in is 1 to max
            * ; however, some need at least 2, so for the ones that need at least 2 if we get k = 1 we shoud print no
            * had to stop doing the contest after my wrong submission, so maybe if i return back i think of this 
            * how to know if it's . for the ones that are already the same we already know it's 0, we did figure that out by sample 
            * counter example both primes (17, 7) logic fails we need two operatons..however for like 5 40 we need 1
            * so if their gcd is 1 and we have k = 1, then it's impossible 
            * still an edge case with this their gcd could be 1, but both of them would have to be not on, so the min would have to not one
            */
            
        }
        
        pw.close();
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
