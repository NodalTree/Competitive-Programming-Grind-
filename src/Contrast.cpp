// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<int, int> pi;

int n; vi a;

void solve(){
	cin >> n; a.resize(n);
	for(int i = 0; i<n; i++) cin >> a[i];
	//idea: minimum size is equivalent to deleting as many elements as possible
	//how do we know if we can delete a_i, well if delete a_i, then we new value we have is abs(a_(i + 1) - a_i)
	//and old value we had was abs(a_i - a_(i - 1)) + abs(a_(i + 1) - a_i)
	//if old and new are the same we can delete them 
	n = unique(a.begin(), a.end()) - a.begin();
	int max_deletions = 0;
	for(int i = 1; i<n - 1; i++){
	   if(abs(a[i + 1] - a[i - 1]) == abs(a[i] - a[i - 1]) + abs(a[i + 1] - a[i])) {max_deletions++; a[i] = a[i  -1];}
	}
	int ans = n - max_deletions;
	cout << ans << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}
