import java.io.*;
import java.util.*;
import java.awt.Point;

public class CopilTrees{
https://youtu.be/_0OAo8y7AtI
    public static class State{
        public int node, edge_index;
        public State(int node, int edge_index){
            this.node = node;
            this.edge_index = edge_index;
        }
    }
    public static class Pair {
        public int a, b;
        public Pair(int a, int b){ this.a = a; this.b = b;}
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] tree = new ArrayList[N + 1];
            for(int i = 1; i<=N; i++) tree[i] = new ArrayList<>();
            HashMap<Point, Integer> edge_index = new HashMap<>();
            for(int i = 0; i<N - 1; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edge_index.put(new Point(u, v), i);
                edge_index.put(new Point(v, u), i);
                tree[u].add(v);
                tree[v].add(u);
            }
            Queue<State> states = new LinkedList<>();
            states.add(new State(1, Integer.MIN_VALUE));
            //state (1, -inf) curr_index = -inf
            //

            boolean[] vis = new boolean[N - 1];
            int[] depth = new int[N - 1];
            while(states.size() != 0){
                State curr_state = states.poll();
                if(curr_state.edge_index != Integer.MIN_VALUE){
                    vis[curr_state.edge_index] = true;
                }
                for(int v: tree[curr_state.node]){
                    if(!vis[edge_index.get(new Point(curr_state.node, v))] && !vis[edge_index.get(new Point(v, curr_state.node))]) {
                        states.add(new State(v, edge_index.get(new Point(curr_state.node, v))));
                        if (curr_state.edge_index == Integer.MIN_VALUE) {
                            depth[edge_index.get(new Point(curr_state.node, v))] = 1;
                        }
                        else if (edge_index.get(new Point(curr_state.node, v)) < curr_state.edge_index) {
                            depth[edge_index.get(new Point(curr_state.node, v))] = depth[curr_state.edge_index] + 1;
                        }else if(curr_state.edge_index != Integer.MIN_VALUE){
                            depth[edge_index.get(new Point(curr_state.node, v))] = depth[curr_state.edge_index];
                        }
                    }
                }
            }
            int ans = Integer.MIN_VALUE;
            for(int i = 0; i<depth.length; i++){
                ans = Math.max(depth[i], ans);
            }
            if(ans == 0 || ans == Integer.MIN_VALUE) pw.println(1);
            else pw.println(ans);
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
