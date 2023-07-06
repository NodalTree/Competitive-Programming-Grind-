#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
/*
have seen this setup several times, won't even explain what im doing
too lazy, but def know how to do this.did some dp for suffix case tho W.
*/

void solve(){
	int n, m; cin >> n >> m; vi a(n + 1);
	for(int i = 1; i<=n; i++){
		char s; cin >> s;
		if(s == '+') a[i] = 1;
		else a[i] = -1;
	}
	vi min_pref(n + 2); vi max_pref(n + 2); vi min_suff(n + 2); vi max_suff(n + 2);
	int sum = 0;
	vi ps(n + 2);
	for(int i = 1; i<=n; i++){
	   sum += a[i];
	   ps[i] = sum;
	   min_pref[i] = min(sum, min_pref[i - 1]);
	   max_pref[i] = max(sum, max_pref[i - 1]);
	}
	max_suff[n] = a[n];
	min_suff[n] = a[n];
	for(int i = n - 1; i>=1; i--){
		max_suff[i] = max(a[i], max_suff[i + 1] + a[i]);
		min_suff[i] = min(a[i], min_suff[i + 1] + a[i]);
	}
	int mx = 0; int mn = 0;
	for(int i = 0; i<m; i++){
		int l; int r; cin >> l >> r;
		mx = max(max_pref[l - 1], ps[l - 1] + max_suff[r + 1]);
		mn = min(min_pref[l - 1], min_suff[r + 1] + ps[l - 1]);
		mx = max(0, mx);
		mn = min(0, mn);
		int ans = mx - mn + 1;
		cout << ans << endl;
	}
}

int main() {
	int t; cin >> t; while(t--) solve();
}
