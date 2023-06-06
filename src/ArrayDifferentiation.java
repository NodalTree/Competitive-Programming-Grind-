import java.io.*;
import java.util.*;
//https://codeforces.com/contest/1552/problem/D

public class ArrayDifferentiation {
	//proof incoming later...this is a very hard problem to prove why, but graphs was a smart smart thing to do to see the proof and to sort of understand in terms of graphs
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] a = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) a[i] = Integer.parseInt(st.nextToken());
			TreeSet<Integer> set = new TreeSet<>();
			set.add(0);
			for(int i = 0; i < (1 << N); i++) {
				int sum = 0;
				for(int j = 0; j< N; j++) {
					if ((i & (1 << j)) > 0){
						sum += a[j];
					}
				}
				set.add(sum);
			}
			//pw.println(set.size());
			pw.println(set.size() < (1 <<  N) ? "YES":"NO");
		}
		pw.close();
	}

}
