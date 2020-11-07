package com.need.revision;

import java.util.ArrayList;
import java.util.List;

public class BalancedParensPermutations {

    public static List<String> permutations(int n) {
        // Implement this placeholder.
        ArrayList<String> result = new ArrayList<>();
        helper("", n, n, result);
        return result;
    }

    private static void helper(String str, int n, int m,ArrayList<String> result) {
        if(n==0 && m==0) {
            result.add(str);
            return;
        }

        if(n==0)
            helper(str + ")",0,m-1,result);
        else if (n == m)
            helper(str + "(",n-1,m,result);
        else {
            helper(str + ")",n,m-1,result);
            helper(str + "(",n-1,m,result);
        }
    }


    public static void main(String[] args) {
        for (String s : BalancedParensPermutations.permutations(3))
            System.out.format("\n%s", s);
    }
}
