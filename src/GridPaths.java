import java.io.*;
import java.util.*;

public class GridPaths{
    //https://cses.fi/problemset/task/1625
    static PrintWriter pw;
    static boolean [][] visited = new boolean [9][9];
    static int [] dirR = {-1,0,1,0};
    static int [] dirC = {0,1,0,-1};
    static int [] grid_path = new int [48];
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);

        String path = br.readLine();
        for(int i = 0; i<48; i++){
            if(path.charAt(i) == 'U') grid_path[i] = 0;
            else if(path.charAt(i) == 'R') grid_path[i] = 1;
            else if(path.charAt(i) == 'D') grid_path[i] = 2;
            else if(path.charAt(i) == 'L') grid_path[i] = 3;
            else grid_path[i] = -1; //for the q mark
        }
        //set borders so we can stay inside our grid space
        for(int i = 0; i<9; i++){
            visited[0][i] = true;
            visited[i][0] = true;
            visited[8][i] = true;
            visited[i][8] = true;
        }
        pw.println(search(0, 1,1));
        pw.close();

    }
    public static int search(int index, int curr_row, int curr_col){
        int num_paths = 0;
        if(visited[curr_row][curr_col-1] && visited[curr_row][curr_col+1] && (!visited[curr_row-1][curr_col] && !visited[curr_row+1][curr_col])) return 0;
        if(visited[curr_row-1][curr_col] && visited[curr_row+1][curr_col] && (!visited[curr_row][curr_col-1] && !visited[curr_row][curr_col+1])) return 0;

        if(curr_row == 7 && curr_col == 1){
            if(index == 48) return 1;
            else return 0;
        }
        if(index ==  48) return 0;

        visited[curr_row][curr_col] = true;
        if(grid_path[index] != -1){
            int next_row = curr_row + dirR[grid_path[index]];
            int next_col = curr_col + dirC[grid_path[index]];
            if(!visited[next_row][next_col]) num_paths += search(index+1, next_row, next_col);
        }
        else if((curr_col>2) && visited[curr_row][curr_col-2] && (visited[curr_row-1][curr_col-1] || visited[curr_row+1][curr_col-1]) && (!visited[curr_row][curr_col-1])){
            int next_row = curr_row;
            int next_col =  curr_col-1;
            num_paths += search(index+1, next_row, next_col);
        }
        else if((curr_col<6) && visited[curr_row][curr_col+2] && (visited[curr_row+1][curr_col+1] || visited[curr_row-1][curr_col+1]) && (!visited[curr_row][curr_col+1])){
            int next_row =  curr_row;
            int next_col =  curr_col+1;
            num_paths += search(index+1, next_row, next_col);
        }
        else if((curr_row>2) && visited[curr_row-2][curr_col] && (visited[curr_row-1][curr_col+1] && visited[curr_row-1][curr_col-1]) && (!visited[curr_row-1][curr_col])){
            int next_row =  curr_row-1;
            int next_col =  curr_col;
            num_paths += search(index+1, next_row, next_col);
        }
        else{
            for(int i = 0; i<4; i++){
                int next_row = curr_row + dirR[i];
                int next_col = curr_col+ dirC[i];
                if(!visited[next_row][next_col]) num_paths += search(index+1,next_row, next_col);
                else continue;
            }
        }
        visited[curr_row][curr_col] = false;
        return num_paths;
    }
}