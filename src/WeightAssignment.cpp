// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;


void solve(){
	int n; cin >> n; vi pa; pa.resize(n + 1); vi p; p.resize(n + 1); vi d(n + 1, - 1);//define d as distance 
	for(int i = 1; i<=n; i++) cin >> pa[i];
    for(int i = 1; i<=n; i++) cin >>p[i];
	if(pa[p[1]] != p[1]){
		//first node obviously has to be the root that will always have weight of 0
		//this is how they define the root, parent of first one is itsewlf
		cout << -1 << endl;
	}else{
		//lets start assign the distance values for each node 
		d[p[1]] = 0;//distance value is clearly 0 
		for(int i = 2; i<=n; i++){
			if(d[pa[p[i]]] == -1){
	           //in order for this to be possible we always have to assign the distance value to the parent before this node 
              //if this is true, we know that we the parent has greater index, which is contradiction
			  cout << -1 << endl;
			  return;
			}
			//then we know that d[p[i]] > d[p[i - 1]], so we assign it + 1
			d[p[i]] = d[p[i - 1]] + 1;
		}
		//now we've assigned each node it's distance value from the root, to get the weights we can simply subtract the sum of weights
		//from the parent and node
		for(int node = 1; node<=n; node++){
			cout << d[node] - d[pa[node]] << " ";
		}
		cout << endl;
	}
}

int main() {
	int t; cin >> t; while(t--) solve();
}
