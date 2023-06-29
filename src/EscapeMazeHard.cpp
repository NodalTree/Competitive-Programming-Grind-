// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

/*
Lol E2 is no harder than E1, we can use same code just different approch in dfs function 

*/
vi closest;
int ans = 0;
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
      works = true; //we know it works, we'd have to print out -1...this means there is way in which vlad can win 
   }
   //otherwise 
   for(int ch: graph[node]){
      if(red[ch] || vis[ch] || closest[ch]  <= depth + 1){
         //i know that if i can catch vlad here, then there is minimally one node that in the subtree of v that stops vlad here
         //i only need to add this into my set of friends who can catch vlad...i don't care about any other node 
         //think about if there are no nodes that work along this path, then the answer is obv -1
         //but if there is node that works along this path i only care about when it first worked
         //and i'll only take one node/...in other words just increment answer here 
         if(!vis[ch] && closest[ch]  <= depth + 1)
         ans++;
         continue;//end path exploration here 
      }
      dfs(ch, graph, red,vis, node, depth + 1);
   }
}
void solve(){
   int n; int k; cin >>  n >> k; vector<vi> graph; graph.resize(n + 1); vector<bool> red; red.resize(n + 1);
   closest.resize(n + 1);
   for(int i = 1; i<=n; i++) closest[i] = 0;  
   works = false;
   ans = 0;
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
   cout << (works ? -1 : ans) << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}
