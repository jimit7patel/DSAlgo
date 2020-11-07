package com.epi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class TrieNode{

    public static class Trie{
        Trie[] children = new Trie[26];
        boolean isLeaf;
        public Trie() {
            // TODO Auto-generated constructor stub
        }
    }

    Trie root;
    TrieNode(){
        root=new Trie();
    }
    public void insert(String word){
        Trie p = root;
        //Trie[] childs = root.children;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            int a = c-'a';
            if(p.children[a]==null){
                Trie temp = new Trie();
                p.children[a] = temp;
                p=temp;
            }else{
                p=p.children[a];
            }
        }
        p.isLeaf=true;
    }

    public Trie searchNode(String word){
        Trie p=root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            int a = c - 'a';
            if(p.children[a]==null){
                return null;
            }else{
                p=p.children[a];
            }
        }
        if(p==root){
            return null;
        }
        return p;

    }
    public boolean search(String word){
        Trie p =searchNode(word);
        if(p==null){
            return false;
        }
        else if(p.isLeaf==true){
            return true;
        }
        else{
            return false;
        }

    }
    public boolean startsWith(String word){
        Trie p =searchNode(word);
        if(p==null){
            return false;
        }
        else{
            return true;
        }

    }



    // below is another approach using hashmap. Important thing is delete method
    // where you keep track of nodes in stack and then remove stack element and then
    // remove node of current element from the element popped from stack.

    // DO NOT EDIT
    // TrieNode class
    public class TTrieNode {
        public Character value;
        public Map<Character, TTrieNode> next = new HashMap<>();
        public boolean end = false;

        public TTrieNode(Character value) {
            value = value;
        }
    }

    public class TTrie {

        public TTrieNode root = new TTrieNode('\u0000');

        public boolean insert(String word) {
            TTrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                Map<Character, TTrieNode> t = cur.next;
                if (t.containsKey(c)) {
                    cur = t.get(c);
                } else {
                    TTrieNode tmp = new TTrieNode(c);
                    t.put(c, tmp);
                    cur = tmp;
                }

            }
            cur.end = true;
            return true;
        }

        public boolean isWord(String word) {
            if (word.isEmpty())
                return false;
            TTrieNode cur = root;
            for (Character c : word.toCharArray()) {
                Map<Character, TTrieNode> t = cur.next;
                if (!t.containsKey(c))
                    return false;
                cur = t.get(c);
            }
            if (cur.end == true)
                return true;
            else
                return false;
        }

        public boolean isPrefix(String prefix) {
            if (prefix.isEmpty())
                return false;
            TTrieNode cur = root;
            for (Character c : prefix.toCharArray()) {
                Map<Character, TTrieNode> t = cur.next;
                if (!t.containsKey(c))
                    return false;
                cur = t.get(c);
            }
            return true;
        }

        public ArrayList<String> startsWith(String prefix) {

            ArrayList<String> result = new ArrayList<>();

            TTrieNode cur = root;
            for (Character c : prefix.toCharArray()) {
                Map<Character, TTrieNode> t = cur.next;
                if (!t.containsKey(c))
                    return null;
                cur = t.get(c);
            }
            helper(prefix, cur, result);
            return result;
        }

        public void helper(String word, TTrieNode cur, ArrayList<String> result) {

            if (cur.end)
                result.add(word);
            Map<Character, TTrieNode> hm = cur.next;

            for (Character t : hm.keySet()) {
                helper(word + t, hm.get(t), result);
            }
        }

        public void remove(String word) {
            TTrieNode cur = root;
            Stack<TTrieNode> st = new Stack<>();

            for (Character c : word.toCharArray()) {
                Map<Character, TTrieNode> t = cur.next;
                if (!t.containsKey(c))
                    return;
                st.push(cur);
                cur = t.get(c);
            }
            cur.end = false;
            if (cur.next.keySet().size() > 0)
                return;
            while (cur.end == false && !st.isEmpty()) {
                TTrieNode t = st.pop();
                t.next.remove(cur.value);
                if (t.next.keySet().size() > 1)
                    return;
                cur = t;
            }

        }
    }
}