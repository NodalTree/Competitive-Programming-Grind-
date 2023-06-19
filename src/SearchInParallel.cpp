// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<int, int> pi;
typedef vector<pi> vp;

bool high_sort(const pair<int, int> &a, const pair<int, int> &b){
	return a.second > b.second;
}
//gonna try greedy by sorting by highest requested and just make best des locally, not sure why this exactly works tho if it ends up
void solve(){
	int n; int s1; int s2;
	cin >> n >> s1 >> s2;
	vp requests; requests.resize(n);
	for(int i = 1; i<=n; i++){
		int amount = 0;
		cin >> amount;
		requests.push_back(make_pair(i, amount));
		
	}
   sort(requests.begin(), requests.end(), high_sort);//sort by highest requests as these need better positions, this greedy should be very obvious
   vi a; vi b;
   for(int i = 0; i<n; i++){
	   pair<int, int> curr_ball = requests[i];
	   int size_a = a.size();
	   int size_b = b.size();
	   //costs if we place in either a or b 
	   ll cost_a = (ll) (size_a + 1) * s1;
	   ll cost_b = (ll) (size_b + 1) * s2;
	   if(cost_a == cost_b){
		   //not sure about this local choice, but i would allow the list with cheaper s grow larger?
		   if(size_a == size_b){
			   a.push_back(curr_ball.first);
		   }else if(size_a > size_b){
			   //it's s is cheaper, allow this one to grow???
			   a.push_back(curr_ball.first);
		   }else{
			   b.push_back(curr_ball.first);
		   }//make best local choice now 
	   }else if(cost_a > cost_b){
		   b.push_back(curr_ball.first);
	   }else{
		   a.push_back(curr_ball.first);
	   }
   }
   cout << a.size() << " ";
   for(int i: a) cout << i << " ";
   cout << endl;
   cout  << b.size() << " ";
   for(int i: b) cout << i << " ";
   cout << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}
