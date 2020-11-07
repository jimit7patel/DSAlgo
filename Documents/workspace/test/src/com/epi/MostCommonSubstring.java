package com.epi;
import java.util.*;
import com.epi.TrieNode;

public class MostCommonSubstring{

	 public void findWords(String s1,String s2) {
		 
		 int m = s1.length();
		 int max = 0;
		 int local=0;
		 String tmp = "";
		 String result="";
		 
		 for(int i=0;i<m;i++){
			 
			 for(int j=i+1;j<=m;j++){
				 
				 if(findMatch(s1.substring(i,j),s2)){
					 local++;
					 tmp = s1.substring(i,j);
					 if(local>max){
						 max=local;
						 result = tmp;
					 }

					 
				 }
			 }
			 local=0;
			 tmp="";
			 
		 }
		
		 
	 }
	 public String mostCommon(String s1, String s2){
		 int m = s1.length();
		 int n = s2.length();
		 int [][] dp = new int[m+1][n+1];
		 int max=0;
		 int id=0;
		 
		 for(int i=0;i<m;i++){
			 for(int j=0;j<n;j++){
				 if(s1.charAt(i)==s2.charAt(j)){
					 dp[i+1][j+1]=dp[i][j]+1;
					 if(dp[i+1][j+1]>max){
						 max=dp[i+1][j+1];
						 id=i;
					 }
				 }
			 }
		 }
		 return s1.substring(id-max+1,id+1);
		 
		 
	 }
	 public boolean findMatch(String t, String s){
		 
		 int l = t.length();
		 for (int i=0;i<=s.length()-l;i++){
			 if(s.substring(i,i+l).equals(t)){
				 for(int j=0;j<l;j++){
					 
				 }
					
				 return true;
			 }
			 
		 }
		 return false;
	 }
	 
	 
	 public static void main(String[] args){
		String s1 = "abccccccccccde";
		String s2 = "dbccccccabccde";
		//new MostCommonSubstring().findWords(s1,s2);
		System.out.println(new MostCommonSubstring().mostCommon(s1, s2));
		 
	 }
}