package com.epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {
  @EpiTest(testfile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
	  
    // Implement this placeholder.
	  int l1 = A.length();
	  int l2 = B.length();
	  int [][] d = new int [l1+1][l2+1];
	  
	  return helper(A,B,l1,l2,d);
	  //return helperDP(A,B,l1,l2,d);
  
}
  public static int helper(String word1, String word2, int l1, int l2, int d[][]){
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
public static int helperDP(String word1, String word2, int l1, int l2, int d[][]){

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
  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
