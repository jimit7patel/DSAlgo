package com.IK.dp;

import java.util.List;

public class SolveBalancedLineBreaks {

    public static long solveBalancedLineBreaks(List<String> words, int limit) {

        // Write your code here
        int n = words.size();
        long dp[] = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int total = 0;
            dp[i] = Long.MAX_VALUE;
            long currentLineCost;
            int no_of_words = 0;
            int non_space_total = 0;
            for (int j = i; j < n; j++) {
                non_space_total += words.get(j).length();
                no_of_words++;
                total = non_space_total + no_of_words - 1;
                if (total > limit) {
                    break;
                    // find cost
                }
                currentLineCost = (j == n - 1) ? 0 : (limit - total);
                currentLineCost = currentLineCost * currentLineCost * currentLineCost;
                dp[i] = Math.min(dp[i], currentLineCost + dp[j + 1]);
            }
        }
        return dp[0];

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
