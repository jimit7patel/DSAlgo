package com.IK.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindOrderInAlienDictionary {

    /*
     * Complete the function below.
     */
    static Map<Character, Set<Character>> adj = new HashMap<>();
    static Map<Character, Integer> visited = new HashMap<>();
    static int timestamp = 1;
    static StringBuilder result = new StringBuilder();
    static Set<Character> all_chars = new HashSet<>();
    static String find_order(String[] words) {
        for(int i=0; i< words.length; i++) {
            String s = words[i];
            for(char c: s.toCharArray()) {
                all_chars.add(c);
            }
        }
        // if(words.length==1) {
        // return words[0].
        // }
        buildAdjList(words);
        // boolean[] visited = new boolean[adj.size()];
        for (Character c : all_chars) {
            if (!visited.containsKey(c)) {
                if (dfs(c)) {
                    return "";
                }
            }
        }
        return result.reverse().toString();
    }

    static void buildAdjList(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String s1 = words[i];
                String s2 = words[j];
                int k = 0, l = 0;
                while (k < s1.length()) {
                    if (s1.charAt(k) != s2.charAt(l)) {
                        char c = s1.charAt(k);
                        char d = s2.charAt(l);
                        if (!adj.containsKey(c)) {
                            Set<Character> s = new HashSet<>();
                            s.add(d);
                            adj.put(c, s);
                        } else {
                            adj.get(c).add(d);
                        }
                        break;
                    }
                    k++;
                    l++;
                }
            }
        }
    }

    static boolean dfs(Character c) {

        visited.put(c, 1);
        timestamp++;
        if (adj.containsKey(c)) {
            for (char d : adj.get(c)) {
                if (!visited.containsKey(d)) {
                    if (dfs(d)) {
                        return true;
                    }
                } else {
                    if (visited.get(d) == 1) {
                        return true;// destination is not set. cycle
                    }
                }
            }
        }

        visited.put(c, timestamp++);
        result = result.append(c);
        return false;
    }

    public static void main(String[] args) {
        // find_order(new String[] { "baa", "abcd",
        // "abca",
        // "cab",
        // "cad" });

        find_order(new String[] { "aab", "aac" });
    }
}
