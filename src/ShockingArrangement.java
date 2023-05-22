import java.io.*;
import java.util.*;

public class ShockingArrangement {
    public static void main(String[] args) throws IOException{
        //https://codeforces.com/problemset/problem/1798/D
        //short impl but reaaaaally good problem 1600 rated, proof is beautiful
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int[] a = new int[N];
            ArrayList<Integer> pos = new ArrayList<>();
            ArrayList<Integer> neg = new ArrayList<>();
            ArrayList<Long> perm = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                a[i] = Integer.parseInt(st.nextToken());
                if(a[i] >= 0) pos.add(a[i]);
                else neg.add(a[i]);
                min = Math.min(a[i], min);
                max = Math.max(a[i], max);
            }
            if(min == max){
                pw.println("NO");//same line 0 impossible this implies 0 array
                continue;
            }
            long prefix_sum = 0;
            for(int i = 0; i<N; i++){
                long element;
                if(prefix_sum <= 0){
                    element = pos.remove(pos.size() - 1);//removing from back O(1)
                }else{
                    element = neg.remove(neg.size() - 1);//removing from back O(1)
                }
                prefix_sum += element;
                perm.add(element);
            }
            pw.println("YES");
            for(long i: perm) pw.print(i + " ");
            pw.println();
        }
        pw.close();
    }
}
