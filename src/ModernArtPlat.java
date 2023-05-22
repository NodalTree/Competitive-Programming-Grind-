import java.io.*;
import java.util.*;

public class ModernArtPlat {
//prefix sums, so still silver level on usaco guide as hard
	static int N;
	static int ans = 0;
	static int [][] A, dp;
	static int [] up, left, down, right;
	static boolean [] seen;
	static boolean [] bad;
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("art.in"));
		PrintWriter pw = new PrintWriter("art.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int [N][N];
		dp = new int [N + 1][N + 1];
		up = new int [N*N + 1];
		Arrays.fill(up, N);
		down = new int [N*N + 1];
		left = new int [N*N + 1];
		Arrays.fill(left, N);
		right = new int [N*N + 1];
		seen = new boolean [N*N + 1];
		bad = new boolean [N*N + 1];
		for(int i = 0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				up[A[i][j]] = Math.min(i, up[A[i][j]]);
				down[A[i][j]] = Math.max(i, down[A[i][j]]);
				left[A[i][j]] = Math.min(j, left[A[i][j]]);
				right[A[i][j]] = Math.max(j, right[A[i][j]]);
			}
		}
		br.close();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(!seen[A[i][j]] && A[i][j] != 0) {
					dp[up[A[i][j]]][left[A[i][j]]]++;
					dp[up[A[i][j]]][right[A[i][j]] + 1]--;
					dp[down[A[i][j]] + 1][left[A[i][j]]]--;
					dp[down[A[i][j]] + 1][right[A[i][j]] + 1]++;
					seen[A[i][j]] = true;
				}
			}
		}
		for(int i = 1; i<=N; i++) {
			dp[0][i] +=  dp[0][i - 1];
			dp[i][0] += dp[i - 1][0];
		}
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				dp[i][j] += dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
			}
		}
	   for(int i = 0; i<N; i++) {
		   for(int j = 0; j<N; j++) {
			   if(dp[i][j]  > 1) {
				   bad[A[i][j]] = true;
			   }
		   }
	   }
	   for(int i = 1; i<=N*N; i++) {
		   if(!bad[i]) ans++;
	   }
	   pw.println(ans);
	   pw.close();
	
	}

}
