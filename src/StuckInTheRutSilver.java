import java.io.*;
import java.util.*;

public class StuckInTheRutSilver {
    static class Cow{
        public String dir;
        public int x, y, id;
        public boolean isFinal;
        public Cow(String dir, int x, int y,int id, boolean isFinal){
            this.dir = dir;
            this.x = x;
            this.y = y;
            this.id = id;
            this.isFinal = isFinal;
        }//33
    }
    static class Collision implements Comparable<Collision>{
        public int time;
        public Cow stopped, ongoing;
        public Collision(int time , Cow stopped, Cow ongoing){
            this.time = time;
            this.stopped = stopped;
            this.ongoing = ongoing;
        }
        public int compareTo(Collision other){
            return this.time - other.time;
        }
        public String toString(){
            return time + " "+ stopped.id + " " + ongoing.id;
        }
    }
    static int count = 0;
    static boolean[] vis;
    static int N;
    static ArrayList<Integer>[]  adj;
    static int[] ans;
    static Cow[] cows;
    static ArrayList <Collision> meets = new ArrayList<>();
    static boolean [] is_stopped;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ans = new int[N];
        adj = new ArrayList[N];
        cows = new Cow[N];
        is_stopped = new boolean[N];
        vis = new boolean[N];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String dir = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(dir, x, y, i, false);
        }

        for(int i = 0;i<N; i++){
            for(int j = i + 1; j<N; j++){
                Cow nC = cows[i].dir.equals("N") ? cows[i] : cows[j];
                Cow eC = cows[i].dir.equals("E") ? cows[i] : cows[j];
                if(nC.dir.equals(eC.dir)) continue;
                if(nC.x < eC.x || nC.y > eC.y) continue;
                int meetX = nC.x;
                int meetY = eC.y;
                int timeForN = Math.abs(nC.y - meetY);
                int timeForE = Math.abs(eC.x - meetX);
                if(timeForN == timeForE) continue;
                if(timeForN > timeForE){
                    meets.add(new Collision(timeForN, nC, eC));
                    cows[i] = new Cow("N", meetX, meetY, nC.id, false);
                }else{
                    meets.add(new Collision(timeForE, eC, nC));
                    cows[j] = new Cow("E", meetX, meetY, eC.id, false);
                }
            }
        }
        Collections.sort(meets);
        for(int i = 0; i < meets.size(); i++){
            Collision c = meets.get(i);
            Cow stopped = c.stopped;
            Cow ongoing = c.ongoing;
            if(stopped.isFinal && ongoing.isFinal){
                meets.remove(i);
                i--;
            }
            if(stopped.isFinal || ongoing.isFinal){
                if(stopped.x == ongoing.x || stopped.y == ongoing.y){

                }
            }
        }
        for(int i = 0; i<N; i++) adj[i] = new ArrayList <>();
        for(Collision c: meets){
            Cow stopped = c.stopped;
            Cow ongoing = c.ongoing;
            if(is_stopped[stopped.id] && is_stopped[ongoing.id]) continue;
            adj[ongoing.id].add(stopped.id);
            is_stopped[stopped.id] = true;
        }
        for(int i = 0; i<N; i++){
            dfs(i);
            ans[i] = count - 1;
            count = 0;
            Arrays.fill(vis, false);
        }
        for(int i = 0; i<N; i++) pw.println(ans[i]);
        // pw.println(Arrays.toString(adj));
        pw.close();

    }
    public static void dfs(int node){
        count++;
        vis[node] = true;
        for(int v: adj[node]){
            if(!vis[v]){
                dfs(v);
            }
        }
    }
}