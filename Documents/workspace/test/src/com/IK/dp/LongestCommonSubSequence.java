package com.IK.dp;

public class LongestCommonSubSequence {
    public static String lcs(String a, String b) {
        // Write your code here
        int la = a.length();
        int lb = b.length();

        int dp[][] = new int[la + 1][lb + 1];

        for (int i = 1; i <= la; i++) {
            for (int j = 1; j <= lb; j++) {
                dp[i][j] = a.charAt(i - 1) == b.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int i = la;
        int j = lb;
        String result = "";
        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                result = result + a.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new StringBuilder(result).reverse().toString();

    }

    public static void main(String[] args) {
        lcs("ABCDE", "AECBD");
    }

}
