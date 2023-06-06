import java.io.*;
import java.util.*;
import java.awt.Point;

public class DilucAndKaeya {
    //first bad observation: i claimed that like the strings have to be of equal length...very very very very very very very very very wrong
    //should've drawn out my thoughts more clearly to not make this stupid mistake
    //anyways lets just focus on solving the  question for the entire string
    //i also thought of putting the indicies of K and seeing how often they sort of occur throughout the string
    //there is really no point in doing this again if you just sort of draw it out, ull see how useless this is
    //K's can be spaced out as much ...so the indicies don't do anything
    //again these were all very very very bad thoughts....HMPH
    //here's how we should think: we are solving the question for a all prefixes...well let's first even see if we can solve the question for the entire string
    //as that problem right now isn't even obvious
    //notice we can split DKDKDDDDK this like DKD|KDD|DDK...this is split into 3 , what's interesting here that if you take a look at the endpoitns of each block
    //which are 3 6 9, you'll notice that the prefix of the endpoints so the 3rd prefix 6th and 9th ...they must have the same ratio of D's and K's as the block
    //this is already very intuitive, but the proof is that the prefix of an endpoint in a block..will be composed of blocks that add up with the same ration
    //now you will notice this if we are now answering for prefixes if we are going to be able to split into blocks that have the same ratio, then the entire prefix also must have the same ratio
    //this is just using an observation of before, so we really what we are doing is for a given prefix i...how many prefixes have the same ratio as the given prefix including prefix i
    //that will be our answer for the ith prefix
    //anyways we solved this by considering how to answer the whole string, then realized that the prefix endpoint blocks have to obv have the same ratio and if we are consideirng a prerfix
    //it must be divided into those blocks  that have the same ratio, but really those blocks are just prefixes
    //pretty nice how we used an example to easily arrive at answer, should've not thought abstractly rather look at the example and make those observations
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            HashMap<Point, Integer> freq = new HashMap<>();
            int d = 0;
            int k = 0;
            for(int i = 0; i<S.length(); i++){
                if(S.charAt(i) == 'D') d++;
                else k++;
                int g = gcd(d, k);
                freq.put(new Point(d/g, k/g), freq.getOrDefault(new Point(d/g, k/g), 0) + 1);
                pw.print(freq.get(new Point(d/g, k/g)) + " ");
            }
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
    public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

}