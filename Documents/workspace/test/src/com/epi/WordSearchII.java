package com.epi;
import java.util.*;
import com.epi.TrieNode;

public class WordSearchII{
	List<String> result = new ArrayList<String>();
	static Set<List<Integer>> dup = new HashSet<List<Integer>>();
	Set<String> dict = new HashSet<String>();
    public List<String> findWords(char[][] board, String[] words) {
		
		if (words == null){
			 return null;
		 }
		 for(String t:words){
			 dict.add(t);
		 }
		 
		      for(int i=0;i<board.length;i++){
		    	  for(int j=0; j<board[0].length; j++){
		    		  StringBuilder sb = new StringBuilder();
		    		  findWordsHelper(i,j,board,sb,dict);
		    	  }
		      }
        for(String s:result){
        }
	        return result;
	    }
	 public void findWordsHelper(int r, int c,char[][] board,StringBuilder l,Set<String> dict){
		 
		 if(r>=board.length || r<0){
			 return;
		 }
		 if(c>=board[0].length || c<0){
			 return;
		 }
		 if(dup.contains(Arrays.asList(r,c))){
			 return;
		 }
		 l=l.append(board[r][c]);
		 dup.add(Arrays.asList(r,c));
		 if(dict.contains(l.toString())){
             if(!result.contains(l.toString()))
			    result.add(l.toString());
		 }
			 findWordsHelper(r+1,c,board,l,dict);
			 findWordsHelper(r-1,c,board,l,dict);
			 findWordsHelper(r,c+1,board,l,dict);
			 findWordsHelper(r,c-1,board,l,dict);
			 dup.remove(Arrays.asList(r,c));
			 l.deleteCharAt(l.length()-1);
		 
		 
		 
	 }
	 public static void main(String[] args){
		char [][]board=new char[][]{
		 								{'o','a','a','n'},
										{'e','t','a','e'},
										{'i','h','k','r'},
										{'i','f','l','v'}
		 							};
		 						
		 String[] words = {"oath","pea","eat","rain"};
		 //char [][]board=new char[][]{
		//		{'a'},
		//	};
		 //String[] words = {};

		 for (String t:new WordSearchII().findWords(board,words)){
			 System.out.println(t);
		 }
		 
	 }
}