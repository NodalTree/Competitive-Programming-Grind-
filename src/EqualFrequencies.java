import java.io.*;
import java.util.*;

public class EqualFrequencies {
    /*
      Lets fix the unknown information: lets say the equal frequency is x. Then N (mod x) = 0. This implies
      for the common frequency we only have to try the divisors of N which is <=  2* sqrt(N). For each fixed
      divisor, we can try answering the question. Lets say string T is currently just a bunch of question marks.
      Now, if we want to resemble string S as much as possible, then  we to maximize the amount of matches in the string.
      Intuitively, we should have a frequency array of each character in the string. NOw by simple observation,
      if the frequency of a character in String S is over the fixed frequency, then we MUST modify the excess. This means
      we should sort the frequency from greatest to least and deal with the ones that have the most...if it's under the
      frequency just leave it as it is...for now.
      Now, we know we are dealing with a bunch of question marks and the highest occuring letter in the string S, call it c.
      Well then for c, just let min(freq[c],frequency) stay in the string...the rest will be question marks.
      This always is optimal think about it: for the ones that are over frequency we just let there be the fixed
      frequency of each one of them and leave the rest for question marks. Now for the ones that are under
      the frequency we can simply just fill however we need of that letter. Done thats solution.

      Example to make more clear sense:
      codeforces
      c - 2
      e - 2
      o - 2
      d - 1
      f - 1
      r - 1
      d - 1
      String T = ??????????
      Frequency: 1, unique letters: 10
      Deal with highest c, just let one c stay
      c?????????
      deal with letter e
      just let one e stay
      c??e???????
      just let one o stay
      co??e??????
      for the rest of the other letters simply just fill it out
     */
    public static class Pair implements Comparable<Pair>{
        public int freq;
        public char letter;
        public Pair(int freq, char letter){
            this.freq = freq;
            this.letter = letter;
        }
        public int compareTo(Pair other){
            //always deal with a letter that has highest frequnecy first
            return other.freq - this.freq;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            char[] s = st.nextToken().toCharArray();
            int[] freq = new int[26];
            char[] t = new char[N];
            ArrayList<Integer>[] indicies = new ArrayList[26];
            for(int i = 0; i<26; i++) indicies[i] = new ArrayList<>();
            for(int i = 0; i<N; i++){
                indicies[s[i] - 'a'].add(i);
            }
            char[] ans = new char[N];
            int best_changes = Integer.MAX_VALUE;
            ArrayList<Pair> letters = new ArrayList<>();
            for(int i = 0; i<N; i++){
                freq[s[i] - 'a']++;
            }
            for(int i = 0; i<26; i++){
                letters.add(new Pair(freq[i],(char)(97 + i)));
            }
            Collections.sort(letters);//sort by freqeunecy
            /*for(Pair p: letters){
                pw.println("letter "+ p.letter + " frequency " + p.freq);
            }*/
            for(int frequency = 1; frequency<=N; frequency++){
                if(N % frequency != 0 || N/frequency > 26) continue;//skip ones that don't work
                Arrays.fill(t, '?');//initially we don't know what the string t is
                int unique = N/frequency;
                for(int i = 0; i<unique; i++){
                    Pair letter = letters.get(i);
                    char c = letter.letter;
                    int freq_c = letter.freq;
                    int leave = Math.min(frequency, freq_c);//leave this many letters in t
                    //pw.println("leave this much "+ leave + " for " + c);
                    ArrayList<Integer> ind = indicies[c - 'a'];
                    for(int j = 0; j<leave; j++){
                        t[ind.get(j)] = c;
                    }
                }
                //pw.println(Arrays.toString(t));
                //we've left the most we could in the string, now the number of changes it he number of question marks in t
                int changes = 0;
                for(int i = 0; i<N; i++){
                    if(t[i] == '?') changes++;
                }
                //pw.println("Number of changes "+ changes);
                for(int i = 0; i<unique; i++){
                    Pair letter = letters.get(i);
                    char c = letter.letter;
                    int freq_c = letter.freq;
                    int q_marks = frequency - Math.min(frequency, freq_c) ;//amount o q makrs we got to fill with that letter
                    int count = 0;
                    //if(i == unique - 1) pw.println("q marks "+ q_marks);
                    for(int j = 0; j<N; j++){
                        if(count == q_marks) break;//we are done get out
                        if(t[j] == '?') {t[j] = c; count++;}

                    }
                }
               // pw.println("Changes "+ changes);
                //pw.println(Arrays.toString(t));
                //string is now constructed
                if(changes < best_changes){
                    best_changes = changes;
                    for(int i = 0; i<N; i++){
                        ans[i] = t[i];
                    }
                }
            }
            pw.println(best_changes);
            for(char c: ans) pw.print(c);
            pw.println();
        }
        pw.close();
    }

    static final int mod = 1_000_000_007;
    static final Random random = new Random();

    static void ruffleSort(int[] a) {
        int n = a.length;// shuffle, then sort
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    static long add(long a, long b) {
        return (a + b) % mod;
    }

    static long sub(long a, long b) {
        return ((a - b) % mod + mod) % mod;
    }

    static long mul(long a, long b) {
        return (a * b) % mod;
    }

    static long div(long a, long b) {
        return mul(a, exp(b, mod - 2));
    }

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

}
