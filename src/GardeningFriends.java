import java.io.*;
import java.util.*;

public class GardeningFriends {
    //https://codeforces.com/problemset/problem/1822/F
    static ArrayList<Integer>[] tree;
    static boolean[] vis;
    static int numOfOps;
    static int height;
    static int diameter;
    static int end1;
    static int end2;
    static int[] heightEnd1, heightEnd2, distFromOne;
    static PrintWriter pw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            tree = new  ArrayList[N + 1];
            vis = new boolean[N + 1];
            numOfOps = 0;
            height = 0;
            diameter = 0;
            end1 = -1;
            end2 = -1;
            for(int i = 1; i<=N; i++){
                tree[i] = new ArrayList<>();
            }
            for(int i = 0; i<N - 1; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                tree[u].add(v);
                tree[v].add(u);
            }
            //the algorithm for finding the diameter implies that the height from each node is either ends of the diameter
            //this instantly kills the problem
            dfs(1, 0, true, false, false, false);//lets find the first end of the diameter
            Arrays.fill(vis, false);
            height = 0;//set height to 0 before u find first end
            dfs(end1, 0, false, false, false, false);//find the second end
            heightEnd1 = new int[N + 1];
            heightEnd2 = new int[N + 1];
            Arrays.fill(vis, false);
            dfs(end1, 0, false, true, false, false);//find first  heights
            Arrays.fill(vis, false);
            dfs(end2,0, false, false, true, false);//find second heights
            distFromOne = new int[N + 1];
            Arrays.fill(vis, false);
            dfs(1, 0, false, false, false, true);//find distance from 1
            long ans = 0;
            for(int v = 1; v<=N; v++){
                long profit = (long) Math.max(heightEnd1[v], heightEnd2[v]) * K - (long) distFromOne[v] * C;
                ans = Math.max(profit, ans);
            }
            //pw.println(end1 + " " + end2);
            //pw.println(Arrays.toString(distFromOne));
            //pw.println(Arrays.toString(heightEnd1));
            //pw.println(Arrays.toString(heightEnd2));
            pw.println(ans);
        }
        pw.close();
    }
    public static void dfs(int u, int depth, boolean findEnd1, boolean findEnd1Heights, boolean findEnd2Heights, boolean fromOne){
        if(u == -1) return;
        vis[u]  = true;
        if(findEnd1Heights){
            heightEnd1[u] = depth;
        }else if(findEnd2Heights){
            heightEnd2[u] = depth;
        }else if(fromOne){
            distFromOne[u] = depth;
        }
        else if(height < depth){
                if(findEnd1) end1 = u;
                else end2 = u;
                height = depth;
            }
         for(int v: tree[u]){
            if(!vis[v]){
                dfs(v, depth + 1, findEnd1, findEnd1Heights, findEnd2Heights, fromOne);
            }
        }
    }
}
