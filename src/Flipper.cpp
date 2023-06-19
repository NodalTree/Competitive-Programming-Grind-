#include <iostream>
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
/* I think this is fairly straightforward. The whole idea is founded on the fact that
we want earlier positions to have greater numbers as much as we can. Like basically I don't care
about position b ( assume b > a) if Ihaven't worried about position a. With that I think solution
is pretty obvious not going to lie. Code should explain what I'm doing. 
*/

void solve(){
  int n; cin >> n; vector<int> a; a.resize(n);
  for(int& i: a) cin >> i;
  //lets handle edge case, suffix would get nothing in this case 
  if(n == 1){
    cout << 1 << endl;
    return;
  }
  vector<int> prefix, middle, suffix;
  //lets get the suffix first 
  int suff_start = -1;
  int max =  -1;
  for(int i = 1; i<n; i++){
    //can only start on i >= 1...see this in test case with 10 as first number
    if(a[i]  > max){
      max = a[i];
      suff_start = i;
    }
  }
  //now we can get the suffix 
  for(int i = suff_start; i<n; i++){
    suffix.push_back(a[i]);
  }
  //lets get the middle now
  int middle_start = suff_start - 1;
  for(int i = suff_start -2; i >= 0; i--){
    //only include if it's
    if(a[i] > a[0]){
      middle_start = i;
    }else{
      break;
    }
  }
  //suffix might be empty as suggested in 7th test case
  //we need to handle this case ....only case is if middle behaves a suffix..same logic as
  //now we can get middle
  for(int i = middle_start; i<suff_start; i++){
    middle.push_back(a[i]);
  }
  //okay now prefix is fixed, notice how we worried about suffix first, then middle, then lastly prefix
  //this mirrors the idea i said earlier we know suffix will be first once we apply the operation, so
  //we worry about it first etc...
  for(int i = 0; i<middle_start; i++){
    prefix.push_back(a[i]);
  }
  if(a[n - 1] == max){
   //here we have an annoying edge case to take care over
   //bc now this implies that this "suffix" we are assuming could
   //actually represent the middle section and next to is an empty suffix 
   //so we know that this element has to be in front
   //should the element next to it be in next to it?
   //only if it's greater than the prefix we will move over it
   int p = n + 1;
   cout << max << " ";
   for(int i = n - 2; i >= 0; i--){
      if(a[i] > a[0]){
        //if this is true expand middle which will be in front
        //if it's not, then obv don't expand it
        cout << a[i] << " ";
      }else{
        p = i;
        break;
      }
   }
   if(p != n + 1 || true){
    for(int i = 0; i <= p; i++){
      cout << a[i] << " ";
    }
   }
   cout << endl;
  }else{
  //we print suffix first
  for(int i: suffix) cout << i << " ";
  //print middle reversed now due to operation
  for(int i = middle.size() - 1; i >=0; i--) cout << middle[i] << " ";
  //now print out prefix
  for(int i = 0; i<prefix.size(); i++) cout << prefix[i] << " ";
  cout << endl;
  }
}
int main() {
  int t; cin >> t; while(t--) solve();
}
