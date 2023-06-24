// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll ten(int a){
	return (ll)pow(10, a);
}
ll getValue(string s){
    ll out = 0;
	int mx = -1;
    for(int i = s.size() - 1;i >= 0;  i--){
         int j = s[i] - 'A';
		 if(j >= mx){
           out += ten(j);
		 }else{
			 out -= ten(j);
		 }
		 mx = max(j, mx);
	}
	return out;
}
void solve(){
	string s; cin >> s;
	ll worst = -s.size() * ten(4);
	ll ans1, ans2,ans3;
    ans1 = ans2 = ans3 = worst;
	//if the optimal replacement, doesn't actually replace anything
	ans1 = getValue(s);
	//if the optimal replacement chooses to increase a letter, then only go to first occurences
	vector<bool> seen; seen.resize(5);
	int n = s.size();
	for(int i = 0; i<n; i++){
		int j = s[i] - 'A';
		if(!seen[j]){
			string t = s;
			t[i] = 'E';
			ans2 = max(ans2, getValue(t));
			seen[j] = true;
		}
	}
	for(int i = 0; i<=4; i++) seen[i] = false;
	for(int i = n - 1; i >= 0; i--){
		int j = s[i] - 'A';
		if(!seen[j]){
			string r = s;
			for(int d = j - 1; d>= 0; d--){
				r[i] = (char) (d + 'A');
				ans3 = max(ans3, getValue(r));
			}
			seen[j] = true;
		}
	}
    ll best = max(max(ans1, ans2), ans3);
	cout << best << endl;
}
int main() {
  int t; cin >> t; while(t--) solve();
}

//A -> 10^0
//B -> 10^1
//C -> 10^2
//D -> 10^3
//E -> 10^4
//E's are always positive 
//if we choose to increase a number, we might as well choose to increase
//the first occurence of it, greedily make this E 
/*if  the optimal replacement, choose to increase a letter, then it's the first occurence
of the letter it's replacing.
What happens if the optimal replacement, chooses to decrease a letter
Then, it's the last occurence of the letter it's replacing...since it's 
not too obvious to see what to decrease letter too because we don't know if we should
greedily improve the prefix by making it A (but then that position could become negative etc...),
we will just try decrease a letter down for all its options 
*/
//DDDDAAADDABECD -> DDDDAAADCABECD

