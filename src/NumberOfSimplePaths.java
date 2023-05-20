import java.io.*;
import java.util.*;

public class NumberOfSimplePaths {
    //https://codeforces.com/problemset/problem/1454/E
    static ArrayList<Integer>[] graph;
    static boolean[] vis;
    static int[] parent;
    static ArrayList<Integer> cycleNodes;
    static int cycleStart;
    static int cycleEnd;
    static boolean[] isCycleNode;
    static int size;
    static int[] sizes;


    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            cycleNodes = new ArrayList<>();
            vis = new boolean[N + 1];
            parent = new int[N + 1];
            isCycleNode = new boolean[N + 1];
            cycleStart = -1;
            cycleEnd = -1;
            size = 0;
            sizes = new int[N + 1];
            for(int i = 1; i<=N; i++) graph[i] = new ArrayList<>();
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
            dfs1(1, -1);
            for(int v = cycleEnd; v != cycleStart; v = parent[v]){
                cycleNodes.add(v);
            }
            cycleNodes.add(cycleStart);
            int lengthOfCycle = cycleNodes.size();
            for(int i: cycleNodes) isCycleNode[i] = true;
            Arrays.fill(vis, false);
            for(int i = 0; i<cycleNodes.size(); i++){
                size = 0;
                dfs2(cycleNodes.get(i));
                sizes[cycleNodes.get(i)] = size;
            }
            long S = 0;//let this be sum of the sizes
            long S_P = 0;//sum of perfect squares of sizes
            long tree_sum = 0;//sum of the trees hanging from the cycle
            for (int j : sizes) {
                S += j;
                S_P += Math.max((long) j * j, 0);
                tree_sum += (long) (j - 1) * j / 2;
            }
            long ans = S * S + tree_sum - S_P;//sum of the sizes squared + the trees - perfect squares
            //for(int i: cycleNodes) pw.print(i + " ");
            //pw.println();
            //pw.println(Arrays.toString(sizes));
            pw.println(ans);
            //pw.println(cycleStart + " "+ cycleEnd);
        }
        pw.close();
    }
    public static boolean dfs1(int v, int par){
        vis[v] = true;
        for(int u: graph[v]){
            if(u == par) continue;
            if(vis[u]){
                cycleStart = u;
                cycleEnd = v;
                return true;
            }
            parent[u] = v;
            if(dfs1(u, parent[u])){
                return true;
            }
        }
        return false;
    }
    public static void dfs2(int v){
        vis[v] = true;
        size++;
        for(int u: graph[v]){
            if(!vis[u] && !isCycleNode[u]){
                dfs2(u);
            }
        }
    }
}
