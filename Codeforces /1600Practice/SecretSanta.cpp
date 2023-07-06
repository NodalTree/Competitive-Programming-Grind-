

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
/*
Lets think of the input as a directed graph  where there is a directed edge between i and a_i. Notice that this graph forms
a functional graph where every node has exactly one outgoing edge. However, we need to reshape this graph such that the amount
of ingoing edges (indegree) of every node is exactly one  as well. Because the problem requires everyone to give and also recieve a gift.
Also note, we never have to worrying about fufilling the condition that every node has one outgoing edge, so we can fully put our attentions
to ingoing edges. Alright, so for any node that has an indegree of exactly 1, leave it. So, now we are dealing with nodes that have
0 ingoing edges or more than 1 outgoing edges. Any node that has more than outoing edge, call it a giver node, and on the other hand,
any node that has an ingoing edge of 0, call it a taker node. Then, we try to fufill the taker nodes. A taker node will need
to get one edge pointing to itself (that is not itself obviously). How will it do this? Well, we just take an ingoing edge from
a giver node (assume this obviously not the ingoing edge that is the outgoing edge of the taker node we are trying to appease).,
and spin it around to point to the taker node. Notice, how we never decrease the outdegree of any node and we only decrease the indegree
of node that can already give in the first place (it must give so that it works). Now we can forget about this node and thus throw it out.
Then we will do this for each taker node, and once this is fufilled, we know that we are good. 

*/


void solve(){
	int n; cin >> n; vi a; a.resize(n + 1); for(int i = 1; i <= n; i++) cin >> a[i];
	vector<vi> in_deg; vi ans;  in_deg.resize(n +1); ans.resize(n + 1); set<int> giver; vi taker; 
	for(int i = 1; i <= n; i++){
		in_deg[a[i]].push_back(i);//only have to worry about indegrees 
		ans[i] = a[i];
	}
	for(int i = 1; i<=n; i++){
		if(in_deg[i].size() == 1) continue;
        else if(in_deg[i].size()) giver.insert(i);
		else taker.push_back(i);
	}
	for(int taker_node: taker){
		//we wish to use a giver node, and take one of its ingoing  edges and spin it around 
		int giver_node = *giver.begin();
		int inter_node = -1;//this is the node that connects to giver that we will use to spin edge around
		//notice how the indegree of inter doesn't get affected and the outdeg still reamsin one n
		int i = -1;
        if(in_deg[giver_node][in_deg[giver_node].size() - 1] != taker_node){
			i = in_deg[giver_node].size() - 1;
		}else{
			//we obviosuly cant make inter the taker node 
			i = in_deg[giver_node].size() - 2;
		}
		inter_node = in_deg[giver_node][i];
		//now let inter_node give a gift to taker_node to increase indegree (i guess iinter node is tech the giver, but wutever)
		ans[inter_node] = taker_node;
		in_deg[giver_node].erase(in_deg[giver_node].begin() + i);//since we are either removing second to last or last, this is fast removal 
		if(in_deg[giver_node].size() <= 1) giver.erase(giver.begin());//might not be able to give 
	}
	int k = 0;
	for(int i = 1; i<=n; i++){
		k += (ans[i] == a[i]);//how many matchings we have in the end
	}
	cout << k << endl;
	for(int i = 1; i<=n; i++) cout << ans[i] << " ";
	cout << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}

