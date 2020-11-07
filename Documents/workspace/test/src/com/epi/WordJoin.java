package com.epi;
import java.util.*;
import com.epi.TrieNode;

public class WordJoin{

    List<String> result = new ArrayList<String>();
	//static Set<List<Integer>> dup = new HashSet<List<Integer>>();
	 public List<String> findWords(int[] input, HashMap<Integer,List<String>> hm) {
		 
		 
		 wordjoin(input,hm,"",0);
		return result;
		 
	 }
	 public void wordjoin(int[] input, HashMap<Integer,List<String>> hm, String s, int index){
		 if(index>input.length)
			 return;
		 if(index==input.length){
			 result.add(s);
			 return;
		 }
		 for(String t:hm.get(input[index])){
			 wordjoin(input,hm,s+t,index+1);
		 }
		 
	 }
	 
	 public static void main(String[] args){
		int[] input = {1,2};//"catsanddog";
		String[] a = {"A","B","C"};
		String[] b = {"D","E","F"};
		String[] c = {"G","H","I"};
		HashMap<Integer,List<String>> hm = new HashMap<Integer,List<String>>();
		
				hm.put(1, Arrays.asList(a));
				hm.put(2, Arrays.asList(b));
		 for (String t:new WordJoin().findWords(input,hm)){
			 System.out.println(t);
		 }
		 
	 }
}