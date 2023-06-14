void solve(){
	int n; vi a, ex_max, m; vector<vi> indexes;
	cin >> n; a.resize(n + 1); for(int i = 1; i<=n; i++) cin >> a[i];
	//so lets first calculate this ex_max value 
	ex_max.resize(n + 1); indexes.resize(n + 1); m.resize(n + 1);
	for(int i = 1; i<= n; i++){
		indexes[a[i]].push_back(i);
	}
	//will use our formula to construct ex_max, again longest subsegment length that does not include x 
	for(int i = 1; i<=n; i++){
		vi inds_i = indexes[i];
		if(inds_i.size() == 0){
			ex_max[i] = n + 1;
			continue;
		}
		//corner cases
		ex_max[i] = max(ex_max[i], inds_i[0] - 1);
		ex_max[i] = max(ex_max[i], n - inds_i[inds_i.size() - 1]);
		for(int j = 0; j + 1<inds_i.size(); j++){
				ex_max[i] = max(inds_i[j + 1] - inds_i[j] - 1, ex_max[i]);
		   }
		}
		for(int& i: m) i = n + 1;
	    for(int i = 1; i <= n; i++){
			if(ex_max[i] == n + 1) continue;
			m[ex_max[i]] = min(m[ex_max[i]], i);
		}
		int min_ans = m[0];
		for(int k = 1; k<=n; k++){
		    if(min_ans == n + 1) cout << -1 << " ";
			else cout << min_ans << " ";
			min_ans = min(min_ans, m[k]);
		}
		cout << endl;
	}
 
int main() {
	int t; cin >> t; while(t--) solve();
}
