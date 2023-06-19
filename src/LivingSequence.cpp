#include <iostream>
//saw this in the aops number theory book onw time, idea is that now there are only 9 digits, thus numbers act like they are in base 9, and 5 is really a 4 and 6 is really 5 etc...
//convert number to base 9 and then convert any number greater than 5 to itself +  1
//time complexity, should be O(N), yeah we should be fine 
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

char reVal(int num)
{
    if (num >= 0 && num <= 9)
        return (char)(num + '0');
    else
        return (char)(num - 10 + 'A');
}
string fromDeci(string& res, int base, ll inputNum)
{
    int index = 0; // Initialize index of result
 
    // Convert input number is given base by repeatedly
    // dividing it by base and taking remainder
    while (inputNum > 0) {
        res.push_back(reVal(inputNum % base));
        index++;
        inputNum /= base;
    }
 
    // Reverse the result
    reverse(res.begin(), res.end());
 
    return res;
}
void solve(){
  ll k; cin >> k; string s;
  s = fromDeci(s, 9, k);
  //cout << s << endl;
  for(int i = 0; i<s.size(); i++){
    if(s[i] == '4') s[i] = '5'; else if(s[i] == '5') s[i] = '6'; else if(s[i] == '6') s[i] = '7'; else if(s[i] == '7') s[i] =
    '8'; else if(s[i] == '8') s[i] = '9';
  }
  cout << s << endl;
}

int main() {
  int t; cin >>t; while(t--) solve();
}
