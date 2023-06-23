// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<ll> vl;
typedef vector<int> vi;

void solve(){
vector<long long> pow; pow.resize(5);
pow[0] = 1; pow[1] = 10; pow[2] = 100; pow[3] = 1000; pow[4] = 10000;
string s; cin >> s;
int n = s.size();
vector<vector<long long>> pref_cnt;//define pref_cnt[i][j] as the amount of the jth letter in the ith prefix 
pref_cnt.resize(n + 1);
for(int i = 1; i<=n; i++){
	pref_cnt[i].resize(5);
}
    ll a = 0; ll b = 0; ll c = 0; ll d = 0; ll e = 0;
	for(int i = 1; i<=n; i++){
	if(s[i - 1] == 'A') a++;
	else if(s[i - 1] == 'B') b++;
	else if(s[i - 1] == 'C') c++;
	else if(s[i - 1] == 'D') d++;
	else if(s[i - 1] == 'E') e++;
	pref_cnt[i][0] = a;
	pref_cnt[i][1] = b;
	pref_cnt[i][2] = c;
	pref_cnt[i][3] = d;
	pref_cnt[i][4] = e;
	}
vi suff_max;//define suffix max as the maximum letter in a suffix
suff_max.resize(n + 1);
int mx = s[n - 1] - 'A';
	for(int i = n; i >= 1; i--){
	mx = max(mx, s[i - 1] - 'A');
    suff_max[i] = mx;
	}
/*vector<vi> right_most; right_most.resize(5);
//right_most index of aletter j will always be right_most.back()
vector<vl> delta;//define delta[i][j] as how much the sum would change if we use letter j in position i
vector<vi> under;//under[i] will return the index that is under i and is the greater than the letter at i, the biggest index
under.resize(n + 1);
	for(int i = 1; i<=n; i++){
	//lets base case A
	//how much would the letter change if A was in this posiion
	int j = s[i - 1] - 'A';
	if(j == 0){
		//obviously this would change by 0
		delta[i][1] = 0;
		continue;
	}
	int rm = 0;
	if(right_most[j].size() != 0){
		rm = right_most[j].back();
	}
	if(i + 1 > n){
		delta[i][1] = -pow[j - 1] * (pref_cnt[i][j - 1] - pref_cnt[rm][j - 1]) + suff_max[i;
	}else if(suff[i + 1] <= j - 1){
        delta[i][1] = -pow[j - 1] * (pref_cnt[i][j - 1] - pref_cnt[rm][j - 1]);//all these get negated 
	}
	
	under[i] = rm;
	right_most[j].push_back(i);
	}
right_most.clear();
//alright now we can plug in formula
ll max_change = 0;
	for(int i = 1; i<=n;i++){
		for(int j = 1; j<5; j++){
       delta[i][j] = delta[i][j - 1] - pow[j - 1] * (pref_cnt[i][j - 1] - pref_cnt[under[i]][j - 1]) + (j >= suff_max[i] ? pow[j]: -pow[j]);
	   max_change = max(max_change, delta[i][j]);
		}
	}
ll sum = 0;
	for(int i = 1; i<=n; i++){
		int j = s[i - 1] - 'A';
		if(j >= suff_max[j]){
		sum += pow[j];
		}else{
		sum -= pow[j];
		}
	}
sum += max_change;
cout << sum << endl;*/
}


int main() {
	int t; cin >> t; while(t--) solve();
}
