import java.io.*;
import java.util.*;

public class OutOfSorts {
  //http://www.usaco.org/index.php?page=viewproblem2&cpid=834
  static int N;
  static int [] arr;
  static int [] sorted;
  static HashMap<Integer, Integer> map =  new HashMap<>();
  static int ans  = 0;
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    arr = new int [N];
    sorted = new int [N];
    for(int i  = 0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      arr[i] = Integer.parseInt(st.nextToken());
      sorted[i] =  arr[i];
    }
    Arrays.sort(sorted);
    for(int i = 0; i<N; i++){
      map.put(sorted[i], i);
    }
    for(int i = 0; i<N; i++){
       ans  = Math.max(ans, i - map.get(arr[i]));
    }
    pw.println(ans + 1);
    pw.close();
  }
}
