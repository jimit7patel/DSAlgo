package lc.topliked.medium;

/*
 * https://leetcode.com/problems/longest-palindromic-substring/submissions/
 *
 * O(n^2) solution O(1) space O(n^2) and O(n^2) using DP, basically start from
 * bottom, save each 2 sequence, then 3, 4..
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";
        int result = 1;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            int t1 = findPalindromeLength(i, i, s);
            if (i + 1 < s.length()) {
                t1 = Math.max(t1, findPalindromeLength(i, i + 1, s));
            }

            if (t1 > result) {
                result = t1;
                index = i;
            }

        }
        System.out.println("result" + result + "index" + index);
        if (result % 2 == 0) {
            int start = index - (result / 2 - 1);
            return s.substring(start, start + result);
        } else {
            int start = index - (result / 2);
            return s.substring(start, start + result);
        }

    }

    public int findPalindromeLength(int i, int j, String s) {
        int l = s.length() - 1;
        int count = 0;

        while (i >= 0 && j <= l && s.charAt(i) == s.charAt(j)) {
            count = i == j ? count + 1 : count + 2;
            i--;
            j++;
        }
        return count;
    }
}
