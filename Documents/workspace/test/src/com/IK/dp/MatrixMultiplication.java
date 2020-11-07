package com.IK.dp;

public class MatrixMultiplication {

    /*
     * This is not prefix pattern rather multiple pair pattern input is (10,30,5,60)
     * means A -> 10 * 30,B-> 30 * 5, C -> 5 * 60 so, index is overlapped
     * f(start,end) -> f(start,index) + f(index,end) + cost[index] index varies from
     * each position base case f(i, i+1) -> 0, cost[index] = a[start] * a[index] *
     * a[end];
     */
    static int minMultiplicationCost(int[] mtxSizes) {
        int n = mtxSizes.length;

        int dp[][] = new int[n][n];

        for (int l = 2; l < n; l++) {
            for (int start = 0; start < n - l; start++) {
                int end = start + l;
                dp[start][end] = Integer.MAX_VALUE;
                for (int k = start + 1; k < end; k++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k][end] + mtxSizes[start] * mtxSizes[k] * mtxSizes[end]);
                }
            }
        }
        return dp[0][n - 1];

    }

    static int helper(int[] mtxSizes, int start, int end) {
        if (end == start + 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int index = start + 1; index < end - 1; index++) {

            max = Math.max(max, helper(mtxSizes, start, index) + helper(mtxSizes, index, end) + mtxSizes[start] * mtxSizes[index] * mtxSizes[end]);
        }
        return max;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        minMultiplicationCost(new int[] { 10, 30, 5, 60 });
    }

}
