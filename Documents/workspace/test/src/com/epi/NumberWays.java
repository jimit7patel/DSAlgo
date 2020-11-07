package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NumberWays {
  // @include
  public static int numberOfWays(int n, int m) {
    return computeNumberOfWaysToXY(n - 1, m - 1);
  }

  private static int computeNumberOfWaysToXY(int x, int y
                                             ) {
	  if(x==0 || y ==0)
		  return 1;
	  
	  return computeNumberOfWaysToXY(x-1,y) + computeNumberOfWaysToXY(x,y-1);
    
  }
  // @exclude

  private static int computeNumberOfWaysSpaceEfficient(int n, int m) {
	  
	int table[][]=new int[n][m];
	Arrays.fill(table[0],1);
	for(int i=0;i<n;i++){
		table[i][0]=1;
	}
	for(int i=1;i<n;i++){
		int prev=1;
		for(int j=1;j<m;j++){
			
			table[i][j]=table[i][j-1]+table[i-1][j];
			//prev=table[i][j];
		}
	}
    return table[n-1][m-1];
  }

  private static int checkAns(int n, int k) {
    int table[][] = new int[n + 1][k + 1];
    // Basic case: C(i, 0) = 1.
    for (int i = 0; i <= n; ++i) {
      table[i][0] = 1;
    }
    // Basic case: C(i, i) = 1.
    for (int i = 1; i <= k; ++i) {
      table[i][i] = 1;
    }
    // C(i, j) = C(i - 1, j) + C(i - 1, j - 1).
    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j < i && j <= k; ++j) {
        table[i][j] = table[i - 1][j] + table[i - 1][j - 1];
      }
    }
    return table[n][k];
  }

  private static void check(int n, int m) {
    int expected = checkAns(n + m - 2, m - 1);
    int got = numberOfWays(n, m);
    if (got != expected) {
      System.err.println("Incorrect result for n, m = " + n + ", " + m);
      System.err.println("Expected " + expected);
      System.err.println("Your program computed " + got);
      System.exit(-1);
    }
  }
 
  public static void main(String[] args) {
    Random r = new Random();
    System.out.println( new Object() {}.getClass().getEnclosingClass().getMethods().toString());
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(10) + 1;
        m = r.nextInt(10) + 1;
      }
      System.out.println("n = " + n + ", m = " + m + ", number of ways = "
                         + computeNumberOfWaysSpaceEfficient(n, m));
      check(n, m);
      assert(computeNumberOfWaysSpaceEfficient(n, m) == numberOfWays(n, m));
      if (args.length == 2) {
        break;
      }
    }
    
  }
}