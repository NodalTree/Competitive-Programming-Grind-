import java.io.*;
import java.util.*;
//ill come back and see what i did wrong here
/*
Diameter: 5

G_5 = 13 - 8 + 1 = 6
G_4 = 6 - 3  = 3
G_3  = 1
G_2 = 1
G_1 = 1

We already know an algorithm to find G_(diameter)
What if we want to find G_(diameter - K), for K > 0 && k < diameter
All nodes k distance from the endpoints of the diameter (do it from both) can join the component
Let dist[x]  represent the number of nodes that are x edges away from a diameter endpoint

Very easy to find dist[x] by doing two dfses from the endpoints of the diameter

Just to not overcount...(paranoid) also it's easy to overcount  when there is half way between both endpoints
So, i'll let dist[x] represent the treeset of nodes that are x edges from a diameter endpoint, simply just find this
through two dfses.
then G_(diameter - K) = G_diameter - dist[x].size();
         everything afect diameter is simply 1's...this should work problem is simiar to gardening friends
         https://codeforces.com/problemset/problem/1805/D
 */
public class WideGraph {
    static ArrayList<Integer>[] tree;
    static TreeSet<Integer>[] dist;
    static int diameter_endpoints;
    static boolean[] isDimEnd;

    static boolean[] vis;
    static int side1_endpoint, side2_endpoint, max_depth;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        dist = new TreeSet[N + 1];
        isDimEnd = new boolean[N + 1];
        vis = new boolean[N + 1];
        for (int i = 0; i <= N; i++) dist[i] = new TreeSet<>();
        diameter_endpoints = 0;
        side1_endpoint = -1;
        side2_endpoint = -1;
        max_depth = Integer.MIN_VALUE;
        dfs(1, 0, true, true, false);//to find side1
        Arrays.fill(vis, false);
        max_depth = Integer.MIN_VALUE;
        dfs(side1_endpoint, 0, false, true, false);//find side 2 and diameter (which aka max_depth)
        ans = new int[N + 1];
        Arrays.fill(ans, N);
        Arrays.fill(vis, false);
        dfs(side1_endpoint, 0, false, false, true);//to find amount of dim ends
        Arrays.fill(vis, false);
        dfs(side2_endpoint, 0, false, false, true);//to find amt of dim ends
        Arrays.fill(vis, false);//above two dfses also mark dim ends
        dfs(side1_endpoint, 0, false, false, false);//dfs from one side
        Arrays.fill(vis, false);
        dfs(side2_endpoint, 0, false, false, false);//dfs from another side
        ans[max_depth] = N - diameter_endpoints + 1;
        for (int k = 1; k < max_depth; k++) {
            ans[max_depth - k] = ans[max_depth -  k - 1] - dist[k].size();
        }
        for (int i = 1; i <= N; i++) pw.print(ans[i] + " ");
        for(int i = 0; i<=N; i++) pw.println("("+ i + " , " + dist[i].size() + ")");
        pw.println(side1_endpoint + " "+ side2_endpoint);
        pw.println(diameter_endpoints);
        pw.println();
        pw.close();
    }

    public static void dfs(int u, int depth, boolean find_side1, boolean findMax, boolean findAmt) {
        vis[u] = true;
        if(findAmt && depth == max_depth){
            diameter_endpoints++;
            isDimEnd[u]  = true;
        }
        if(!findAmt && !findMax && !isDimEnd[u]) dist[depth].add(u);//we only want to do this for the last two dfses
        if (depth > max_depth && findMax) {
            if (find_side1) {
                side1_endpoint = u;
            } else {
                side2_endpoint = u;
            }
            max_depth = depth;
        }
        for (int v : tree[u]) {
            if (!vis[v]) {
                dfs(v, depth + 1, find_side1, findMax, findAmt);
            }
        }
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
