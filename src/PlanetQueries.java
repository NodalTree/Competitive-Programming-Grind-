import java.io.*;
import java.util.*;
import java.awt.Point;

public class PlanetQueries {
    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        pw = new PrintWriter(System.out);
        int N = fs.nextInt();
        int Q = fs.nextInt();
        ArrayList<Node>[] uni_graph = new ArrayList[N + 1];
        ArrayList<Node>[] bi_graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            uni_graph[i] = new ArrayList<>();
            bi_graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            int tele = fs.nextInt();
            uni_graph[i].add(new Node(tele, -1, -1));//currently we don't know the depth, we don't now index only update if it's a cycleNode
            bi_graph[i].add(new Node(tele, -1, -1));
            bi_graph[tele].add(new Node(i, -1, -1));
        }
        HashMap<Integer, ArrayList<Integer>> queries = new HashMap<>();
        HashMap<Point, Integer> ans = new HashMap<>();
        Point[] save_queries = new Point[Q];
        for (int i = 0; i < Q; i++) {
            int t = fs.nextInt();
            int k = fs.nextInt();
            if (queries.get(t) == null) {
                queries.put(t, new ArrayList<>());
            }
            queries.get(t).add(k);

            save_queries[i] = new Point(t, k);
        }
        //dfs the bi graph to get compeonents
        boolean[] vis = new boolean[N + 1];
        for (int v = 1; v <= N; v++) {
            ArrayList<Node> nodes = new ArrayList<>();
            if (!vis[v]) {
                //dfs the outgoing edge doesn't matter
                dfsGlobal(bi_graph[v].get(0), nodes, vis, bi_graph);
                new Component(nodes, N + 1, uni_graph, bi_graph, queries, ans);//updates answer
            }
        }
        for (Point query : save_queries) pw.println(ans.get(query));
        pw.close();
    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

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

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    public static void dfsGlobal(Node node, ArrayList<Node> nodes, boolean[] vis, ArrayList<Node>[] graph) {
        vis[node.val] = true;
        nodes.add(node);
        //pw.println(node.val);
        for (Node v : graph[node.val]) {
            if (!vis[v.val]) {
                dfsGlobal(v, nodes, vis, graph);
            }
        }

    }

    static class Component {
        public ArrayList<Node> nodes;
        public ArrayList<Node> cycleNodes;
        public int graphSize;
        public boolean[] vis;
        public ArrayList<Node>[] uni_graph, bi_graph;
        public boolean[] isCycleNode;
        HashMap<Integer, ArrayList<Integer>> queries;
        HashMap<Point, Integer> ans;//tells me the answer to that query
        HashMap<Integer, Integer> indexToNode;

        public Component(ArrayList<Node> nodes, int graphSize, ArrayList<Node>[] uni_graph, ArrayList<Node>[] bi_graph, HashMap<Integer, ArrayList<Integer>> queries, HashMap<Point, Integer> ans) {
            this.nodes = nodes;
            this.graphSize = graphSize;
            vis = new boolean[graphSize];
            this.uni_graph = uni_graph;
            this.bi_graph = bi_graph;
            cycleNodes = new ArrayList<>();
            this.queries = queries;
            indexToNode = new HashMap<>();
            this.ans = ans;
            isCycleNode = new boolean[graphSize];
            /*for(Node n: nodes) pw.print(n.val + " ");
            pw.println();*/
            // pw.println("This is the size of nodes "+ nodes.size()); pw.println("After print this "+ nodes.get(0).val);
            //pw.println("This a cycle node " + dfs(nodes.get(0)).val);
            getCycleNodes(dfs(nodes.get(0)), true, 0);
            /*for(Node n: cycleNodes) pw.print(n.val + " ");
            pw.println();*/
            //now lets answer all the queries for the nodes
            for (int i = 0; i < cycleNodes.size(); i++) {
                dfsTree(cycleNodes.get(i), true, new ArrayList<>(), 0, cycleNodes.get(i));
            }
        }

        public Node dfs(Node node) {
            vis[node.val] = true;
            //pw.println("bad");
            Node cycleNode = new Node(-1, -1, -1);
            for (Node v : uni_graph[node.val]) {
                if (vis[v.val]) {
                    //pw.println("sucess found "+ v.val);
                    return v;//this has to be a cycle node
                } else {
                    cycleNode = dfs(v);
                }
            }
            return cycleNode;
        }

        public void getCycleNodes(Node cycleNode, boolean start, int index) {
            if (start) Arrays.fill(vis, false);
            isCycleNode[cycleNode.val] = true;
            cycleNodes.add(cycleNode);
            cycleNode.index = index;
            indexToNode.put(index, cycleNode.val);
            vis[cycleNode.val] = true;
            for (Node v : uni_graph[cycleNode.val]) {
                if (!vis[v.val])
                    getCycleNodes(v, false, index + 1);
            }
        }

        public void dfsTree(Node node, boolean start, ArrayList<Node> currentPath, int depth, Node cycleNode) {
            //we will dfs tree at the start from cycle node, now we will use the bi graph
            if (start) Arrays.fill(vis, false);
            node.depthToCycle = depth;//update the distance or sort of depth to the cycle
            vis[node.val] = true;
            currentPath.add(node);
            //lets now lets deal with queries that are less than the node's <= node.depth
            if (queries.get(node.val) != null) {

                    //pw.println("skkdkwcw "+ queries.get(node.val).size());
                    for (int k : queries.get(node.val)) {
                        if (k <= node.depthToCycle) {
                            ans.put(new Point(node.val, k), currentPath.get(depth - k).val);
                        } else {
                            //pw.println("are we really dancing bru");
                            int cycle_len = cycleNodes.size();
                            int val = k - node.depthToCycle;
                            ans.put(new Point(node.val, k), indexToNode.get((cycleNode.index + val) % cycle_len));
                        }
                    }
            }
            for (Node v : bi_graph[node.val]) {
                if (!vis[v.val] && !isCycleNode[v.val]) {
                    dfsTree(v, false, currentPath, depth + 1, cycleNode);
                }
            }
            currentPath.remove(node);
        }
    }

    static class Node {
        public int val, depthToCycle;
        public int index;
        HashMap<Integer, Integer> precomputedQueries;

        public Node(int val, int depthToCycle, int index) {
            this.val = val;
            this.depthToCycle = depthToCycle;
            precomputedQueries = new HashMap<>();
            this.index = index;
        }
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
