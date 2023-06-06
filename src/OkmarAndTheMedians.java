import java.io.*;
import java.util.*;

public class OkmarAndMedians {
    static class MultiSet<E> {
        // TreeSet Version
        TreeMap<E, Integer> Counter = new TreeMap<E, Integer> ();
        private int size = 0;
        public void add(E key) { Counter.put(key, Counter.getOrDefault(key, 0) + 1); size ++; }
        public boolean remove(E key) {
            Integer value = Counter.get(key);
            if (value == null) return false;
            if (value == 1) Counter.remove(key);
            else Counter.put(key, value - 1);
            size --;
            return true;
        }
        public void deleteAll(E key) { size -= Counter.remove(key); }
        public boolean contains(E item) { return Counter.containsKey(item); }
        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        public E ceiling(E item) { return Counter.ceilingKey(item); }
        public E floor(E item) { return Counter.floorKey(item); }
        public E higher(E item) { return Counter.higherKey(item); }
        public E lower(E item) { return Counter.lowerKey(item); }
        public E first() { return Counter.firstKey(); }
        public E last() { return Counter.lastKey(); }
        public String toString() { return Counter.toString(); }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] b = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) b[i] = Integer.parseInt(st.nextToken());
            TreeSet<Integer> set = new TreeSet<>();
            HashMap<Integer, Integer> freq = new HashMap<>();
            boolean good = true;
            //2 1 2 3
            //desired = 1, previous = 2 set{2}
            //desired = 2, previous 1 , set {1,2}
            //desired = 3, previous = 2, set {1,2}
            //set {1,2}
            for (int i = 0; i < N; i++) {
                if (set.size() == 0) {
                    set.add(b[i]);
                    freq.put(b[i], 1);
                    continue;
                }
                int desired_median = b[i];
                int previous_median = b[i - 1];
                //pw.println(i + " "+ desired_median + " "+ previous_median);
                //intuitvely the desired median has to be "close" to the previous median
                if (desired_median > previous_median) {
                    //we invalidate if we desired median is too big
                    if(freq.get(previous_median) == 1){
                        //if freq of previous median is 1 we check set.higher for the closest one to the previous median
                        if(set.higher(previous_median) != null && desired_median > set.higher(previous_median)){
                            //make sure it's null and if it's too much then it's impossible
                           good = false; break;
                        }
                    }else{
                      //1  3  2 2 2  5 6 even it's really freq.get(previous)median)/2'
                        //in this case we are sure that it does not work since it isn't equal to it, equal will always work
                        if(freq.get(previous_median)/2 > 1) {good = false; break;}
                    }
                } else if (desired_median < previous_median) {
                    //no need to check if desired median is the same as prevous median, it works perfectly fine
                    if(freq.get(previous_median) == 1){
                        //same idea as higher just ackwards
                        if(set.lower(previous_median) != null && set.lower(previous_median) > desired_median){
                            good = false; break;
                        }
                    }else{
                        if(freq.get(previous_median)/2 - 1 > 1) {good = false; break;}
                    }
                }
                //if(i == 3) pw.println("passed");
                set.add(b[i]);
                freq.put(b[i], freq.getOrDefault(b[i], 0) + 1);
            }
            pw.println(good ? "YES":"NO");
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
