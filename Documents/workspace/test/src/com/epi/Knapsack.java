package com.epi;

import java.util.Arrays;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }
  private static int[][] w;
  private static int[] w1;
  private static int[] w2;
  @EpiTest(testfile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    // Implement this placeholder.
	  w = new int[items.size()+1][capacity+1];
	  for(int i=0;i<w.length;i++) {
		  Arrays.fill(w[i], 0);
	  }
	  w1=new int[capacity+1];
	  w2=new int[capacity+1];
	  Arrays.fill(w1,0);
	  Arrays.fill(w2,0);
	  //return knapSack(capacity,items,items.size());
	  //return knapSackChanged(capacity,items,items.size());
    return helper(items,capacity,0);
  }
  
  static int knapSack(int capacity, List<Item> items, int n)
  {
	  for(int i=1;i<=n;i++) {
		  for(int j=1;j<=capacity;j++) {
			  if(items.get(i-1).weight>j)
				  w[i][j]=w[i-1][j];
			  else {
				  w[i][j]=Math.max(items.get(i-1).value+w[i-1][j-items.get(i-1).weight],w[i-1][j]);
			  }
		  }
	  }
	  return w[n][capacity];
  }
  //below is space efficient algorithm
  static int knapSackChanged(int capacity, List<Item> items, int n)
  {
	  for(int i=1;i<=n;i++) {
		  for(int j=1;j<=capacity;j++) {
			  if(items.get(i-1).weight>j)
				  w2[j]=w1[j];
			  else {
				  w2[j]=Math.max(items.get(i-1).value+w1[j-items.get(i-1).weight],w1[j]);
			  }
		  }
		  for(int j=1;j<=capacity;j++) {
			 w1[j]=w2[j];
		  }
	  }
	  return w2[capacity];
  }
  
  
  public static int helper(List<Item> items, int capacity, int i) {
	  
	  if(i==items.size())
		  return 0;
	  if(w[i][capacity]==0) {
		  //return w[i][capacity];
		  int a = helper(items,capacity,i+1);
			  int b = capacity-items.get(i).weight>=0 ?
					  items.get(i).value+helper(items,capacity-items.get(i).weight,i+1)
					  :0;
		w[i][capacity]=Math.max(a, b);
	  }
	  //w[capacity][i] = Math.max(a, b);
	  return w[i][capacity];
  }
  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
