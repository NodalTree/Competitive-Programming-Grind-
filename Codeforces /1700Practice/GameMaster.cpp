
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

struct node{
	int a, b, id;
};
bool comp_a(const node& a, const node& b){
	return a.a > b.a;
}
bool comp_b(const node& a, const node& b){
	return a.b > b.b;
}
void dfs(int node, vector<bool>& vis, vector<vi>& graph){
	vis[node] = true;
	for(int ch: graph[node]){
		if(!vis[ch]){
			dfs(ch, vis, graph);
		}
	}
}

void solve(){
	int n; cin >> n; vi a(n); vi b(n);
	for(int& i : a) cin >> i; for(int& i: b) cin >> i;
	vector<vi> graph(n); vector<node> nodes;
	int special_node = -1;//node with highest a or b, doesn't matter
	for(int i = 0; i<n; i++){
       nodes.push_back({a[i], b[i], i});
	}
	sort(nodes.begin(), nodes.end(), comp_a);//sort by one v alue
	special_node = nodes[0].id;
	for(int i = 0; i + 1 < n; i++){
		graph[nodes[i + 1].id].push_back(nodes[i].id);
	}
	sort(nodes.begin(), nodes.end(), comp_b);//sort nodes by other value
	for(int i = 0; i + 1 < n; i++){
		graph[nodes[i + 1].id].push_back(nodes[i].id);
	}
	vector<bool> vis(n);
	dfs(special_node, vis, graph);
	for(int i = 0; i<n; i++){
        if(vis[i]) cout << 1;
		else cout << 0;
	}
	cout << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}
