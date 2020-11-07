package com.IK.dp;

public class StringInterleaves {
    static int l1;
    static int l2;
    static int l3;
    static String la;
    static String lb;
    static String li;
    
 
    public static boolean doStringsInterleave(String a, String b, String i) {
        // Write your code here
        l1 = a.length();
        l2 = b.length();
        l3 = i.length();
        la = a;
        lb = b;
        li = i;
        if (l3 != l2 + l1)
            return false;

        boolean dp[][] = new boolean[l1 + 1][l2 + 1];
        dp[l1][l2] = true;
        for (int ii = l1 - 1; ii >= 0; ii--) {
            dp[ii][l2] = i.charAt(l3 - l1 + ii) == a.charAt(ii) && dp[ii + 1][l2];
        }
        for (int ii = l2 - 1; ii >= 0; ii--) {
            dp[l1][ii] = i.charAt(l3 - l2 + ii) == b.charAt(ii) && dp[l1][ii + 1];
        }
        for (int ii = l1 - 1; ii >= 0; ii--) {
            for (int j = l2 - 1; j >= 0; j--) {
                dp[ii][j] = (dp[ii + 1][j] && (i.charAt(ii + j) == a.charAt(ii))) || (dp[ii][j + 1] && (i.charAt(ii + j) == b.charAt(j)));
            }
        }
        return dp[0][0];
        // below is dp solution

        // return l1 + l2 != l3 ? false : helper(0, 0);

    }
    
    public static boolean helper(int i, int j) {
        // base case
        if (i == l1 && j == l2 && (i + j) == l3) {
            return true;
        }

        // when either of two match
        if (i < l1 && j < l2 && la.charAt(i) == li.charAt(i + j) && lb.charAt(j) == li.charAt(i + j)) {
            return helper(i + 1, j) || helper(i, j + 1);
        }

        if (i < l1 && la.charAt(i) == li.charAt(i + j)) {
            return helper(i + 1, j);
        }

        if (j < l2 && lb.charAt(j) == li.charAt(i + j)) {
            return helper(i, j + 1);
        }
        return false;

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        doStringsInterleave("CAB", "", "DAB");

    }

}
