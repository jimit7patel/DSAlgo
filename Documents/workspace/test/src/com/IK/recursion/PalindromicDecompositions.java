package com.IK.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromicDecompositions {
    static List<String> res = new ArrayList<>();

    static String[] generate_palindromic_decompositions(String s) {
        // helper("", 0, 0, s);
        helper2("", 0, s);
        return res.toArray(new String[0]);
    }

    static void helper(String slate, int i, int j, String s) {
        if (j == s.length()) {
            res.add(slate);
            return;
        }
        if (j + 1 < s.length()) {
            helper(slate, i, j + 1, s);
        }
        if (ifPalindrome(s.substring(i, j + 1))) {
            helper(i == 0 ? s.substring(i, j + 1) : slate + "|" + s.substring(i, j + 1), j + 1, j + 1, s);
        }
    }

    static void helper2(String slate, int i, String s) {
        if (i == s.length()) {
            res.add(slate);
            return;
        }
        for (int index = i; index < s.length(); index++) {
            if (ifPalindrome(s.substring(i, index + 1))) {
                helper2(i == 0 ? s.substring(i, index + 1) : slate + "|" + s.substring(i, index + 1), index + 1, s);
            }
        }

    }
    static boolean ifPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static void main(String[] args) {
        generate_palindromic_decompositions("yyy");
        // TODO Auto-generated method stub

    }

}
