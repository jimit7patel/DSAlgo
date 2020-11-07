package com.epi;


import java.util.HashSet;
import java.util.Scanner;  

public class StringDecoding {
	class Mapping{
		int pos;
		int score;
		Mapping(int pos,int score){
			this.pos=pos;
			this.score=score;
		}
	}
	static int n;
	String input;
	static String result;
	static int quality;
  // @include	
    public void decode(String s){
    	input = s;
    	quality = -1;
    	HashSet<Mapping> vis = new HashSet<Mapping>();
    	helperAnother(0,"",0,vis);
    }
   
    public void helperAnother(int score,String s, int cur, HashSet<Mapping> vis){
    	if(vis.contains(new Mapping(cur,score))){
    		return;
    	}
    	vis.add(new Mapping(cur,score));
    	if(input.length()==cur){
    		if(score>quality){
    			quality = score;
    			result = s;
    		}
    	}
    	HashSet<Character> hs = new HashSet<Character>();
    	for(int i=0;i<3;i++,cur++){
    		if((cur==input.length()-1 && i==0)||(cur==input.length()))
    			break;
    		//if(cur>=input.length())
    			
    		if(i==0 & s!="")
    			s=s+"-";
    		s = s + input.charAt(cur);
    		hs.add(input.charAt(cur));
    		if(i==1){
    			helperAnother(score+(hs.size()==1?2:0),s,cur+1,vis);
    		}
    		if(i==2){
    			helperAnother(score+(3-hs.size()),s,cur+1,vis);
    		}
    	}
    	
    }
  public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  n = sc.nextInt();
	  
	  String s[] = new String[n];
	  
	  for (int i=0;i<n; i++)
		 s[i]=sc.next();
	  for(String t:s){
		  new StringDecoding().decode(t);
		  System.out.println(result);
	  }
		  
  }  
  
}