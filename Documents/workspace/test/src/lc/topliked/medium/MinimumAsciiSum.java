package lc.topliked.medium;

/*
 * variation of min edit.
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/submissions/
 * DP
 * (i,j) = if(charAt(i)==charAt(j)) then (i-1,j-1)
 *          else Min((i-1,j) + ascii of charAt(i), (i,j-1) + ascii of charAt(j)
 */
public class MinimumAsciiSum {
    public int minimumDeleteSum(String s1, String s2) {

        int dp[][] = new int[s1.length() + 1][s2.length() + 1];
        for (int j = 0; j < s2.length(); j++) {
            dp[0][j + 1] = dp[0][j] + s2.charAt(j);
        }
        for (int j = 0; j < s1.length(); j++) {
            dp[j + 1][0] = dp[j][0] + s1.charAt(j);
        }
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                else
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1] + s1.charAt(i), dp[i + 1][j] + (s2.charAt(j)));
            }
        }
        return dp[s1.length()][s2.length()];

    }
}
