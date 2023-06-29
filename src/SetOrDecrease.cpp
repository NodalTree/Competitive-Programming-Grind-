// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vi;

void solve(){
	int n; ll k; cin >> n >> k; ll sum = 0;
	vi a; a.resize(n);
	for(int i = 0;i<n; i++) {cin >> a[i]; sum += a[i];}
	sort(a.begin(), a.end());
	//in general (for a spread out array), replacing decreases sum faster, thus we fix the amount of replacements, and then solution is not bad
	ll mn = a[0];//min value is a[0]
	ll total_decrease = sum - k;//how much we have to decrease it by  
	//now lets reverse the vector
	reverse(a.begin(), a.end());
	//decrease every value by min now
	for(int i = 0; i<n; i++) a[i] -= mn;
	//take the prefix sum now
	for(int i = 1; i<n; i++){
		a[i] += a[i - 1];
	}
	ll ans = max((ll)0, total_decrease);//if we consider an empty prefix
	for(int i = 0; i<n; i++){
		if(ans == 0 || n == 1) break;//get out of here if this is true , edge cases 
		//now lets consider using ith prefix
		if(i != n - 1)
		ans = min(ans, i + 1 +  max((ll)0, (i + 1 +  total_decrease - a[i])/(i + 2)));
		else ans = ans = min(ans, i + 1 +  max((ll)0, (i  +  total_decrease - a[i])/(i + 1)));
	    //BE VERY CAREFUL TO INCLUDE DECREASING THE MIN
	}
	cout << ans << endl;

}

int main() {
	int t; cin >> t; while(t--) solve();
}
