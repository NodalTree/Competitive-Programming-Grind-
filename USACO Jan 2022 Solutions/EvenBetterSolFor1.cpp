// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll solve(ll a, ll b, bool add_state){
     if(a == b) return 0;
     if(add_state) return (a % 2 == 1) + 1 + solve(max((a + 1)/2, b), min((a + 1)/2, b), (a + 1)/2 > b);
     else return (a/2 > b) ? (a % 2 == 1) + 1 +  solve(a/2, b, add_state) : min(a - b,  (a % 2 == 1) + 1 + solve(b, a/2, !add_state));
}
int main() {
    int n; cin >> n; while(n--) {ll a, b; cin >> a >>  b; cout << solve(max(a, b),min(a,b), a > b) << endl;}
}
