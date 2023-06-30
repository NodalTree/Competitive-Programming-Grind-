#include <bits/stdc++.h>
using namespace std;
typedef vector<int> vi;
/*
we know that this will be a functional graph one cycle and trees hanging from it, thus we ust binary jumping 
to find our answer. Let pa[i][d] represent the planet we end up on after 2^d teleporters. Then, we have
pa[i][d] = pa[pa[i][d - 1]][d - 1]. Basically, we can jump 2^(d - 1) nodes from node i, then jump again
2^(d - 1) nodes. Since i is only N and d is at most 30. This suggets that we can find pa in O(N * log N) time. The reason we can
do binary jumping is because the graph is functional, basically each node has only one outgoing edge, so only one
place to teleport  
 
*/
int MAX_D = 30;
vector<vi> pa;
int query(int node, int k){
	for(int i = 0; i<MAX_D; i++){
		if(k & (1 << i)){
			//of that bit is present, then jump that much 
			node = pa[node][i];
		}
	}
	return node;
}
void solve(){
	int n; int q; cin >> n >> q;
	pa.resize(n + 1); for(int i = 1; i<= n; i++) pa[i].resize(MAX_D + 1);
	for(int i = 1; i<=n; i++){
       cin >> pa[i][0];
	}
	
	for(int jump = 1; jump <= MAX_D; jump++){
		for(int node = 1; node<=n;node++){
			pa[node][jump] = pa[pa[node][jump - 1]][jump - 1];
		}
	}
	for(int i = 0; i<q; i++){
		int x; int k; cin >> x >> k;
		cout << query(x, k) << endl;
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	solve();
}
