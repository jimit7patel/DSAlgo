package com.epi;
import java.util.*;
import com.epi.TrieNode;

public class WordSearchII2{

    List<String> result = new ArrayList<String>();
	//static Set<List<Integer>> dup = new HashSet<List<Integer>>();
   
	TrieNode tn = new TrieNode();
   
	 public List<String> findWords(char[][] board, String[] words) {
		
		 if (words == null){
			 return null;
		 }
		
		 for(String t:words){
			 tn.insert(t);
		 }
		 
		      for(int i=0;i<board.length;i++){
		    	  for(int j=0; j<board[0].length; j++){
		    		  //StringBuilder sb = new StringBuilder();
		    		  findWordsHelper(i,j,board,"");
		    	  }
		      }
		      Collections.sort(result);
	        return result;
	    }
	 
	 public void findWordsHelper(int r, int c,char[][] board,String l){ //StringBuilder l
		 
		 if(r>=board.length || r<0){
			 return;
		 }
		 if(c>=board[0].length || c<0){
			 return;
		 }
        char dup = board[r][c];
		 if(dup=='#'){                                         //dup.contains(Arrays.asList(r,c))
			 return;
		 }
       
		 l=l+board[r][c];									//l=l.append(board[r][c]);
        board[r][c]='#';
		                                                    //dup.add(Arrays.asList(r,c));
		 if(tn.search(l.toString())){
            if(!result.contains(l.toString()))
			    result.add(l.toString());
			
		 }
        if(tn.startsWith(l.toString())){
			 findWordsHelper(r+1,c,board,l);
			 findWordsHelper(r-1,c,board,l);
			 findWordsHelper(r,c+1,board,l);
			 findWordsHelper(r,c-1,board,l);
			 
		 }
		board[r][c]=dup;                                      // dup.remove(Arrays.asList(r,c));
		 													//l.deleteCharAt(l.length()-1);
		
		return;
	 }
	 public static void main(String[] args){
		char [][]board=new char[][]{
		 								{'a','b'},
										{'a','a'},
		 							};
		 						
		 String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};

		 for (String t:new WordSearchII2().findWords(board,words)){
			 System.out.println(t);
		 }
		 
	 }
}