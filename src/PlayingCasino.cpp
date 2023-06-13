#include <iostream>
#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<int, int> pi;

int n, m; 
vector<vi> a;

void solve(){
  cin >> n >> m;
  a.resize(n);
  for(int i = 0; i<n; i++){
    vi v; 
    for(int j = 0; j<m; j++){
      int x; cin >> x; v.push_back(x);
    }
    a[i] = v;
  }
  ll ans = 0;
  for(int c = 0; c<m; c++){
    ll last_winning = 0;
    vi v; 
    for(int r = 0; r<n; r++){
       v.push_back(a[r][c]);
    }
    sort(v.begin(), v.end());
    for(int r = 0; r<n; r++){
      a[r][c] = v[r];
      last_winning += (v[r] - v[0]);
    }
    ans += last_winning;
  
    for(int r = 1; r<n; r++){
      ll new_winning = last_winning - (ll) (a[r][c] - a[r - 1][c]) + (ll) (n - r - 1) * (a[r - 1][c] - a[r][c]);
      ans += new_winning;
      last_winning = new_winning;
    }
  }
  cout << ans << endl;
}


int main() {
  ios::sync_with_stdio(0);
  cin.tie(0); 
  int t; cin >> t; while(t--) solve();
}
