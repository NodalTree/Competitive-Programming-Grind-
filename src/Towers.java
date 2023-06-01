import java.io.*;
import java.util.*;
//towers
public class Tower {
    public static class Pair implements Comparable<Pair> {
        public int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair other) {
            return other.first - this.first;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            Pair[] blocks = new Pair[N];
            int[] ind = new int[N];//takes an index referring to block and spits out which tower it went on
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                blocks[i] = new Pair(Integer.parseInt(st.nextToken()), i);
            }
            Arrays.sort(blocks);
            PriorityQueue<Pair> towers = new PriorityQueue<>((a, b) -> a.first - b.first);
            for (int i = 0; i < M; i++) {
                towers.add(new Pair(0, i));
            }
            for (int i = 0; i < blocks.length; i++) {
                //get current smallest tower
                Pair current_smallest = towers.remove();
                //add the biggest block possible
                current_smallest.first += blocks[i].first;
                //then push it back in the M towers
                towers.add(current_smallest);
                //we placed the index of the block into the index of the tower, for outputting purposes
                ind[blocks[i].second] = current_smallest.second + 1;
            }
            int max_height = Integer.MIN_VALUE;
            for (Pair p : towers) {
                max_height = Math.max(max_height, p.first);
            }
            boolean good = max_height - towers.peek().first <= X;
            if (good) {
                pw.println("YES");
                for(int i = 0; i<N; i++){
                    pw.print(ind[i] + " ");
                }
                pw.println();
            }else{
                pw.println("NO");
            }
        }
        pw.close();
    }
}
