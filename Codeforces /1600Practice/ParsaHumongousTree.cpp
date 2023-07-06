//test
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
/*
Intuitively, by just playing around with test cases, you can easily notice that you should only assign numbers
endpoints and not any in betweeen answer cuz u can always improve the answer if not. Lets prove this rigoursly,
imagine we choose some a_v such that l_v < a_v < r_v. Then, let there be x nodes adj to v such that are higher than the 
value a_v, and let there be y nodes adj to v such that they are lower than the value of a_v (wlog x > y). Then if we change a_v to l_c, the n does that are higher
than a_v obviously contribute a x * (a_v - l_v) gain, but the nodes that are lower (can MAXMICALLY, in worst case) contribute  y * (a_v - l_v) negation.
Overall, we it's clear that since x > y, then gain easily beats the negation. We can use symmtery and say if p < q, then we can change a_v to r_v to imrpove
answer. 

So, now how do we proceed with answer.  Well now it should be strikingly obvious with dp. If each node has either two options l_v, r_v we can compress this
into a state. Furthermore, we can let dp[v][state] represent the answer for the subtree rooted at v where state = 0 means v = l_v and state = 1 means r_v = 1.
Now, how do we transition, well dp[v][0] += max(dp[u][0] + abs(l_v - l_u), dp[u][1] + abs(r_v - r_u )). Clearly, we can only come off two possible options....
we do something similar for dp[v][1]...should be clear

*/
const int max_n = 1e5 + 5;
ll dp[2][max_n];
void dfs(int node, vector<bool>& vis, vector<vi>& tree, vi& l, vi& r, int pa){
	dp[0][node] = 0; dp[1][node] = 0;
	vis[node] = true;
	for(int ch: tree[node]){
		if(!vis[ch]){
			dfs(ch, vis, tree, l, r, node);
		}
		if(ch == pa) continue;//don't wan to count this in outside subtree 
		dp[0][node] += max(dp[0][ch] + (ll)abs(l[node] - l[ch]), dp[1][ch] + (ll)abs(l[node] - r[ch]));
		dp[1][node] += max(dp[0][ch] + (ll)abs(r[node] - l[ch]), dp[1][ch] + (ll)abs(r[node] - r[ch]));
	}
}
void solve(){
	int n; cin >> n; vi l(n + 1); vi r(n + 1);
	for(int i = 1; i<=n; i++){
		int s, e; cin >> s >> e;
		l[i] = s;
		r[i] = e;
	}
	vector<vi> tree(n + 1);
	for(int i = 1; i<n; i++){
		int u; int v;
		cin >> u >> v;
        tree[u].push_back(v);
		tree[v].push_back(u);
	}
	vector<bool> vis(n + 1);
	dfs(1, vis, tree, l, r, -1);
	cout << max(dp[0][1], dp[1][1]) << '\n';
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
	int t; cin >> t; while(t--) solve();
}
