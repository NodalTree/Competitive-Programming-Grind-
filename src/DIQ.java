import java.io.*;
import java.util.*;


public class DIQ {

    /* we know that he can take up to q hard contests (contests in which is iq is less than the difficulty of the contest).
     It's just a matter of which hard contests he takes. Does this even matter?
     Yes, it does because taking a hard contest could make more harder contests later on in the future.
     We can get rid of this annoying issue if we think in opposite order.
     Imagine, we taking the first hard contest from the back.
     Lets assume that at the end of the entire process the iq goes to 0?
     Why can we do this? Well assume it went to something like 2 instead (if there are still hard contests you can take like 2 more then why don't you just take those 2 and go to 0). This will become clearer as the solution is fleshed out. Lets just assume you start from 0 tho. With this if I want to take the last contest my iq has to be at least 1 so i increment it. However, I go to make sure that this iq is less than the actual iq in order to increment it. If the iq is more than the difficulty of the contest going backwards, then you don't have to do anything and you can just take the contest. Now lets prove that it's always safe to start at 0...everything else seems straightforward from here.  Think about like this even if we go forward and we end up with a nonzero IQ we can just assume this IQ is 0 anyways because it's not like the size of the IQ matters after we are already done going through each contest. For instance, if I jsut finished with an iq of 2. Well the 2 iq after being done doesn't mean anything becuase it's not like I'm going to take any more contests, so just let it be 0...it won't have any effect. Seems a bit unrigourous but lets just use the intuition of the matter here ig.
     */
    //https://codeforces.com/problemset/problem/1708/C
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] a = new int[N];
            for(int i = 0; i<N; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            int end_iq = 0;
            for(int i = N - 1; i>= 0; i--){
                //if the end_iq which we will start at 0 if thats greater than just take the contest...else increment this iq to make it enuff however, make sure it doesn't go over Q. In this way we can make sure we take those  q hard contests at the back. If we started anywhere from not 0 the second else if condition would become false quicker.
                if(end_iq >= a[i]) a[i] = 1;
                else if(end_iq < Q) {end_iq++; a[i] = 1;}
                else a[i] = 0;
            }
            for(int i = 0; i<N; i++) pw.print(a[i]);
            pw.println();

        }
        pw.close();
    }
}
