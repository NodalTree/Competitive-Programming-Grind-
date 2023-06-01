import java.io.*;
import java.util.*;


/*
 Lets think which coffee should we take first?. Well, intuitively just the lowest min. Lets sort the minutues
 so on Day 1: we take a1... then look for a coffee with a1 + d. So if we are on minute a_i, then
 we can just do set.ceiling(a1 + 1 + d), and then take that coffee iff it's within jf. If it is then that becomes
 the current coffee we are drinking else we jsut start a new day.
 */

public class CoffBreak {
    public static class Pair implements Comparable<Pair> {
        public int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair other) {
            return other.first - this.first;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ind = new int[N];//takes in a coffee index and spits out what day it should be drunk
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int minute = Integer.parseInt(st.nextToken());
            set.add(minute);
            map.put(minute, i);
        }
        int current_day = 1;
        int current_minute = set.first();
        ind[map.get(current_minute)] = current_day;
        set.remove(current_minute);
        while (!set.isEmpty()) {
            if (set.ceiling(current_minute + 1 + D) == null) {
                current_day++;
                current_minute = set.first();
            } else {
                current_minute = set.ceiling(current_minute + 1 + D);
            }
            ind[map.get(current_minute)] = current_day;
            set.remove(current_minute);
        }
        pw.println(current_day);
        for (int i = 0; i < N; i++) {
            pw.print(ind[i] + " ");
        }
        pw.close();
    }
}

