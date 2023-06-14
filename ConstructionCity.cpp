// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;

int n; vi a;

void solve(){
	cin >> n; a.resize(n);
	for(int& i: a) cin >> i;
	sort(a.begin(), a.end());
	ll ans = n/2;
	for(int i = 0; i + 1<n; i++){
		if(a[i] != a[i +  1]){
			//make a cut at i only if next element is not equal to it
			ans = max(ans,(ll) (i + 1)  * (ll) (n - i - 1));
		}
	}
	cout << ans << endl;
	
}
int main() {
	int t; cin >> t; while(t--) solve();
}
/*
thought space:
so, lets realize that this is saying we cannot have edges that go in increasing order.
One observation that is pretty obvious is that you can't have triangles; you can either form a triangle
MISSING an edge or just connect two vertices. The second one seems subopitmal, so we look at when we can
form a triangle missing one edge, for convience call this a v component. We notice that in a v
component we never want these numbers to be increasing from bottom endpoint to middle then to other bottom endpoint.
Think of a set of three disinct numbers {a, b, c} sorted from least to greatest, we never want to put
the middle num on the apex of the v... the middle number must go on one of the bottoms. Which means
either a or c is the apex on trhe v component. Now what's interesting here is we can kind of think of a v component
as two colorable or bipartite in a sense. Because if a v component is apexed with an "a", then the nodes opposite will
always be bigger, and if a v component is apexed with a "c", the nodes opposite will be smaller. So, we have
this pretty cool property: when we have an edge we must always choose to construct it with something bigger or smaller than it.
Then by symmetry, we can just think of handling the nodes in which we will connect something bigger to it.
How so we know these set of nodes? Well, this is where we can split the sorted array at some index i. So then,
for each node in the smaller section we can connect to each node that is clearly bigger to it in the bigger section.
Now you might be asking what rto do when nthe same number is in both sections:
like 1 3 3 3 3 3   6 6 split in the middle small { 1 3 3 } big {3 3}. Clearly, you can'tconnect all small to big
as itll violate the conditionlike 1 3 3 | 3 3 3...why would u cut here? when u move those two 3's on the other side
clearly, the big one's cant even connect to the two 3's on the left side, so might as well put them into the right side so it can create more edges
what if all the same like 3 3 3 3 3 just take pairs and connect obv can't do v components n/2
1 2 |




*/
