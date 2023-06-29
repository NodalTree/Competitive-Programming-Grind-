// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

/*
Vlad starts the game in the room 1
 and wins if he reaches a room other than 1
, into which exactly one corridor leads. This statement basically means that can we reach a leaf node before getting caught.
Statement was a bit unclear in its wording should've said where only one corridor is connected to it instead of saying leads
(leads into??)...point is use sample to sort of see it's leaf nodes.

Now after thats in the way in order to reach a leaf node we must have been able to visit basically an entire path without getting caught.
So, now the question extends how do we know if we get caught at some vertex v? Well that implies there was no red node that was caught
p[v] otherwise we would've stopped exploring that path. Thus, we know node v can get caught if there exists a node u, which is red
in which the dist from u to v is <= the depth of v. So now the question extends again, do we know if there is such a node u that exists?
Well cleary we only care about the closest red node to v...but then the question now extends how do we know this.
Well, I claim we only care about the closest node in the subtree of v. Why? Well if we are at v, we can assume that there are obviously
no red nodes in its path, otherwise we would've stoppped.. That means at some point we will go less than the depth up and swing into some other node 
in a different subtree...if that node is closest to v. lets say we went up y, then go down x to find that node...the if the node stops
v, then it also stops the node y up. I guess this only a small doubt, but should be intuitive.

*/
vi closest;
bool works = false;
void dfsClosest(int node, vector<vi>& graph, vector<bool>& red, vector<bool>& vis, int pa){
   vis[node] = true;
   int mn = INT_MAX;
   for(int ch: graph[node]){
      if(vis[ch])  continue;
      dfsClosest(ch, graph, red,  vis, node);
      if(ch == pa || closest[ch] == INT_MAX) continue;//could possibly not have any red nodes near it, so we just put it at "infinity"
      mn = min(mn, closest[ch]  + 1);//otherwise this is simple tree dp
   }
   if(!red[node]) closest[node] = mn;//and if we found node red nodes in the subtree it's at infinity 
}
void dfs(int node, vector<vi>& graph, vector<bool>& red, vector<bool>& vis, int pa, int depth){
   vis[node] = true;
   if(graph[node].size() == 1 && node != 1){
      works = true; //we know it works if we ever get to this point 
   }
   //otherwise 
   for(int ch: graph[node]){
      if(red[ch] || vis[ch] || closest[ch]  <= depth + 1) continue;//main condition is if we going to this node, would get us caught, we don't start a dfs there
      dfs(ch, graph, red,vis, node, depth + 1);
   }
}
void solve(){
   int n; int k; cin >>  n >> k; vector<vi> graph; graph.resize(n + 1); vector<bool> red; red.resize(n + 1);
   closest.resize(n + 1);
   for(int i = 1; i<=n; i++) closest[i] = 0;  
   works = false;
   for(int i = 0; i<k; i++){
      int v; cin >> v;
      red[v] = true;
   }
   for(int i = 0; i<n - 1; i++){
      int u; int v; cin >> u >> v;
      graph[u].push_back(v);
      graph[v].push_back(u);
   }
   //lets get closest
   vector<bool> vis; vis.resize(n + 1);
   dfsClosest(1, graph, red,vis, -1);
   for(int i = 1; i<=n; i++) vis[i] = false;
   dfs(1, graph, red,  vis, -1, 0);
   cout << (works ? "YES":"NO") << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}
