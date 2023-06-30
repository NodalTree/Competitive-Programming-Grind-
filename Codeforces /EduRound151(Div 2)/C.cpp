#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

void solve(){
	string s; int m; string l; string r; cin >> s >> m >> l >> r;
	int n = s.size();
	vector<vi> first_index; first_index.resize(10);
	for(int i = 0; i<10; i++) first_index[i].resize(n + 1);
	//first_index[digit][i] represents the first index of digit in the ith suffix 
	for(int d = 0; d<10; d++){
		for(int i = 0; i<=n; i++){
			first_index[d][i] = -1;
		}
	}
	for(int i = n - 1; i>=0;i--){
		//loop through string to fill this out
		for(int d = 0; d<10; d++){
			if(s[i] - '0' == d){
				first_index[d][i] = i;//if ith char is d, then answer is i
			}else{
				first_index[d][i] = first_index[d][i + 1];//if not, then it's just last one 
			}
		}
	}
	int suffix = 0;//first the suffix is 0
	for(int i = 0; i<m; i++){
		//we always want to choose a character outside suffix 
		int mx = -1;//we always want to decrease size of suffix greedily
		//be careful should be not including position 
		for(int d = l[i] - '0'; d<=r[i] - '0'; d++){
            if(first_index[d][suffix] == -1){
				cout << "YES" << endl;//answer is yes return 
				return;
			}
			mx = max(first_index[d][suffix], mx);
		}
		suffix = mx + 1;//make suffix start as big as possible to greedily decrease it
	}
	//if we reached here the answer is for sure no
	cout << "NO" << endl;

}

int main() {
	int t; cin >> t; while(t--) solve();
}
