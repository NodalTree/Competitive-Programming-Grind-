import java.io.*;
import java.util.*;

//don't know why this 1100 was hard for me anyways, now i get it
//where i went wrong: i was trying to fix T's and proceed with the question
//if you do this it's like okay i fixed T, which M should I use ...intuitively the closest M possible right
//so my intuition was actually right, but then after it was like okay which other T should I use.........
//that's sort of where my thought process got stuck
//well you should use a t that's further than this M right and so we greedily take the smallest T that is further than this M, so we leave the bigger ones
//however this was straight up wrong: TMTMTT, failed on that test case, but then passed on this one TTMMTT
//Okay so now we know that the logic doesn't work but why: well we are assuming that the smallest T that is further than this M will be and end T of this subsequence
//thats not ness true, and in fact what our logic is doing is it's scanning T's from left to right and assuming the leftmost T's (since i's a left and right scan)
//will be end T's of TMT subsequences: Think about it logically the 2nd leftmost T is an endpoint of a TMT subsequence???? we are saying the 2nd left most should e use as a righmost end point
//this is very counter intuitive
//intuitively if i were to pick an end T i would pick the rightmost T, so it looks like for every M we should always pick the leftmost and rightmost T
//which now inspires the solution to instead acc fix M and go thru it, but here is how I could've corrected my solution to think like the editorial
//i realized that the optimal T would have to be the rightmost ...that's surely not going to be used as a start T, so why not just use it as an end T
//the problem intuitively has a symmetry aspect to it
//all tho this is getting pretty werid...fixing M, then thinking about it was much clear and intuitive than what I did

public class TMTDocument {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            ArrayList<Integer> t = new ArrayList<>();
            ArrayList<Integer> m = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (S.charAt(i) == 'T') {
                    t.add(i);
                } else {
                    m.add(i);
                }
            }
            if (t.size() != 2 * m.size()) {
                pw.println("NO");
                continue;
            }
            boolean good = true;
            for (int i = 0; i < m.size(); i++) {
                //use the left most t , and closest right most t
                if (t.get(i) > m.get(i) || t.get(i + m.size()) < m.get(i)) {
                    good = false;
                    break;
                }
            }
            pw.println(good ? "YES" : "NO");

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
