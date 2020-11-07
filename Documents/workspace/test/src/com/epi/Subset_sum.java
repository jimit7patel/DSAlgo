package com.epi;
import java.util.ArrayList;
import java.util.Arrays;

class Subset_sum
{	
	static int count;
	static int[] cache0;
	static int[] cache1;

    static  int countStrings(int n, String s)
    {   
    	print(n,s,' ');
        return count;
    }
    static  int countStrings(int n)
    {
        int a[] = new int [n];
        int b[] = new int [n];
        a[0] = b[0] = 1;
        for (int i = 1; i < n; i++)
        {
            a[i] = a[i-1] + b[i-1];
            b[i] = a[i-1];
        }
        
        return a[n-1] + b[n-1];
    }
    static void print(int n, String s, char c) {
    	if(n==0) {
    		count++;
    		return;
    	}
    	
    	
    	if(c == '1') {
    		
    		print(n-1,s+"0",'0');
    	}
    	else {
    		print(n-1,s+"1",'1');
    		print(n-1,s+"0",'0');
    	}
    }
    
    /* This is using DP */
    static int printAlternate(int n, String s, char c) {
    	if(n==0) {
    		return 1;
    	}
    	
    	if(c == '1') {
    		cache1[n] = (cache1[n]==0?printAlternate(n-1,s+"0",'0'):cache1[n]);
    		return cache1[n];
    	}
    	else {
    		if(cache0[n]!=0) return cache0[n];
    		
    		cache1[n-1] = (cache1[n-1]==0?printAlternate(n-1,s+"1",'1'):cache1[n-1]);
    		cache0[n-1]=(cache0[n-1]==0?printAlternate(n-1,s+"0",'0'):cache0[n-1]);
    		return cache1[n-1]+cache0[n-1];
    	}
    }
  
    /* Driver program to test above function */
    public static void main (String args[])
    {
    	  int n =400;
    	  cache0 = new int[n+1];
    	  cache1 = new int[n+1];
    	  Arrays.fill(cache0, 0);
    	  Arrays.fill(cache1, 0);
    	  System.out.println(countStrings(n));
    	  System.out.println(printAlternate(n,"",' '));
    	  //System.out.println(countStrings(n,""));
      
    }
}/* This code is contributed by Rajat Mishra */