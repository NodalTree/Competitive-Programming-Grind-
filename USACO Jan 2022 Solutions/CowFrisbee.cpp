// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

vi a; int n;

void solve(){
    int n; cin >> n; a.resize(n);
	for(int& i: a) cin >> i;
	/*
     fix position i as the minimum, then we know that we need to find the first index to the right of it and left of it,
	 that is greater or equal to it, why? well if this is fixed as min, then we know min can't drop... we also know we don't want
	 any heights in between that is greater than it
	 how do we find these index 
	*/
	ll ans = 0;
	stack<int> s;
	s.push(0);
	for(int i = 1; i<n; i++){
	   while(s.size() != 0 && a[s.top()] < a[i]){
		   //we know that this can never be an answer because a[i] will always better option
		   s.pop();
	   }
	   if(s.size() != 0 && a[s.top()] > a[i]){
		   ans += (i - s.top() + 1);
	   }
	   s.push(i);
	}
	stack<int> z;
	z.push(n - 1);
	for(int i = n - 2; i>=0; i--){
		while(z.size() != 0 && a[z.top()] < a[i]){
              z.pop();
		}
		if(z.size() != 0 && a[z.top()] > a[i]){
			ans += (z.top() - i + 1);
		}
		z.push(i);
	}
	cout << ans << endl;

}
int main() {
	solve();
}
