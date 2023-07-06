// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<pair<ll, ll>> vp;

/*
It takes less amount of "time" for items with lower b_i values
to drop to 1, we know we are going to have to buy all items, so obviously
we can let the lower ones drop and then buy all of them at 1.
High values take longer, so it makes sense to buy them when all of the prices
cost 2. In fact, just buy min of max at first 



*/
bool comp(const pair<ll, ll>& a, const pair<ll, ll>& b){
	if(a.first == b.first) return false;
	return a.first < b.first;
}

void solve(){
	int n; cin >> n; vp items; 
	for(int i = 0; i<n; i++){
		ll a; ll b; cin >> a >> b;
		items.push_back(make_pair(b, a));
	}
	sort(items.begin(), items.end(), comp);//sort by start points
	int l = 0;
	int r = n - 1;
	ll bought = 0;
	ll ans = 0;
	ll used = 0;
	for(pair p: items) used += p.second;
	//first is threshold, second is count
	while(l <= r){
	   if(bought >= items[l].first){
		   //check if bought is at least threshold
          ans += items[l].second;
		  bought += items[l].second;
		  l++;
	   }else{
		   int need = min(items[l].first - bought, items[r].second);//amount we can use of last product
           bought += need;//now last one is competely bought
		   items[r].second -= need;
		   ans += 2 * need;
		   if(items[r].second == 0){
			   r--;
		   }
	   }
	   if(bought == used) break;
	}
	cout << ans << endl;

}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	solve();
}
