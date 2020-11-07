package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/*
 *A player can score 3 or 5 or 10 points in a move
 */
public class NumberWaysToReachAScore {
  // @include
  private static int[] table;
  private static int count;
  public static int numberOfWays(int n) {
	  table = new int[n+1];
	  Arrays.fill(table, 0);
	  
    //return computeNumberOfWaysToXY(n,0,"");
    return computeNumberOfWaysEfficientY(n);
    
  }

  private static int computeNumberOfWaysToXY(int x, int y, String s)
   {
	  System.out.println(" for x "+x+" s "+s+" y "+y+" count "+count);
	 if(x==0) {
		 //System.out.println(s);
		 return 1;
	 }
	 if(x<3)
		 return 0;
	 
	 //System.out.println(" for x "+x+" s "+s);
	 
	 //This condition is put to make sure that we do not have same combination in different order like (3,5) and (5,3)
	 count= (y<4?computeNumberOfWaysToXY(x-3,3,s+"3"):0);
	 count+=(y<6?computeNumberOfWaysToXY(x-5,5,s+"5"):0);
	 count+=computeNumberOfWaysToXY(x-10,10,s+"10");
	 return count;
	 
    
  }
  private static int computeNumberOfWaysEfficientY(int x)
  {
	  table[0]=1;
	 for(int i=3;i<=x;i=i+1) {
		 table[i]+=table[i-3];
		 table[i]+=(i-5>3?table[i-5]:i==5?table[0]:0);   //(i-5) and (i-10) is put to make sure that we do not have same combination in different order like (3,5) and (5,3)
		 table[i]+=(i-10>5?table[i-10]:i==10?table[0]:0);
		 //System.out.println(table[i]);
	 }
	 return table[x];
 }
  
  // @exclude

  
  public static void main(String[] args) {
	  System.out.println(numberOfWays(5));
  }
}