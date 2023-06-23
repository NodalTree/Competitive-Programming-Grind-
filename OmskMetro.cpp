// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

void solve(){
    int n; cin >> n;
	/*
	function -> continious for integers because we are going up by 1 and down by negative -1 -> this means that we can achieve
	any subsegment sum betwen min and max
	*/
	vi mn, mx, mn_suff, mx_suff;
	mn.resize(n + 2); mn_suff.resize(n + 2); mx_suff.resize(n + 2); mx.resize(n + 2);
	//define mn[i] as maximum subsegment sum from 1 to i and then define mn_suff[i] as maximum subsegment from i to 1
	//we also have to consider the empty subsegment at all times
	//base cases 
	mn[1] = 0; mn_suff[1] = 0;
	mx[1] = 1; mx_suff[1] = 1;
	int node = 2;
	while(n--){
		string s; int pa; int w;
		cin >> s >> pa >> w;
		if(s == "+"){
           //update the node we are adding 
		   mn_suff[node] = min(0, mn_suff[pa] + w);//consider empty suffix 
		   mn[node] = min(mn[pa], mn_suff[node]);//only a suffix from node could possibly beat mn[pa]
		   //apply same logic for mx
		   mx_suff[node] = max(0, mx_suff[pa]  + w);
		   mx[node] = max(mx[pa], mx_suff[node]);
		   node++;
		}else{
			int k; cin >> k;
			if(k >= mn[w] && k <= mx[w]) cout << "YES" << endl;
			else cout << "NO" << endl;
		}
	}
}

int main() {
   int t; cin >> t; while(t--) solve();
}
