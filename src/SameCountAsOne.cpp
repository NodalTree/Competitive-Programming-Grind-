// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

struct binaryArray{
    int id;
	vi zero_indexes, one_indexes;
	int ones;
};
struct answer{
	int x, y, z;
};

void solve(){
	int n, m; cin >> n >> m;
	vector<binaryArray> all;//all the binary arrays 
	vector<vi> pos; pos.resize(m);
    int total_ones = 0;
	vector<vi> a; a.resize(n);
	for(int i = 0; i<n; i++){
		vi zero_indexes; vi one_indexes;
		for(int j = 0; j<m; j++){
           int num; cin >> num;
		   if(num == 1){
			   one_indexes.push_back(j);
		   }else{
			   zero_indexes.push_back(j);
		   }
		   a[i].push_back(num);
		}
		total_ones += one_indexes.size();
		all.push_back({i, zero_indexes, one_indexes, one_indexes.size()});
	}
	if(total_ones % n != 0){
		cout << -1 << endl;
		//obviously this impossible, otherwise it's not 
	}else{
	    int goal = total_ones/n;
		vector<answer> ans;
	    for(int i = 0; i<m; i++){
			//since we can only swap 1s and 0s in columns, lets fix a column and try to resolve the ones that need to be resolved
			vi give,take;
			for(int j = 0; j<n; j++){
               if(all[j].ones == goal) continue;
			   if(all[j].ones > goal && a[j][i] == 1){
				   give.push_back(j + 1);
			   }else if(a[j][i] == 0 && all[j].ones < goal){
				   take.push_back(j + 1);
			   }
			}
			//now we swap ones and zeroes
			for(int k = 0; k<min(give.size(), take.size()); k++){
                ans.push_back({give[k], take[k], i + 1});
				all[give[k] - 1].ones--;
				all[take[k] - 1].ones++;
			}
		}
		cout << ans.size() << endl;
		for(answer a: ans){
			cout << a.x << " " << a.y << " " << a.z << endl;
		}
	}
}

int main() {
	int t; cin >> t; while(t--) solve();
}
