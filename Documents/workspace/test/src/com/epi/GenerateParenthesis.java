package com.epi;

import java.util.ArrayList;
import java.util.List;
/*
 * ((()))
 * (()())
 * (())()
 * ()(())
 * ()()()
 * backtracking '(' goes till n and ')' goes till '(' goes*/
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return null;
        }
        ArrayList<String> res = new ArrayList<>();
        helper("", res, 0, 0, n);
        return res;
    }

    public void helper(String s, List<String> res, int i, int j, int n) {
        if (i == n && j == n) {
            res.add(s);
            return;
        }

        if (i < n) {
            helper(s + "(", res, i + 1, j, n);
        }
        if (j < i) {
            helper(s + ")", res, i, j + 1, n);
        }

    }

    public static void main(String[] args) {

    }
}
