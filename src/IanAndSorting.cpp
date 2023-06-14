// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<ll> vi;

int n; vi a;

void solve(){
	cin >> n; a.resize(n);
	for(ll& i: a) cin >> i;
	for(int i = 1; i<n; i++){
		if(a[i] < a[i - 1]){
			ll diff = a[i - 1] - a[i];
			//must increase i  and i + 1 by diff
			if(i + 1 < n){
			  a[i] += diff;
			  a[i + 1] += diff;
			}
			
		}
	}
	//note that this turns 5 4 3 2 1 - > 5 5 5 5 3...5 3, then fails,so the only way tofix this is to the next two 5s below it
	//and decrease them to 3 ..we might have to go backwards and now fix these
	//really just look at where my intuition faisl for 5 4 3 2 1, and ull realize this is the only choice we have 
	for(int i = n - 1; i > 0; i--){
		if(a[i]  < a[i - 1]){
            ll diff = a[i - 1] - a[i];
			if(i - 2 >= 0){
				a[i - 2] -= diff;
				a[i - 1] -= diff;
			}
		}
	}
    cout << ((is_sorted(a.begin(), a.end())) ? "YES":"NO") << endl;
}

int main() {
	int t; cin >> t; while(t--) solve();
}
/*
thought space:
4
2 1 4 3
2 3 5 3

Obs 1:  say
a_0 > a_1
Then we HAVE TO apply an operation
on (1,2)

a_n < a_(n - 1)
we HAVE O apply an operation on (n - 1, n - 2)
Lets think about the array

sorted prefix, then decreases somewhere, lets say i
a_1, a_2, a_3, ..., a_i
so we have a_i < a_(i - 1). Then we need
to make a_i BIGGER..this means an operation MUST BE applied
on a_i and a_(i + 1). In particular, we need to make this bigger by
a_i - a_(i - 1)...so do we force the pair i and i + 1 to be increased by that ore more???

3 6 9 12 10 11  - > 3 6 9 12 10 12 13
3 6 9 12 10 9 -> 3 6 9 12 12 11 a b 
So, whenever we have an inveserion or just decrease so a_i < a_(i - 1)
then we must operate on i + 1 and i:
case 1 a_(i + 1) >= a_i 

in this case you increase by obv just the difference so a_(i - 1) - a_i
Case 2 
a_(i + 1) < a_i 
            7  6 3
            7   7 4 

Any time we have decrease, so we have a_i < a_(i - 1)
define d : a_(i - 1) - a_i 
so loop through 1 to N, anytime we have this, then 
we have to increase the element a_i += dand a_(i + 1) += d...well, only if i + 1 is in bounds 

we do this and just check if array is sorted afterwards 







*/
