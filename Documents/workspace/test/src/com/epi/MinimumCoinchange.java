package com.epi;

/*
 * https://leetcode.com/problems/coin-change/submissions/
 */
class MinimumCoinchange
{

    public int coinChange(int[] coins, int amount) {
        // int ans = helper(coins,amount,0, new int[amount+1][coins.length]);
        // int ans = helperAnother(coins,amount, new int[amount+1]);
        int ans = bottomUp(coins, amount, new int[amount + 1]);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // below is one way of top down
    public int helper(int[] coins, int amount, int i, int[][] memo) {
        if (i == coins.length)
            return Integer.MAX_VALUE;
        if (amount == 0)
            return 0;
        if (memo[amount][i] != 0) {
            return memo[amount][i];
        }
        int a = Integer.MAX_VALUE;
        if (amount >= coins[i]) {
            a = helper(coins, amount - coins[i], i, memo);
            a = a == Integer.MAX_VALUE ? Integer.MAX_VALUE : a + 1;
        }
        int b = helper(coins, amount, i + 1, memo);
        int res = Math.min(a, b);

        memo[amount][i] = res;
        return res;
    }

    // below is aother top down
    public int helperAnother(int[] coins, int amount, int[] memo) {
        if (amount < 0)
            return Integer.MAX_VALUE;
        if (amount == 0)
            return 0;
        if (memo[amount] != 0)
            return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            res = Math.min(res, helperAnother(coins, amount - coins[i], memo));
        }
        res = res == Integer.MAX_VALUE ? Integer.MAX_VALUE : res + 1;
        memo[amount] = res;
        return res;

    }

    // below is bottom up approach
    public int bottomUp(int[] coins, int amount, int[] memo) {
        for (int i = 1; i <= amount; i++) {
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    res = Math.min(res, memo[i - coins[j]]);
                }
            }
            res = res == Integer.MAX_VALUE ? Integer.MAX_VALUE : res + 1;
            memo[i] = res;
        }
        return memo[amount];
    }


    public static void main(String[] args)
    {
    }
}