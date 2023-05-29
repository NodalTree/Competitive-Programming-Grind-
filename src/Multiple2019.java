import java.io.*;
import java.util.*;

public class Multiple2019 {
  //https://atcoder.jp/contests/abc164/tasks/abc164_d
  static String S;
  static int [] digits;
  static int [] pref;
  static int [] freq;
  static int ans = 0;
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    //StringTokenizer st = new StringTokenizer(br.readLine());
    S = br.readLine(); 
    digits = new int [S.length() + 1];
    for(int i = 1; i<=S.length(); i++){
      digits[i] = Integer.parseInt(S.substring(i - 1, i));
    }
    pref = new int [S.length() + 2];
    freq = new int [2019];
    int base_10 = 1;
    for(int i = S.length(); i>0; i--){
      pref[i] = (pref[i + 1] + base_10 * digits[i])  % 2019;
      base_10 = (base_10 * 10) % 2019;
    }
    for(int i = 0; i<= S.length(); i++){
      freq[pref[i]]++;
    }
    for(int i = 0; i<2019; i++){
      ans += freq[i] * (freq[i] - 1)/2;
    }
    pw.println(ans);
    pw.close();
  }
}
