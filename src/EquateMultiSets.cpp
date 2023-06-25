// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;

void solve(){
	int n; vi a; vi b; cin >> n;
	a.resize(n); b.resize(n); map<ll, ll> freq;
	for(int& i: a) cin >> i; for(int& i: b) cin >> i;
    for(int& i: a){
		while(i % 2 == 0){
			i = i/2;//remove the biggest power of 2
		}
       freq[i]++;//store the frequnecy of i in a 
	}
	bool good = true;
	//now lets go through b and try to make it a
	 for(int i = 0; i<n; i++){
		if(freq[b[i]] > 0){
			//this suggests that this element in b is in a
			//and thus since we must have this state anyways, we leave it
			freq[b[i]]--;
		}else{ 
			//this suggets that element is not in a, and the only way to make this element to use is to divide
			b[i] = b[i]/2;
			while(freq[b[i]] == 0 && b[i] != 0){
				b[i] = b[i]/2;
			}
			if(b[i] == 0){
				//if we reached the point of 0, then we know that this element must contradict some position in a
                good = false;
				break;
			}else{
				freq[b[i]]--;
			}
		}
	}
	cout << (good ? "YES":"NO") << endl;

}
int main() {
	int t; cin >> t; while(t--) solve();
}
/*
by observation we know that we can easily just remove the biggest power of  2,
then we know that we can only focus on the second operation.Why?
Say you need to multiply some element in array by 2^k to get some element in a,
then this implies some element in a  = b[i] * 2^k..... if we do multiplcations Essentially we can remove
2^k from all a's and just see for that element b is that element present in a.
So then basically solution is just go through each element in b, and ask yourself
is the element already in a...if it is, then obviously leave it there since we will need that element
in a anyways. If it is not in a, what should we do? Well, clearly we won't multiply anymore since we've already
taken care of that operaition (in the beg). Then, we will have to divide b, to make it useful...
keep dividing it until you reach a number in a...if we do, then obviously use it.
So i think the main point here is that if there is some element present in b and a already, then 
clearly leave it because we have to reach that state anyways.
Dividing is also logarithmic. 

*/
