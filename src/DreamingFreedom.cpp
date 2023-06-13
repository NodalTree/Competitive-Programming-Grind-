#include <iostream>
#include <bits/stdc++.h>


using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<int , int> pi;

int n, m;
void solve(){
cin >> n >> m;
//we don't need to handle m = 1 edge case it is generalized into finding divisor from 2 to M
  bool good = true;
  for(int i = 2; i<= min(m, (int)sqrt(n)); i++){
    if(n % i == 0){
      good = false;
      break;
    }
  }
  if(m >= n && n != 1) good = false;
  cout << (good ? "YES":"NO") << endl;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0); 
  int t; cin >> t; while(t--) solve();
}
  /*basically we notice that if M | N, then each algorithm recieve an equal amount of votes which would mean there is a possbility that it continues indefinitely, so the question is can we decrease M such that it becomes a divisor of N...well how much can we decrease M in one operation, yes simply just have one algorithm to be voted on by every single person - 1 and have that person vote a different algorithm, removing the one with obviously more. This means that this algorithm leaves and M decreases by 1... This means that you can decrease M as much as you can. However there is already one opition, so if M = 1 we print YES, also if. */
