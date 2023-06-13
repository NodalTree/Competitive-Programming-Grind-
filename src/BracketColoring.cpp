#include <iostream>
#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<int, int> pi;

int n; string s;
void solve(){
  cin >> n >> s;
  int count = 0;
  //first lets check if it
  bool above = false;
  bool below = false;
  for(int i = 0; i<n; i++){
    if(s[i] == '(') count++; 
    else count--;
    if(count < 0) below = true;
    if(count > 0) above = true;
  }
  
  if(count != 0){
    //this means that there isn't the same as amount of ( as there is )
    cout << -1 << endl;
  }else{
    if(!below || !above){
      cout << 1 << endl;
      //if the the graph was stictly never below or never above, then it will always be doable in 1
      for(int i = 0; i <n; i++) cout << 1 << " ";
      cout << endl;
    }else{
      //then it's doable in 2 we just connect the belows as color 2 and aboves as color 1 or vice versa
      cout << 2 << endl;
      for(int i = 0; i<n; i++){
        int last = count;
        if(s[i] == '(') count++;
        else count--;
        if(last == 0){
          //either we can increase we go above or below here
          if(count > 0){
            //if we choose to go above,will color it one
            cout << 1;
          }else{
            //if we go below, will color it 2
            cout << 2;
          }
        }else{
          //then we are cotinuing 
          if(last > 0){
            //then we will still be above no matter what
            cout << 1;
          }else{
            cout << 2;
          }
        }
        cout << " ";
      }
      cout << endl;
    }
  }
}
int main() {
   int t; cin >> t; while(t--) solve();
}
