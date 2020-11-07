package com.IK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringTransformation {

    /*
     * Complete the function below.
     */

    /*
     * Complete the function below.
     */

    static String[] string_transformation(String[] words, String start, String stop) {

        Set<String> dict = new HashSet<>(Arrays.asList(words));
        dict.add(stop);
        Deque<String> q = new LinkedList<>();
        q.addLast(start);
        Map<String, String> visited = new HashMap<>();
        boolean ifFirst = true;
        if (words.length > 26 * start.length())
            ifFirst = false;
        visited.put(start, null);
        while (!q.isEmpty()) {
            String t = q.removeFirst();
            if (is1Off(t, stop)) {
                visited.put(stop, t);
                List<String> result = buildResult(stop, visited);
                return result.toArray(new String[result.size()]);
                // found the answer
            }
            List<String> nei = new ArrayList<>();
            if (ifFirst) {
                nei = findNei2(t, dict, visited);
            } else {
                nei = findNeighbours(t, dict, visited);
            }
            for (String n : nei) {
                visited.put(n, t);
                q.addLast(n);
                dict.remove(n);
            }
        }

        return new String[] { "-1" };
    }

    static List<String> buildResult(String stop, Map<String, String> visited) {
        List<String> out = new ArrayList<>();
        String k = stop;
        out.add(k);
        while (visited.get(k) != null) {
            k = visited.get(k);
            out.add(k);
        }
        Collections.reverse(out);
        return out;
    }

    static List<String> findNeighbours(String t, Set<String> dict, Map<String, String> visited) {
        int l = t.length();
        List<String> nei = new ArrayList<>();

        for (int i = 0; i < t.length(); i++) {
            for (int j = 0; j <= 26; j++) {
                char c = (char) ('a' + j);
                String n = t.substring(0, i) + c + t.substring(i + 1, l);
                if (!t.equals(n) && !visited.containsKey(n) && dict.contains(n)) {
                    nei.add(n);
                }
            }
        }

        return nei;
    }

    static List<String> findNei2(String t, Set<String> dict, Map<String, String> visited) {
        List<String> nei = new ArrayList<>();
        for (String s : dict) {
            if (is1Off(t, s) && !visited.containsKey(s) && dict.contains(s)) {
                nei.add(s);
            }
        }
        return nei;
    }

    static boolean is1Off(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return (diff == 1);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        string_transformation(new String[] { "cccw", "accc", "accw" }, "cccc", "cccc");
    }
}

