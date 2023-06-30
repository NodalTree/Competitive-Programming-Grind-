#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vi;

void solve(){
	int n; cin >> n;
	vi a; a.resize(n + 1);
	for(int i = 1; i<=n; i++) cin >> a[i];
	vi mn_suff; mn_suff.resize(n + 1);//min prefix sum for the ith suffix
	for(int i = 1; i<=n; i++){
		a[i] += a[i - 1];
	}
	mn_suff[n] = a[n];
	for(int i = n - 1; i>= 0; i--){
		mn_suff[i] = min(mn_suff[i + 1], a[i]);
	}
	ll max_delta = -1;
	ll ans = -1;
	for(int k = 0; k<=n; k++){
		//try each threshold, easy to see that itll be a prefix sum
		//we must increase the minmum prefix sum after k by the distance it is away from threhold
		//this will always then increase the last prefix sum the result by it
		ll delta = a[k] - mn_suff[k];
        if(delta > max_delta){
           ans = a[k];
		   max_delta = delta;
		}
	}
	cout << ans << endl;

}
int main() {
	int t; cin >> t; while(t--) solve();
}
