#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vi;

void solve(){
  ll n; ll m; ll k; 
	cin >> n >> m >> k; vi a; a.resize(m);
	for(ll& i: a) cin >> i;
	bool good = true;
	ll windows_avail = (n + k - 1)/k;
	ll last = n % k;
    if(last == 0) last = k;
	ll needed_last = 0;
	for(int i = 0; i<m; i++){
		//how many windows a number requires
		ll windows = a[i];
		if(windows > windows_avail){
			good = false;
			break;
		}else if(windows == windows_avail){
			//tricky part here, i guess
			needed_last++;//then we we know that the last sergment, which is n mod k long
			//needs one more 
		}
	}
	cout << ((needed_last <= last && good) ? "YES":"NO") << endl;

}
int main() {
  int t; cin >> t; while(t--) solve();
}
