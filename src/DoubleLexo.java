import java.io.*;
import java.util.*;

public class DoubleLexo {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            char[] s = st.nextToken().toCharArray();
            ArrayList<Character> list = new ArrayList<>();
            for(char c: s) list.add(c);
            Collections.sort(list);
            for(int i = 0; i<s.length; i++){
                s[i] = list.get(i);
            }
            ArrayList<Character> prefix = new ArrayList<>();
            ArrayList<Character> suffix = new ArrayList<>();
            ArrayList<Character> middle = new ArrayList<>();
            int pref = 0;//end point of prefix
            int suff = s.length - 1; //end point of suffix
            for(int i = 0; i<s.length - 1; i++){
                char a = s[i];
                char b = s[i + 1];
                boolean out = false;
                //take two of the smallest letters
                if(a == b){
                    prefix.add(a);
                    suffix.add(b);//easy case
                }else{
                    //once there different fix the larger one in the prefix and the smaller one in the suffix
                    if(a > b){
                        prefix.add(a);
                        suffix.add(b);
                    }else{
                        prefix.add(b);
                        suffix.add(a);
                    }
                    out = true;
                }
                pref++;
                suff--;
            }
            for(int i = pref; i <= suff; i++){
                middle.add(s[i]);
            }
            Collections.sort(middle);
            if(middle.get(0) == middle.get(middle.size() - 1)){
                //edge case
                char temp = middle.get(middle.size()/2);
                middle.set(middle.size()/2, suffix.get(suffix.size() - 1));
                suffix.set(suffix.size() - 1, temp);
            }
            for (Character value : prefix) pw.print(value);

            for (Character character : middle) pw.print(character);

            for(int i = suffix.size() - 1; i>= 0; i--) {
                pw.print(suffix.get(i));
            }
           pw.println();
        }
        pw.close();
    }

}
