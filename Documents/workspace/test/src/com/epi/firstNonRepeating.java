package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class MappingValue{
	private int count;
	private int index;
	MappingValue(int index){
		this.count=1;
		this.index=index;
	}
	public void incrementCount(){
		this.count++;
	}
	public int getCount(){
		return this.count;
	}
	public int getIndex(){
		return this.index;
	}
	
}

public class firstNonRepeating {
	 
  
	 static HashMap<Character,MappingValue> hm = new HashMap<Character,MappingValue>();	     
	    /* calculate count of characters 
	       in the passed string */
	     static void getCharCountArray(String str) 
	     {
	         for (int i = 0; i < str.length();  i++){
	              if(hm.containsKey(str.charAt(i))){
	            	  MappingValue tmp = hm.get(str.charAt(i));
	            	  tmp.incrementCount();
	            	  hm.put(str.charAt(i), tmp);
	              }
	              else
	            	  hm.put(str.charAt(i), new MappingValue(i)); 

	         }
	        
	     }
	      
	    /* The method returns index of first non-repeating
	       character in a string. If all characters are repeating 
	       then returns -1 */
	    static int firstNonRepeating(String str)
	    {
	        getCharCountArray(str);
	        int index = Integer.MAX_VALUE;
	       for(Character t: hm.keySet()){
	    	   if(hm.get(t).getCount()==1)
	    		   index=Math.min(index,hm.get(t).getIndex());
	       }
	       
	      return index;
	    }
 

 

 

	 public static void main (String[] args)
	    {
	        String str = "geeksforgeeksisndndriosnlkjsndkzn;fljdl;jm;sm;mdfl;jmljg'jnkldfbglsdlnfkjdnksnfkbdskbgkbjdlna";
	        int index =  firstNonRepeating(str);
	         
	        System.out.println(index == Integer.MAX_VALUE ? "Either all characters are repeating or string " +
	                   "is empty" : "First non-repeating character is " +  str.charAt(index));
	    }
  
}