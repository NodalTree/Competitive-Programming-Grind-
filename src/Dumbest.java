import java.io.*;
import java.util.*;

public class ProbB{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++) a[i] = Integer.parseInt(st.nextToken());
            boolean good = true;
            int max = -101;
            for(int i = 0; i<N; i++) {
            	if(a[i] < 0) {
            		good = false;
            		break;
            	}
            	max = Math.max(max, a[i]);
            }
            if(!good) {
            	pw.println("NO");
            	continue;
            }else {
            	pw.println("YES");
            	pw.println(max + 1);
            	for(int i = 0; i<=max; i++) pw.print(i + " ");
            	pw.println();
            }

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
    public static boolean add(ArrayList<Integer> a, TreeSet<Integer> set){
    	if(a.size() > 300) return false;
    	for(int i: a) set.add(i);
        boolean good = true;
        int size = a.size();
        for(int i = 0; i<Math.min(300, size); i++){
            for(int j = i + 1; j<Math.min(300, size); j++){
                int num = Math.abs(a.get(i) - a.get(j));
                if(set.size() > 300 || a.size() > 300) return false;
                if(!set.contains(num)){
                    a.add(num);
                    set.add(num);
                    good = false;
                }
            }
        }
        return good && a.size() <= 300;
    }

}
