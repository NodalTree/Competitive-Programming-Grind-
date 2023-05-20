import java.io.*;
import java.util.*;


public class AntiPalindromes {
    //https://codeforces.com/problemset/problem/1822/E
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            char[] s = st.nextToken().toCharArray();
            //by given test cases: if n is odd -> -1, max frequency of any character must be less than N/2
            int max = Integer.MIN_VALUE;
            int[] freq = new int[26];
            for(int i = 0; i<N; i++){
                freq[s[i] - 'a']++;
                max = Math.max(max, freq[s[i] - 'a']);
            }
            if(N % 2 == 1 || max > N/2){
                pw.println(-1);
                continue;
            }
            ArrayList<Character> bad = new ArrayList<>();//this will add characters in the first half of the string such that s[i] == s[n - i - 1] (0 indexed)
            int unique = 0;
            boolean[] seen = new boolean[26];
            for(int i = 0; i * 2 < N; i++){
                if(s[i] == s[N - i - 1]){
                    bad.add(s[i]);
                }
                if(!seen[s[i] - 'a']){
                    unique++;
                    seen[s[i] - 'a'] = true;
                }
            }
            Collections.sort(bad);//why are we sorting the bad characters...well it's always opitmal to swap two distinct bad characters (swapping the same won't change anything)
            //thus by sorting the list we can use two pointers and swap front and end(it's analogus to saying given a set of numbers...give me two distinct numbers, well you would pick the smaller and biggest numbers (the numbers that are furthest away) to give you the best shot at that distinction)
            // for(Character c: bad) pw.print(c + " ");
            // pw.println();
            int l = 0;
            int r = bad.size() - 1;
            int ans = 0;
            while(l <= r){
                if(bad.get(l) != bad.get(r)){
                    ans++;
                    l++;
                    r--;
                }else{
                    //if this condition is not true...then we need to satisfy (r - l + 1) more bad characters
                    //how do we do so?
                    //well it's conditional: we know that the frequency of a bad character in bad has to be no more than N/4. This is because more than N/4 would imply more than N/2 which is impossible (you can't have a letter appearing more than half since there has to be a distinct pairing letter for each letter in the string). This means we need to go up and now actually add this before: check if there is any character that has a frequency of over n/2...if this is not the case...we are guaranteed to satisfing these (r - l + 1) bad characters because we have at least N/4 distinct pairing letters for them in the OTHER SIDE of the string (remember bad is taken from the first half). You can get this tricky intuition from one of the given test cases actually
                    if(unique > 2)
                        ans += (r - l + 1)/2;
                    else ans += (r - l + 1)/2;
                    break;
                }
            }
            pw.println(ans);
        }
        pw.close();
    }
}