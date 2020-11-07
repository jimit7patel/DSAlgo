package com.epi.slidingwindow;

import java.util.HashMap;

public class lengthOfLongestSubstringKDistinct {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {

        HashMap<Character, Integer> hm = new HashMap<>();
        int l = s.length();
        int start = 0;
        int ans = 0;

        for (int end = 0; end < l; end++) {
            if (hm.containsKey(s.charAt(end))) {
                hm.put(s.charAt(end), hm.get(s.charAt(end)) + 1);
            } else {
                hm.put(s.charAt(end), 1);
            }
            while (start <= end && hm.size() > k) {
                if (hm.get(s.charAt(start)) == 1) {
                    hm.remove(s.charAt(start));
                } else {
                    hm.put(s.charAt(start), hm.get(s.charAt(start)) - 1);
                }

                start++;
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.print(lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
