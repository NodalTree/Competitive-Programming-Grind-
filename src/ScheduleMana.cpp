// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vi;//factory means at least on proficient

bool check(ll time, ll non_fac, vi count, ll n){
	//the idea that if a check if it's possible in such a time t
	//then for each factory owner, it's always opitmal to put time of them on their respective blocks and then the other
	//non_fac, this is only possbile if there's a sufficient amount of non_facs availabe
	//this construction is clearly opitmal because it reduces the amount of non_facs needed as much as possible 
	ll sum = 0;
	ll extra_help = 0;
	for(int i = 1; i<= n; i++){
		if(time == 1 && count[i] > 1) return false;//edge case here we might want 1 hour, but like non facs use 2 hours if needed 
		if(count[i] != 0){
			//this is non_fac
			sum += max(count[i] - time,(ll) 0);
			extra_help += max((ll)0, time - count[i]);//count[i] might be less than time in this case we have extra help 
		}
	}
	return non_fac >= sum - extra_help;
}
void solve() {
	int n; int m; cin >> n >> m; vi a; a.resize(m); vi count; count.resize(n + 1);
	for(ll& i: a) {cin >> i; count[i]++;}
	ll non_fac = 0;
	for(int i = 1; i<=n; i++){
		if(count[i] == 0) non_fac++;
	}
	ll l = 1;
	ll r = (int) pow(10, 9);
    while(l < r){
		ll mid = l + (r - l)/2;
		if(check(mid, non_fac, count, n)){
			//then if it works try to make smaller
			r = mid;
		}else{
			//make bigger
			l = mid + 1;
		}
	}
	cout << l << endl;
}

int main(){
	int t; cin >> t; while(t--) solve();
	return 0;
}
