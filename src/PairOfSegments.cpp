import java.io.*;
import java.util.*;
public class PairOfSegments {
    static class Pair{
        public int a,b;
        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
    static class Segment implements Comparable<Segment>{
        public int l, r;
        public Segment(int l, int r){
            this.l = l;
            this.r = r;
        }
        public int compareTo(Segment other){
            return this.r - other.r;//we only care about segments that end fastest
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st  = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            Pair[] segs = new Pair[N];
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                segs[i] = new Pair(a, b);
            }
            //N is nicely 2000, so we can combine segments
            ArrayList<Segment> combined_segs = new ArrayList<>();
            //combine every segment
            for(int i = 0; i<N; i++){
                for(int j = i + 1; j<N; j++){
                    if(intersect(segs[i], segs[j]))
                    combined_segs.add(combine(segs[i], segs[j]));
                }
            }
            //now we've reduced this problem to the generic movie festival on cses
            Collections.sort(combined_segs);
            int r = Integer.MIN_VALUE;
            int count = 0;
            for(Segment s: combined_segs){
                if(s.l > r){
                    count++;
                    r = s.r;
                }
            }

            pw.println(N - 2 * count);
        }
        pw.close();

    }
    public static Segment combine(Pair a, Pair b){
        return new Segment(Math.min(a.a, b.a), Math.max(b.b, a.b));
    }
    public static boolean intersect(Pair a, Pair b){
        if(a.a <= b.a){
            return b.a <= a.b;
        }else{
            return a.a <= b.b;
        }
    }
}
