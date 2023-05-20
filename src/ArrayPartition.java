import java.io.*;
import java.util.*;

public class ArrayPartition {
    //https://codeforces.com/problemset/problem/1454/F
    static int MAX = 500;

    // lookup[i][j] is going to store index of
    // minimum value in arr[i..j]
    static int[][] lookup = new int[MAX][MAX];

    // Structure to represent a query range
    static class Query {
        int L, R;

        public Query(int L, int R)
        {
            this.L = L;
            this.R = R;
        }
    }

    // Fills lookup array lookup[n][n] for
    // all possible values of query ranges
    public static void preprocess(int arr[], int n)
    {
        // Initialize lookup[][] for
        // the intervals with length 1
        for (int i = 0; i < n; i++)
            lookup[i][i] = i;

        // Fill rest of the entries in bottom up manner
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++)

                // To find minimum of [0,4],
                // we compare minimum of
                // arr[lookup[0][3]] with arr[4].
                if (arr[lookup[i][j - 1]] < arr[j])
                    lookup[i][j] = lookup[i][j - 1];
                else
                    lookup[i][j] = j;
        }
    }

    // Prints minimum of given m query
    // ranges in arr[0..n-1]
    public static int RMQ(int arr[], int n, Query q)
    {
        preprocess(arr, n);
        int L = q.L, R = q.R;
        return arr[lookup[L][R]];

    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            int[] p_max = new int[N]; p_max[0] = a[0];
            int[] s_max = new int[N]; s_max[0] = a[N - 1];
            //have not learned rmq, but read solution and it just gives minimum in a certain range (might as well just use it since it's a common algorithm will learn it later, so copied template)
            for(int i = 1; i<N; i++){
                p_max[i]  = Math.max(p_max[i - 1], a[i]);
            }
            for(int i = N - 2; i >= 0; i--){
                s_max[i] = Math.max(s_max[i + 1], a[i]);
            }
            //lets fix the start point of the suffix
            for(int i = N - 1; i >= 2; i--){
                int suff_max = s_max[i];
                int l = 0;
                int r = i - 1;
                int start = -1;
                int end = -1;
                while(l < r){
                    int mid = l + (r - l + 1)/2;
                    if(p_max[mid] == suff_max){
                        r = mid;
                    }else if(p_max[mid] > suff_max){
                        r = mid - 1;
                    }else{
                        l = mid + 1;
                    }
                }
                start = l;
                l = 0;
                r = i - 1;
                while(l < r){
                    int mid = l + (r - l + 1)/2;
                    if(p_max[mid] == suff_max){
                        l = mid;
                    }else if(p_max[mid] > suff_max){
                        r = mid - 1;
                    }else{
                        l = mid + 1;
                    }
                }
                end = l;


            }

        }
    }
}
