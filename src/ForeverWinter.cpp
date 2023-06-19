// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

void solve(){
	int n; int m; cin >> n >> m; vector<vi> graph; graph.resize(n + 1);
	for(int i = 0; i<m; i++){
		int u; int v; cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	int deg1 = -1;
	int deg2 = -1;
	int not_unique = -1;//deg1 will be 4
	for(int i = 1; i<=n; i++){
	    if(graph[i].size() == 1) continue; //dont caree about leafs
		if(deg1 == -1) deg1 = graph[i].size();//first type of degree 
		else if(graph[i].size() != deg1 && deg2 == -1){
			deg2 = graph[i].size();//if the degree is not the same we have second type of degree 
		}else{
			if(deg1 == graph[i].size()) not_unique = 1;
			else not_unique = 2;
		}
	}
	if(deg2 == -1){
		//all nodes had the same degree 
		cout << deg1 << " " << (deg1 - 1);
	}else{
		if(not_unique == 1){
			cout << deg2 << " " << (deg1 - 1);
		}else{
			cout << deg1 << " " << (deg2 - 1);
		}
	}
	cout << endl;
}

int main() {
	int t; cin >> t; while(t--) solve();
}
/*

just look at the graph and notice there are x nodes with degree y + 1, and the answer is just there
Just ignore all nodes with degree 1 or a degree > 1, then we can do basic case work
one node will have degree x the others will have degree y + 1
what happens if (x == y + 1)...this means ignoring all nodes with deg 1 we will cout << deg <<  (deg - 1)
otherwise there are multiple nodes with deg y + 1 and only one with deg x, so we just cout << one with deg x << multiple with deg smth and then - 1
seems like we can put the degrees in a set and simply just check the frequencies if we need to 




*/
