//Fikky D's Sol
import java.io.*;
import java.util.*;
public class SplittingTheFieldGold {
	static class Coord implements Comparable <Coord>{
		public int x, y;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Coord other) {
			return Integer.compare(this.x, other.x);
		}
		public void swap() {
			int t = x;
		    x = y;
			y = t;
		}
	}
    static int N;
    static Coord[] cows;
    static int[] suffMinY, suffMaxY;
    static long curr_area = 0;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("split.in"));
		PrintWriter pw = new PrintWriter("split.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		cows = new Coord[N];
		long leftB = Integer.MAX_VALUE;
		long botB =  Integer.MAX_VALUE;
		long rightB = Integer.MIN_VALUE;
		long topB = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cows[i] = new Coord(x, y);
			leftB = Math.min(x, leftB);
			botB = Math.min(y, botB);
			rightB = Math.max(x, rightB);
			topB = Math.max(y, topB);
		}
		curr_area = (rightB - leftB) * (topB - botB);
		Arrays.sort(cows);
		suffMinY = getMin(cows);
		suffMaxY = getMax(cows);
		long ans1 = solve();
        //apply a swap of (x,y) as a quirk to make impl shorter
		for(Coord c: cows) {
			c.swap();
		}
		Arrays.sort(cows);
		suffMinY = getMin(cows);
		suffMaxY = getMax(cows);
		long ans2 = solve();
		long out = curr_area - Math.min(ans1, ans2);
		pw.println(out);
		pw.close();
		
        
	}
	public static int[] getMin(Coord[] suff) {
        /*This gets the min y values from starting from the back to the front. More formally, at min[i] returns the minimum y value from cow_n, cow_(n-1),..., cow_i*/
		int[] min = new int [suff.length];
		int running_min = Integer.MAX_VALUE;
		for(int i = suff.length - 1; i>-1; i--) {
			if(i == suff.length - 1) {
			   min[i] = suff[i].y;
				running_min = min[i];
			}else {
			    running_min = Math.min(running_min, suff[i].y);
				min[i] = running_min;
			}
		}
		return min;
	}
	public static int[] getMax(Coord[] suff) {
        /*does the same exact thing but takes max this times*/
		int[] max = new int [suff.length];
		int running_max = Integer.MIN_VALUE;
		for(int i = suff.length - 1; i>-1; i--) {
			if(i == suff.length - 1) {
				max[i] = suff[i].y;
			    running_max = max[i];
			}else { 
		        running_max = Math.max(running_max, suff[i].y);
			     max[i] = running_max;
			}
		}
		return max;
	}
	public static long solve() {
        /*we split the grid at i and keep a running min*/
		long save = Long.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(int i = 0; i< N - 1; i++) {
			minY = Math.min(minY, cows[i].y);
			maxY = Math.max(maxY, cows[i].y);
			long width1 = cows[i].x - cows[0].x;
			long length1 = maxY - minY;
			long width2  = cows[N - 1].x - cows[i + 1].x;
			long length2 = suffMaxY[i + 1] -  suffMinY[i + 1];
			long area =  width1*length1 + width2*length2;
			save = Math.min(save,  area);
		}
		return save;
	}

}
