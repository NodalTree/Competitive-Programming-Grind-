// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int solve(ll a, ll b){
//define solve(a,b) as the minimum number of operations it takes to get a to b 
     if(a == b) return 0;//base case 
     if(a > b){
		//if a is bigger than b, a must decrease obviously
	    //then multiplying is essentially useless, why would increase abs(a - b), when we know at some point it must decrease
		//thus we just divide this by 2 
        ll additional = (a % 2 == 1) ? 1: 0;
		return solve((a + 1)/2, b) + additional + 1;
	 }else if(a <= b/2){
		//in this case 
		 ll additional = 0;
		 if(b % 2 == 1){
			 b--;
			 additional++;
		 }
		
		return solve(a, b/2) + 1 + additional;
	 }else{
		 ll additional = 0;
		 ll distance = b - a;
		 if(a % 2 == 1){
			 a++;
			 additional++;
		 }
		 if(b % 2 == 1){
			 b--;
			 additional++;
		 }
		return min(distance, solve(a/2, b/2) + 2 + additional);
	 }
}

int main() {
	int n; cin >> n;
	while(n--){
		ll a, b; cin >> a >> b;
		cout << solve(a,b) << endl;
	}
	
}
