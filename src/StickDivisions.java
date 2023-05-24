import java.io.*;
import java.util.*;

public class StickDivisions {
  static int X, N;
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    X = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    Queue <Integer> pq = new PriorityQueue <>();
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i<N; i++)
       pq.add(Integer.valueOf(st.nextToken()));

    long ans = 0;
    while(pq.size() > 1){
      int stick1 = pq.remove();
      int stick2 = pq.remove();
      pq.add(stick1 + stick2);
      ans += (stick1 + stick2);
    }
    pw.println(ans);
    pw.close();
    
  }
}
