import java.io.*;
import java.util.*;

public class FishGraph{
    //1900 but challenging and took me a while to think about and then code too
    //https://codeforces.com/contest/1817/problem/B
    static ArrayList<Integer>[] graph;
    static boolean[] vis, inCycle;
    static PrintWriter pw;
    static int[] pa;
    static int[] depth;
    static int K;

    static Queue<Integer> visiting = new LinkedList<>();
    static TreeSet<Integer> set = new TreeSet<>();
    //took way too long on this problem


    //i guess start off in this problem by saying well obviously the subgraph has to be a cycle itself
    //then one of these nodes obv must have a deg>= 4...
    //now you question urself...is this enuff...yes it is
    //proof for a fix node u  of deg >= 4: 2 of its children are in the cycle...at case 1: least 2 of its edges is outside cycle (fish graph)
    //case 2: exactly 1 of its edges is outside the cycle (fish graph)
    //case 3: exactly 0 of its edges is outside the cycle (fis graph)
    //case 1 is trivial because the special node is u int hat case
    //case 2: we know at least 3 edges are forced to be in the cycle because only 1 is outside and it has deg >= 4
            /*this means that there is at least one diagonal edge that exists, thus forming a smaller cycle within the orginal cycle
            so now make the special node u, and then use that 1 edge not a part of it...note that two edges of u that are in the cyle
            are not  diagonal (call them non dig), then note the diagonal splits the non dig clearly makes two cycles in this
             cycle...wlog take the cycle on the right, then can use the left non dig edge as ur 2nd edge done*/
    //case 3: this means we have at least two diagonals ..same idea as case 2, just take a non dig and a dig that ur
    //ur not using
    //how to do this now? fix the node u, then try to go through all cycles ...fix the first edge (v) to do this in the cycle
    //after u do that delete the edge and see if u can still get to v from u...if u can then ur good
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            K = 0;
            graph = new ArrayList[N + 1];
            for(int i = 1; i<=N; i++) graph[i] = new ArrayList<>();
            for(int i = 0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
            boolean good = false;
            visiting = new LinkedList<>();
            pa = new int[N + 1];
            vis = new boolean[N + 1];
            inCycle = new boolean[N + 1];
            depth = new int[N + 1];
            //pw.println("im tired nah u rhoght");
            for(int i = 1; i<=N; i++){
                if(graph[i].size() >= 4){
                    for(int child: graph[i]){
                       // pw.println(child);
                        boolean out = false;
                        Arrays.fill(vis, false);
                        Arrays.fill(pa, -1);
                        Arrays.fill(inCycle, false);
                        Arrays.fill(depth, 0);
                        if(bfs(i, child)){
                            //bfs gets rid of diagonals which are annoying
                            out = true;
                            good = true;
                            pw.println("YES");
                            //ArrayList<Integer> ans = new ArrayList<>();
                            pw.println(depth[child] + 3);
                            pw.println(i + " "+ child);
                            for(int v = child; pa[v] != -1; v = pa[v]){
                                inCycle[v] = true;
                                pw.println(v + " " + pa[v]);
                            }
                            //pw.println("bru bruh bruh bruh bru " + i + " "+ inCycle[5]);

                            int count = 0;
                            for(int kid: graph[i]){
                                if(count == 2) break;
                                if(!inCycle[kid]){
                                    pw.println(i + " " + kid);
                                    count++;
                                }
                            }
                           break;
                        }
                        visiting.clear();
                    }
                    if(good) break;
                }
            }
            if(!good) pw.println("NO");
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
    public static boolean bfs(int u, int fixed){
        //fixed represents the node we are fixing to be the first edge of the cycle
        visiting.add(u);
        boolean[] in = new boolean[vis.length];
        while(visiting.size() != 0){
            int node = visiting.poll();
            //if(node == 2) pw.println("I came from  " + pa[2] + " "+ in[3] + " "+ vis[3]);
            //pw.println("visiting tis "+ node);
            if(node == fixed) { K += 2;return true;}
            vis[node] = true;
            for(int v: graph[node]){
                if(node == u && v == fixed) continue;//remove this edge
                if(!vis[v] && !in[v]){
                    pa[v] = node;
                    depth[v] = depth[pa[v]] + 1;
                    in[v] = true;
                    visiting.add(v);
                }
            }
        }
        return false;
    }

}
