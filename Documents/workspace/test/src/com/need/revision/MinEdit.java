package com.need.revision;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int minDistance(String word1, String word2) {
    	int l1 = word1.length();
        int l2 = word2.length();
        int [][] d = new int [l1+1][l2+1];
        
        return helper(word1,word2,l1,l2,d);
        //return helperDP(word1,word2,l1,l2,d);
       
        
    }
    public int helper(String word1, String word2, int l1, int l2, int d[][]){
    	int n1 = word1.length();
        int n2 = word2.length();
        int res;
        if(l1 == 0)
        	res=l2;
        else if(l2 == 0)
        	res=l1;
        else if(d[l1][l2]!=0)
        	res = d[l1][l2];
        else if(word1.charAt(n1-l1) == word2.charAt(n2-l2))
        	res= helper(word1,word2,l1-1,l2-1,d);
        else
        	res= 1 + Math.min(Math.min(helper(word1,word2,l1-1,l2-1,d),helper(word1,word2,l1,l2-1,d)),helper(word1,word2,l1-1,l2,d));
        d[l1][l2]=res;
        return res;
    }
    public int helperDP(String word1, String word2, int l1, int l2, int d[][]){
  
        for(int i = 0 ;i<=l1;i++){
        	for(int j =0;j<=l2;j++){
        		if(i==0)
        			d[i][j]=j;
        		else if(j==0)
        			d[i][j]=i;
        		else if(word1.charAt(i-1)==word2.charAt(j-1))
        				d[i][j]=d[i-1][j-1];
        		else
        			d[i][j] = 1+Math.min(Math.min(d[i-1][j-1],d[i-1][j]),d[i][j-1]);
        			
           }
        }
        return d[l1][l2];
    }
}
public class MinEdit {
    public static String stringToString(String input) {
        assert input.length() >= 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < input.length() - 1; i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '\\') {
                char nextChar = input.charAt(i+1);
                switch (nextChar) {
                    case '\"': sb.append('\"'); break;
                    case '/' : sb.append('/'); break;
                    case '\\': sb.append('\\'); break;
                    case 'b' : sb.append('\b'); break;
                    case 'f' : sb.append('\f'); break;
                    case 'r' : sb.append('\r'); break;
                    case 'n' : sb.append('\n'); break;
                    case 't' : sb.append('\t'); break;
                    default: break;
                }
                i++;
            } else {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String word1 = stringToString(line);
            line = in.readLine();
            String word2 = stringToString(line);
            
            int ret = new Solution().minDistance(word1, word2);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}