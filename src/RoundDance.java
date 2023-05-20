import java.io.*;
import java.util.*;


public class RoundDance {
    //https://codeforces.com/contest/1833/problem/E
    static TreeSet<Integer>[] graph;
    static boolean[] vis;
    static boolean oneAcyclic;
    static PrintWriter pw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            graph = new TreeSet[N + 1];
            vis = new boolean[N + 1];
            for(int i = 1; i<=N; i++) graph[i] = new TreeSet<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<=N; i++){
                int a = Integer.parseInt(st.nextToken());
                graph[i].add(a);
                graph[a].add(i);
            }
            int cycleComp = 0;
            int min = 0;
            int max = 0;
            oneAcyclic = false;
            for(int v = 1; v<=N; v++){
                if(!vis[v]){
                    max++;//max is simply the number of connected components, make the acyclic comps a cycle by filling in the remaining edge
                    cycleComp = dfs(v,-1) ? cycleComp + 1: cycleComp;
                }
            }
            //all the acyclic components  can be merged into one cycle, thus our min is CycleComp + (1 or 0) 1 if there is at least on acylic componet 0 if there's not
            min =  oneAcyclic ? (cycleComp + 1): cycleComp;
            pw.println(min + " " + max);
        }
        pw.close();
    }

    public static void ruffleSort(int[] a){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<a.length; i++){
            list.get(a[i]);
        }
        Collections.shuffle(list);
        Collections.sort(list);
        for(int i = 0; i<a.length; i++){
            a[i] = list.get(i);
        }
    }
    public static boolean dfs(int u, int father){
        //pw.print(u + " ");
        vis[u] = true;
        for(int v: graph[u]){
            if(father == v) continue;
            if(vis[v]){
                //cycle found
                return true;
            }
            if(dfs(v, u)){
                return true;
            }
        }
        oneAcyclic = true;
        return false;
    }
}
