#include<bits/stdc++.h>
//no need to waste time implementing problem, spent some thime thinking about how to motivate sol anyways
//so just gonna put in sol
using namespace std;
typedef long long ll;
const ll NMAX=1e5+5,MOD=1e9+7;
ll v[NMAX],pos[NMAX];
void tc(){
    ll n,l,r,ans=1;
    cin>>n;
    for(ll i=0;i<n;i++){
        cin>>v[i];
        pos[v[i]]=i;
    }
    l=r=pos[0];
    for(ll i=1;i<n;i++){
        if(pos[i]<l) l=pos[i];
        else if(pos[i]>r) r=pos[i];
        else ans=ans*(r-l+1-i)%MOD;
    }
    cout<<ans<<'\n';
}
int main()
{
    ios_base::sync_with_stdio(false); cin.tie(0);
    ll t;
    cin>>t;
    while(t--)
        tc();
    return 0;
}
