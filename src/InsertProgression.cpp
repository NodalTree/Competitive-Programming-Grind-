// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vi;
/*
Dealing with abs value is the same as dealing with number line and distances, so think about each pair of numbers as segment. Then, 
clearly all segments form a continous union segment, U. This means that any number in U will not affect the value of the sequence (idea is from
contrast value, it is basic tho). Thus, we want to expand U as much as we cand right to make the x insertions of no extra cost.
Well, what we know that inserting 1 and  x will guarantee a contious range of 1 to X in U. This implies that 2 ... X - 2 wll never
affect incur  no extra cost on the og value. Where should we place 1 and X though??  Fix the position of 1 and find best position of x is either after or between.. we can precompute prefix mins and max 
*/
void solve(){
	int n; ll x; cin >> n >> x;
	vi a; a.resize(n);
	for(ll& i: a) cin >> i;
	ll og = 0;
	for(int i = 0; i + 1<n; i++){
	   og += abs(a[i] - a[i + 1]);
	}
	ll ans = LLONG_MAX;
	//try fixing the position of 1 
	vi extra_cost; extra_cost.resize(n);
	//extra_cost[i] represents the extra cost that would be inccured if we placed x in between i and i + 1
	extra_cost[n - 1] = abs(x - a[n - 1]);
	for(int i = n - 2; i>=0; i--){
		extra_cost[i] = abs(x - a[i]) + abs(a[i + 1] - x) - abs(a[i + 1] - a[i]);
	}
	//run prefix mins and suffix mins on extra cost
	vi p_min; p_min.resize(n); vi s_min;  s_min.resize(n);
	p_min[0] = extra_cost[0]; s_min[n - 1] = extra_cost[n - 1];
	for(int i = 1; i<n; i++){
	    p_min[i] = min(p_min[i - 1], extra_cost[i]);
	}
	for(int i = n - 2; i>= 0; i--){
		s_min[i] = min(s_min[i + 1], extra_cost[i]);
	}
	//now inserting 1 in each pos0ition...insert it in beg first for base case 
	//couple of annoying cases
	//x 1 array  1 1 3 2 
	ans = abs(x - 1) + abs(a[0] - 1) + og;
	//1 x array 
	ans = min(ans, abs(x - a[0]) + abs(x - 1) + og);
	//1 array x in this 
	ans = min(ans, p_min[n - 1] + abs(1 - a[0]) + og);
	for(int i = 1; i<n; i++){
		//insert it before position i 
		//anoying cases
		//   i - 1,  1, i, some x 
		ll ans_i = abs(1 - a[i - 1]) + (a[i] - 1) - abs(a[i] - a[i - 1]) + og;
		ll extra = s_min[i];
		if(i - 2 >= 0){
			extra = min(extra, p_min[i - 2]);
			extra = min(extra, abs(x - a[0]));
		}else{
			//x goes in beg
			extra = min(extra, abs(x - a[0]));
		}
		ans_i += extra;
		//now lets do
		//i - 1, x, 1, i
		ans_i = min(abs(x - a[i - 1]) + abs(1 - x) + abs(a[i] - 1) - abs(a[i] - a[i - 1]) + og, ans_i);
		//now lets do
		//i - 1, 1, x, i
		ans_i = min(abs(1 - a[i - 1]) + abs(1 - x) + abs(a[i] - x) - abs(a[i] - a[i - 1]) + og, ans_i);
		ans = min(ans, ans_i);
	}
	//insert at position n - 1...so array 1 x array x  1 
    ans = min(ans, abs(x - 1) + min(abs(x - a[n - 1]), abs(1 - a[n - 1])) + og);
	//now annoyingly    ar x || ay 1 
	if(n - 2 >= 0)
	ans = min(p_min[n - 2] + abs(a[n - 1] - 1) + og, ans);
    cout << ans << endl;
}
int main() {
   int t; cin >> t; while(t--) solve();
}
