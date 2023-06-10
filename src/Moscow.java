import java.io.*;
import java.util.*;
import java.awt.Point;

public class MoscowGorillas {
    public static void main(String[] args) throws IOException {
        //FastScanner fs = new FastScanner();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] a = new int[N + 1];
        int[] b = new int[N + 1];
        int[] indb = new int[N + 1];
        int[] inda = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) b[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            indb[b[i]] = i;
            inda[a[i]] = i;
        }
        long ans = 0;
        int sa = inda[1];
        int sb = indb[1];
        int ea = inda[1];
        int eb = indb[1];
        //bc  1 is sort of an edge case cause we've seen no mexes before, we will handle it seperately
        //sa to ea is the range we need to include, for 2 it's obviously going to be just 1
        //idea is to fix mex, and then thinking about solution isn't that bad
        for (int mex = 2; mex <= N; mex++) {
            int left_a = 0;
            int left_b = 0;
            int right_a = 0;
            int right_b = 0;
            //left_ a will be how much can we expand the range sa to sb to the left...rest of the the names follow same convention
            //we can expand that range until we don't see the mex
            //consider the mex to the left of sa, then we must use this + 1
            if (inda[mex] < sa) {
                left_a = inda[mex] + 1;//you can only expand to left that much
                //however this means you can expand to the right all the way to N
                right_a = N;
            } else {
                //in this case you can only expand the right to where the mex is - 1
                right_a = inda[mex] - 1;
                left_a = 1;//but you ca now expand to left as much as you want
            }
            //repeat for b
            if (indb[mex] < sb) {
                left_b = indb[mex] + 1;//you can only expand to left that much
                //however this means you can expand to the right all the way to N
                right_b = N;
            } else {
                //in this case you can only expand the right to where the mex is - 1
                right_b = indb[mex] - 1;
                left_b = 1;//but you can now expand to left as much as you want
            }
            //now we must intersect these ranges, so since it must include sa and sb we take the furthest left
            //then we can expand this as much to the left, however as much to left till max(left_a, left_b)
            //right side works same way by symmetry ...these will be all our choices
            //just some math
            ans += (long) Math.max(0, (Math.min(sa, sb) - Math.max(left_a, left_b) + 1)) * Math.max(0,(-Math.max(ea, eb) + Math.min(right_a, right_b) + 1));
            //pw.println(mex + " "+ ans);
            sa = Math.min(inda[mex], sa);
            sb = Math.min(indb[mex], sb);
            ea = Math.max(inda[mex], ea);
            eb = Math.max(indb[mex], eb);
            //for a fixed mex we know that the range that must be included in a must encompass  all mexes seen before

        }
        //handling one, lets do the the ones on the left
        ans += (long) Math.min(inda[1], indb[1]) * (Math.min(inda[1], indb[1]) - 1) / 2;
        ans += (long) (N - Math.max(inda[1], indb[1])) * (N - Math.max(inda[1], indb[1]) + 1) / 2;
        ans += (long) Math.abs(inda[1] - indb[1]) * (Math.abs(inda[1] - indb[1]) - 1) / 2;
        ans++;
        pw.println(ans);
        pw.close();
    }

    public static long count_haha(String S) {
        long out = 0;
        for (int i = 0; i + 3 < S.length(); i++) {
            if (S.substring(i, i + 4).equals("haha")) out++;
        }
        return out;
    }

    static class Variable {
        public String first, last, str;
        public long hahas;

        public Variable(String first, String last, String str, long hahas) {
            this.first = first;
            this.last = last;
            this.str = str;
            cutFirst(first);
            cutLast(last);
            this.hahas = hahas;
            hahas = count_haha(str);
        }

        public void cutFirst(String s) {
            if (s.length() > 3) s = s.substring(0, 3);
        }

        public void cutLast(String s) {
            if (s.length() > 3) s = s.substring(s.length() - 3);
        }

        public String str() {
            return first + " " + last + " " + str + " " + hahas;
        }

    }

    public static String cutFirst(String s) {
        if (s.length() > 3) s = s.substring(0, 3);
        return s;
    }

    public static String cutLast(String s) {
        if (s.length() > 3) s = s.substring(s.length() - 3);
        return s;
    }

    static Variable combine(Variable a, Variable b) {
        //String first = cutFirst(a.first + b.last);
        //String last = cutLast(a.first + b.last);
        long hahas = a.hahas + b.hahas + count_haha(a.first + b.last);
        return new Variable(a.first, b.last, "", hahas);
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

}
