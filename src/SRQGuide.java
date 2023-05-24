import java.io.*;
import java.util.*;

public class SRQGuide{
    public static class Update{
        public int l, r, v;
        public Update(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }
    }
    public static class Query{
        public int l, r;
        public Query(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
    static long [] seg_val, seg_length;
    static long [] seg_sum;
    static Update [] updates;
    static TreeSet <Integer> points = new TreeSet <>();
    static HashMap <Integer, Integer> compress = new HashMap<>();
    static HashMap <Integer, Integer> decompress = new HashMap <>();
    static Query [] queries;
    static int N, Q;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        updates = new Update [N];
        queries = new Query [N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            updates[i] = new Update(l, r, v);
            points.add(l);
            points.add(r);
        }
        for(int i = 0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            queries[i] = new Query(l, r);
            points.add(l);
            points.add(r);
        }
        int curr_ind = 1;
        for(int i: points) {
            compress.put(i, curr_ind);
            decompress.put(curr_ind, i);
            curr_ind++;
        }
        seg_val = new long [points.size() + 2];
        seg_length = new long [points.size() + 2];
        for(Update upd: updates) {
            seg_val[compress.get(upd.l)] += upd.v;
            seg_val[compress.get(upd.r)] -= upd.v;
        }
        for(int i = 1; i<=points.size(); i++) {
            seg_val[i] += seg_val[i - 1];
        }

        for(int i = 1; i<=points.size(); i++) {
            seg_length[i] = decompress.getOrDefault(i + 1, decompress.get(i)) - decompress.get(i);
        }
        seg_sum = new long [points.size() + 2];
        for(int i = 1; i<=points.size(); i++) {
            seg_sum[i] = seg_sum[i - 1] + (seg_val[i] * seg_length[i]);
        }
        for(int i = 0; i<Q; i++) {
            int l = compress.get(queries[i].l) - 1;
            int r = compress.get(queries[i].r) - 1;
            pw.println(seg_sum[r] - seg_sum[l]);
        }
        pw.close();
	    /*
      5 5
3 7 2
1 10 4
1 6 10
0 4 10
6 7 1
5 7
0 2
5 9
1 6
4 9
*/
      /*
      23
34
31
106
47
*/

    }
}

