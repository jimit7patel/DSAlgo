package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/*
 *    0 1 2 3 4 5 6 7
 *  2 0 m 1 m 2 m 3 m
 *  3 0   1 1 2 2 2 3 
 *  4 0
 * 
 * problem is to find min coin change to get the amount. 
 * This is DP, find sub problems of small amounts. use 2D array of coin and amount. 
 * intialize 0 to amount 0 column and find fist row by getting divisor
 * */
public class CoinChange {
  // @include
  private static  int v[][];
  private static  int vv[];

	public static int coinChange(int[] coins, int amount) {
		if(amount==0)
			return 0;
		v= new int[coins.length][amount+1];
		vv= new int[amount+1];
		for(int i=0;i<v[0].length;i++) {
			for(int j=0;j<v.length;j++) {
				if(i==0)
					v[j][i]=0;
				else if(j==0)
					v[j][i]=(i%coins[j]==0?i/coins[j]:Integer.MAX_VALUE);
				else
					v[j][i]=Integer.MAX_VALUE;
				
			}
		}
		
		Arrays.fill(vv, Integer.MAX_VALUE-1);
		vv[0]=0;
		
		//List coin = Arrays.asList(coins);
		//ArrayList<Integer> coinsa = new ArrayList<Integer>(coin);

		//int result = coinChangeHelperOptimized(coins,amount);
		//return (result==Integer.MAX_VALUE?-1:result);
		//return (result==Integer.MAX_VALUE?-1:result);
		//int result = coinChangeHelper(coins,amount);
		//return (result==Integer.MAX_VALUE?-1:result);
		
		int result = coinChangeHelperSpaceEfficient(coins,amount);
		return (result==Integer.MAX_VALUE?-1:result);
    }
	
//this does not work on optimized way
public static int coinChangeHelperOptimized(int[] coins, int amount) {
	
	if(amount==0)
		return 0;
	if(vv[amount]==Integer.MAX_VALUE-1) {
	for(int i=0;i<coins.length;i++) {
		int a = Integer.MAX_VALUE;
		if(coins[i]<=amount) {
			a =coinChangeHelperOptimized(coins,amount-coins[i]);
			if(a!=Integer.MAX_VALUE)
				a++;
			
		}
		if(vv[amount]==Integer.MAX_VALUE-1)
			vv[amount]=Integer.MAX_VALUE;
		vv[amount]=Math.min(vv[amount], a);
	}
	
	}
	return vv[amount];
	
  }

//bottom up approach
public static int coinChangeHelper(int[] coins, int amount) {
	
	for(int i=1;i<v.length;i++) {
		for(int j=1;j<v[0].length;j++) {
			
			if(coins[i]>j)
				v[i][j]=v[i-1][j];
			else if(v[i][j-coins[i]]!=Integer.MAX_VALUE && 1+v[i][j-coins[i]]<v[i-1][j])
					v[i][j]=1+v[i][j-coins[i]];
				else
					v[i][j]=v[i-1][j];
					
			
		
		}
	}
	return v[coins.length-1][amount];
  }

//bottom up approach space efficient.
public static int coinChangeHelperSpaceEfficient(int[] coins, int amount) {
	
	for(int i=0;i<coins.length;i++) {
		for(int j=1;j<=amount;j++) {
			
			if(coins[i]>j)
				continue;
			if(vv[j-coins[i]]!=Integer.MAX_VALUE && 1+vv[j-coins[i]]<vv[j])
					vv[j]=1+vv[j-coins[i]];
			
		
		}
	}
	return vv[amount];
}

  public static void main(String[] args) {
	  int a[] = {357,239,73,52};
			  
	  System.out.println(coinChange(a,9832));
	  System.out.println(coinChangeHelperOptimized(a,9832));
	  System.out.println(coinChangeHelperSpaceEfficient(a,9832));
	  System.out.println(coinChangeHelper(a,9832));
	  for(int i=0;i<v.length;i++) {
		  String s="";
		  for(int j=0;j<v[0].length;j++) {
			  s=s+v[i][j]+" ";
				
			}
		  //System.out.println(s);
		}
  }
}