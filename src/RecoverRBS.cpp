// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef vector<int> vi;
typedef long long ll;


void solve(){
    string s; cin >> s;
	int n  = s.size();
	vi a; a.resize(n);
	int q = 0;
	for(int i = 0; i<n; i++){
		if(s[i] == '(') a[i] = 1;
		else if(s[i] == ')') a[i]  = -1;
		else q++;
	}
	int sum = 0; for(int i: a) sum += i;
	//by rbs properties the last sum must be 0
	//this helps us find the amount of opens that will be used and closed
	//we know intuitively the way to optimally get an rbs is bby spamming 
	//1's in the beg to make sure the sum is as high it is before it goes down
	//but we know to obviously know how many opens we can spam
	//thats there the sum having to be 0, helps us
	int open = (q - sum)/2;
	int close = (q  + sum)/2;
	vi second_best; second_best.resize(n);
	for(int i = 0; i<n; i++){
		if(a[i] == 1) second_best[i] = 1;
		else if(a[i] == -1) second_best[i] = -1;
		else if(open > 1){
			second_best[i] = 1;
			open--;
		}else if(open == 1){
			//at this point we go down
			second_best[i] = -1;
			open--;
		}else if(open == 0){
			//at this point we go up again
			second_best[i] = 1;
			open--;
		}else{
			second_best[i] = -1;//now we start going down
		}
	}
	sum = 0;
	bool bad = true;
	for(int i: second_best){
		sum += i;
		if(sum < 0){
			bad = false;
			break;
		}
	}
	if(sum != 0){
		//then we know we don't have an rbs, so answer must have to be true
		bad = false;
	}
	if(open == 0 || close == 0){
		bad = false;
	}
	cout << (bad  ? "NO": "YES") << endl;
	
}
int main() {
   int t; cin >> t;
   while(t--) solve();
}
