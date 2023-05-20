import java.io.*;
import java.util.*;

public class KsyushaAndChinchilla {
    static TreeSet<Integer>[] tree;
    static int[] pa;
    static boolean[] vis;
    static int[] orig;
    static HashMap<ArrayList<Integer> , Integer> edgeIndex;
    static int root;
    static ArrayList<Integer> ans;
    static PrintWriter pw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            tree = new TreeSet[N + 1];
            orig = new int[N + 1];
            pa = new int[N + 1];
            vis = new boolean[N + 1];
            edgeIndex = new HashMap<>();
            root = -1;
            ans = new ArrayList<>();
            for(int i = 1; i<=N; i++){
                tree[i] = new TreeSet<>();
            }
            /* root the tree arbitrarily first,  intuitively i thought of starting from the bottom of the tree and then by insight realized it's just a post - order
              transversal of the tree...also obv if N is not 0 mod 3, then print -1...otherwise post order transversal
              and when you visit a root cut the edge between the root and the parent's root...so first we need to
              construct a parent array...when you are at a root in the transversal the parent must only have 2 children
              (ofc we are cytting throughout the process)...if it doesn't then it's impossible to cut the tree into
              branches...cutting will be logarithmic time bc im using  a treese...from this u realize only a binary
              tree can satisfiy this "branch" property...it has to be a full and complete binary tre
              ...wait a second with a litle bit more thinking...so we know the tree must be a full
              tree in order for this to work, very easy to see why if u think abt it visually, i somehow came up with
              this by thinking of cuts from the bottom
             */
            for(int i = 0; i<N - 1; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                tree[u].add(v);
                tree[v].add(u);
                ArrayList<Integer> edge1 = new ArrayList<>();
                ArrayList<Integer> edge2 = new ArrayList<>();
                edge1.add(u); edge1.add(v);
                edge2.add(v); edge2.add(u);
                edgeIndex.put(edge1, i);
                edgeIndex.put(edge2, i);
            }
            int root = -1;
            boolean earlyCheck = false;
            for(int i = 1; i<=N; i++){
                orig[i] = tree[i].size();
                if(orig[i] == 2 && root == -1){
                    root = i;
                    earlyCheck = true;
                }
            }
            pw.println("This is the root "+ root);
            if(!earlyCheck){
                pw.println(-1);
                continue;
            }
            //if the tree is not binary then it's also impossible
            boolean isBinary = true;
            for(int i = 1; i<=N; i++){
                if(i == root){
                    if(orig[root] != 2) isBinary  = false;
                }else if(orig[i] != 1 || orig[i] != 3){
                    isBinary = false;
                }
            }
            if(N % 3 != 0 || !isBinary){
                pw.println(-1);
                continue;
            }
            //now we know we are dealing with a valid tree: a completely full binaryt tree
            dfs(root, -1);//lets get the parents
            Arrays.fill(vis, false);
            dfsCutting(root,false);
            pw.println(ans.size());
            for(int i: ans) pw.print(i + " ");
            pw.println("This is the root "+ root);
            pw.println();
        }
        pw.close();
    }
    public static void  dfs(int u,  int parent){
        vis[u] = true;
        pa[u] = parent;
        for(int v: tree[u]){
            if(!vis[v]){
                dfs(v, u);
            }
        }
    }
    public static void dfsCutting(int u, boolean shouldCut){
      vis[u] = true;
      if(shouldCut){
          ArrayList<Integer> edge = new ArrayList<>();
          edge.add(u); edge.add(pa[u]);
          ans.add(edgeIndex.get(edge));
      }
      for(int v: tree[u]){
          if(!vis[v]){
              boolean nextCut = false;
              if(pa[v] != root && orig[v] != 1){
                  nextCut = false;
              }
              dfsCutting(v, nextCut);
          }
      }

    }
}
