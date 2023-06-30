// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
void solve(){
	int f_x, f_y, a_x, a_y, b_x, b_y; cin >> f_x >> f_y >> a_x >> a_y >> b_x >> b_y;
	int ans = 0;
	int delta_ax = a_x - f_x;
	int delta_bx = b_x - f_x;
	int delta_ay = a_y - f_y;
	int delta_by = b_y - f_y;
	if((delta_ax > 0 && delta_bx > 0) || (delta_ax < 0 && delta_bx < 0)){
		ans += min(abs(delta_ax), abs(delta_bx));
	}
	if((delta_ay > 0 && delta_by > 0) || (delta_ay < 0 && delta_by < 0)){
		ans += min(abs(delta_ay), abs(delta_by));
	}
	cout << ans + 1 << endl;
}
int main() {
	int t; cin >> t; while(t--) solve();
}
