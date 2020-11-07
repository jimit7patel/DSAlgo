package com.epi;

import java.util.HashSet;
import java.util.Set;

public class LongestNonRepeating{

    public int findLength(String s) {

        // sliding window approach
        if (s.length() == 0)
            return 0;
        int slow = 0, fast = 0;
        Set<Character> seen = new HashSet<>();
        int result = Integer.MIN_VALUE;
        while(fast < s.length()) {
            if (!seen.contains(s.charAt(fast))) {
                seen.add(s.charAt(fast));
                result = Math.max(result, fast - slow + 1);
                fast++;
            } else {
                while (seen.contains(s.charAt(fast))) {
                    seen.remove(s.charAt(slow));
                    slow++;
                }
            }
        }

        return result;

    }

    // below approach is optimized window where we store index of seen value in
    // HashMap, so that we can skip slow to that index
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }


    public static void main(String[] args){
        String s1 = "abccccccccccde";
        //new MostCommonSubstring().findWords(s1,s2);
        System.out.println(new LongestNonRepeating().findLength(s1));
        System.out.println(new LongestNonRepeating().findLength("abcabcbb"));
        System.out.println(new LongestNonRepeating().findLength("pwwkew"));
        System.out.println(new LongestNonRepeating().findLength("bbb!bb"));
        System.out.println(new LongestNonRepeating().findLength(" "));

    }
}