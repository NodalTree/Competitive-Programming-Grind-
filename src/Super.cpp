// Source: https://usaco.guide/general/io

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

void solve(){
	int n; cin >> n; 
	if(n == 1){
		cout << 1;
	}else if(n % 2 == 1){
		cout << -1;
	}else{
		 cout << n << " " << (n - 1) <<  " ";
		for(int i = 2; i<n; i += 2){
		 cout << i << " " << (n - 1 -  i) << " ";
		}
	}
	cout << endl;
}

int main() {
	int t; cin >> t; while(t--) solve();
}
/*
thought sapce:
Blog Response:
I understand there isn't a rigorous way to solve this problem as it is constructive,
 but I still believe that the problem was spoiled by the last sample test case,
making it quite easy. One can easily arrive at the fact that odd n > 1 don't work
    and that n must always be in front. The last sample is 6 5 2 3 4 1. We can actually
    generalize a whole algorithm from here in a rigorous way by basic observations: Array: 6 5 2 3 4 1 
	Residues:0, 5, 1, 4, 2 One can notice that after every two residues the numbers decrease.
	 Why? Well in the array we added blocks of n — 1. So n — 1, 2n — 2, 3n — 3. 
	 Clearly, the residues decrease if reduce this modulo n. So the last sample suggests we should center some construction around decreasing residues every other time. Now, interestingly enough the residues also increase starting from 1. Goes from 1 to 2. The sample again suggests we should do something similar to this. More clearly, the sample gives us the intuition that maybe there is some construction in which we start from n — 1 decrease the residues until we reach the halfway point and start from 1 increase residues in till we reach the halfway point. From, there you can easily prove that putting even numbers and n — 1 — even numbers always work. Say you want residue 1 and you have n — 1 before it, obv you need 2, ...no we know that for the next thing, we will have residue n — 2 (since we chose to decrease like this), well then obviously we need 4 to get this residue to increase from 1 to 2. So in general when we want residue i we know we have residue n — i from before it so we will need 2i. 2i never gets too big where it won't already be reduced mod n. I think this is extremely intuitive, and something you don't need to prove.

In conclusion, this question boils down to simple problem-solving by generalizing patterns by sample test cases. It would definitely be much harder without them, but since they are there I think the question is fairly straightforward.

easy by observing patterns in last test case 
6 5 2 3 4 1
0 5 1 4 2 3
n, n - 1,2, (n - 1 - 2), 4, (n - 1 - 4),  

n - 2 +  b = 2 (mod n )
n + b = 4(mod n)
 1 = n - 1 + a ( mod n) 
2 =  + a (mod n)
residues: 0, n - 1, 1, n - 2,  smth , n - 3
n - 2

*/
