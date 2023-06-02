import java.io.*;
import java.util.*;
//motivation: since we are picking paths, we will be forced to be pick the leaf nodes as operations because no other selection will include these leaf nodes, so we start our thinking over there
//when picking a path you should notice that increasing as little as you can applies less pressure to the next node
//you know the leaf nodes have to have some operation on them....
//it's always optimal to make the leaf nodes the maximal such value, bc then it gives more freedom to the nodes above to how much they can increase
//if we are at node u, we can dfs until we reach the leaf nodes and keep track of the sum of the leaf nodes
//if this sum is greater than the l value, then we obviously have enuff to increase it to that value, so we don't have to spend another operation 
//same logic applies bc now node v can be considered a leaf (considering visited vertices)..we increase it to min(r, sum)


public class FakePlasticTrees {
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while(T -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] tree = new ArrayList[N + 1];
			ans = 0;
			int[] l = new int[N + 1];
			int[] r = new int[N + 1];
			for(int i = 1; i<=N; i++) tree[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 2; i<=N; i++) {
				tree[Integer.parseInt(st.nextToken())].add(i);
			}
			for(int i = 1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				l[i] = Integer.parseInt(st.nextToken());
				r[i] = Integer.parseInt(st.nextToken());
			}
			dfs(1, tree, new boolean[N + 1], l , r);
			pw.println(ans);
		}
		pw.close();
	}
	public static long dfs(int u, ArrayList<Integer>[] tree, boolean[] vis, int[] l, int[] r) {
		//dfs will return the sum of all leaf nodes below  a node u
		long sum = 0;
		for(int v: tree[u]) {
			if(!vis[v]) {
				sum += dfs(v, tree, vis, l, r);
			}
		}
		if(tree[u].size() == 0) {
			//if this is a leaf node, then return obv set it to r
			ans++;//spend an operation
			return r[u];
		}
		if(l[u] <= sum) {
			//this means the sum is enough, so we set this to min r and sum
			return Math.min(r[u], sum);
		}
		ans++;//if the sum is not enough, then we will have to apply a new operation on this vertex, so insead of using the other operations just set it to its r value
		return r[u];
	}

}
