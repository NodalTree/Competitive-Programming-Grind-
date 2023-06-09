import java.io.*;
import java.util.*;
import java.awt.Point;

public class ProbA{
	//we are dealing with a function and we want to know how many digit changes occur between l and r
	//whats interesting about these types of probems is we can sort of using that prefix sum idea
	//how many digit changes occur from 0 tor - 1 r? define this as a function f
	//well if we ca find this function then we can do f(r) - f(l)..now slight nuance
	//we don't want f(r) - f(l - 1) bc we are stating that the function counts the amount of changes so like r - 1 to r... for 0 to r
	//then for 0 to l we go from l  - 1 to l, so we don't want all of this we only wanna start from l to l + 1
	//so not really prefix sums, but the idea is that we can think of the function independlty from l to r
	//this type of thinking is similar to  C and many other problems 
	//we will find out that this function is actually not hard to find
	//we can think of it as how many times do the one digits change
	//well all the time so it would just be r times
	//well the tens digit changes a tenth of a time that the one digit changes so floor(r/10)
	//well the hundreds digit changes a /100th of a time that hte one digit changes so floor(r/10^2)
	//so in general we note that if we are doing some digit 10^k it changes floor(r/10^k)
	//k can only get as big as 9 
	//sort of just increase a number in ur head and ull see that this very intuitive
	//so for a function we go from k is = 0 to 9 and just add
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int T = fs.nextInt();
        while (T-- > 0) {
        	long l = fs.nextLong();
        	long r = fs.nextLong();
        	pw.println(f(r) - f(l));//looks trivial, but the main idea is that sort of like when we have range l to r , think of the range from beg to r and see if u ca make function for that
        	//then ull see that u can do this subtraction to make it work
            
        }
        
        pw.close();
    }
    public static long f(long x) {
    	long ans = 0;
    	for(int k = 0; k<=9; k++) {
    		ans += x/(long)Math.pow(10, k);//make sure to cast this to long bruh
    	}
    	return ans;
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
