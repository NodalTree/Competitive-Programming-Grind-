import java.io.*;
import java.util.*;
import java.awt.Point;

public class ProbA {
    /*
        we will basically simulate the process, but very smartly...we won't care about the entire string when we merge two strings
        since caring about that would take too much time.
        Instead we only care about the number of hahas in a string
        when we merge two strings we can really just add the number of hahas in general
        however combining a + b might create new hahas that are in both a and b
        we can either take one char from a and 3 chars from b
        2 and 2 
        or 3 and 1
        ...this means we need to find the number of hahas in the last 3 of a and first three of b
        storing the string in the variable is not a problem it's just when we merge strings we don't want to actually simulate it
        as thats the expensive operation, so when we merge strings we can update everything but the stored strings
        if you think about it we only need to store for the first type  of operation 
        The idea is we are skipping the the epensive operation by only caring about the number of hahas 
        TO BE DEBUGGED LATER spending too much time
        so Var{
            first, last, str
            long hahas 
            public Var(String first, String last, String str){
                  this.first = first;
                  this.last = last;
                  this.str = str;
                  cut(first); cut(last);
                  this.hahas = count_hahas(str); 
            }
            
            cut(String s){
                if(s.size() > 3) s = s.substring(0, 3);
            }
        }
        combine Var(a, b){
        //we don't care about updating the string, we only care about updating the number of hahas
        String first = a.first;
        String last = b.first;
        String hahas = a.hahas + b.hahas + count_hahas(first + last);
        again noticed how everything is updated except for string this is the trick 
        //think we only need to worry about updating string if we assign with a new variable, but it's going to be less than 5
        //thus counting hahas in that string wson't be difficult
         //but afterwards if we keep adding to the string, then i dont care about what the string is i just care how many hahas it has 
          
        return var;
        }
        
     */
    public static void main(String[] args) throws IOException {
        //FastScanner fs = new FastScanner();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
              int N = Integer.parseInt(br.readLine());
              HashMap<String , Variable> var = new HashMap<>();
              for(int i = 0; i<N; i++) {
            	  String statement = br.readLine();
            	  String symbol = statement.substring(0, 1);
            	  //pw.println(statement);
            	  if(statement.contains(":=")) {
            		  String str = statement.substring(5);
            		  /*pw.println("This is the symbol " + symbol);
            		  pw.println("This is after assignment " + str);*/
            		  Variable new_var = new Variable(str, str,str, 0);
            		  var.put(symbol, new_var );
            		  pw.println(new_var.str());
            	  }else {
            		  Variable new_var = combine(var.get(statement.substring(4,5 )), var.get(statement.substring(8, 9)));
            		  var.put(symbol, new_var);
            		  pw.println("combined "+ symbol + " : "+ new_var.str());
            	  }
            	  if( i == N - 1) {
            		  pw.println(var.get(symbol).hahas);
            	  }
              }
        }
        pw.close();
    }
    public static long count_haha(String S) {
    	long out = 0;
    	for(int i = 0; i + 3 < S.length(); i++) {
            if(S.substring(i, i + 4).equals("haha")) out++;	
    	}
    	return out;
    }
    static class Variable{
    	public String first, last, str;
    	public long hahas;
    	public Variable(String first, String last, String str, long hahas) {
    		this.first = first;
    		this.last = last;
    		this.str = str;
    		cutFirst(first); cutLast(last);
    		this.hahas = hahas;
    		hahas = count_haha(str);
    	}
    	public void cutFirst(String s) {
    		if(s.length() > 3) s = s.substring(0, 3);
    	}
    	public void cutLast(String s) {
    		if(s.length() > 3) s = s.substring(s.length() - 3);
    	}
    	public String str() {
           return first + " " + last + " " + str + " "+ hahas;
    	}

    }
    public static String  cutFirst(String s) {
		if(s.length() > 3) s = s.substring(0, 3);
		return s;
	}
	public static String cutLast(String s) {
		if(s.length() > 3) s = s.substring(s.length() - 3);
		return s;
	}
    static Variable combine(Variable a, Variable b) {
    	//String first = cutFirst(a.first + b.last);
    	//String last = cutLast(a.first + b.last);
    	long hahas = a.hahas + b.hahas + count_haha(a.first + b.last);
    	return new Variable(a.first, b.last, "", hahas);
    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

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

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

}
