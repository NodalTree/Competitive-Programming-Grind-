// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vi;

void solve(){
    int n; ll a; ll b; cin >> n >> a >> b;
	vi v; v.resize(n + 1);
	for(int i = 1; i<=n; i++) cin >> v[i];
    //there's a linear amount of options for fixing the captial lets fix the captial at position i
	//fix capital at position 0
	ll ans = 0;
	ll sum_dist0 = 0;
	for(int i = 1; i<=n; i++) sum_dist0 += v[i];
	vi sum_dist; sum_dist.resize(n + 1);
	sum_dist[0] = sum_dist0;
	for(int i = 1; i<=n; i++){
		sum_dist[i] = sum_dist[i - 1] - (n - i  + 1) * (v[i] - v[i - 1]);
	}
	ans = sum_dist[0] * b;//we never move the captial 
	for(int i = 1; i<=n; i++){
        //fix captial at position i, we need sum of the distances up to i, clearly most optimal 
		//after we need sum of the distances from capital to the other captials
		//captial cost is a * distance 
		ll captial_cost = a * (v[i]);
		ll total_left = v[i] * b;//total left is the distance in this capital times b (clearly this most optimal for fixed capital);
		ll total_right = sum_dist[i] * b;
		ll total_cost = captial_cost + total_left + total_right;
		ans = min(total_cost, ans);
	}
	cout << ans << endl;
}

int main() {
     int t; cin >> t; while(t--) solve();
}
