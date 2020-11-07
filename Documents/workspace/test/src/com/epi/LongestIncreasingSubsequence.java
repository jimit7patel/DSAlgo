package com.epi;

/*
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/ It is
 * a DP solution of O(n^2)
 */
import java.util.Arrays;

public class LongestIncreasingSubsequence{
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0)
                return 0;
            int[] result = new int[nums.length];
            Arrays.fill(result, 1);
            int max = 1;
            for (int j = 1; j < nums.length; j++) {
                for (int i = 0; i < j; i++) {
                    if (nums[j] > nums[i]) {
                        result[j] = Math.max(result[j], result[i] + 1);
                        max = Math.max(max, result[j]);
                    }
                }
            }
            return max;
        }

        // below is brute force approach O(2^n). you can optimized that by memoization
        public int lengthofLIS(int[] nums, int prev, int curpos) {
            if (curpos == nums.length) {
                return 0;
            }
            int taken = 0;
            if (nums[curpos] > prev) {
                taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
            }
            int nottaken = lengthofLIS(nums, prev, curpos + 1);
            return Math.max(taken, nottaken);
        }

        // there is an O(n logn) solution not very intuitive
        /*
         * Consider the example:
         *
         * input: [0, 8, 6, 12, 1, 13, 2, 3, 4, 5]
         *
         * dp: [0]
         *
         * dp: [0, 8]
         *
         * dp: [0, 6]
         *
         * dp: [0, 6, 12]
         *
         * dp: [0, 1, 12]
         *
         * dp: [0, 1, 12, 13] [0, 1, 2, 3, 4, 5] which is not the longest increasing
         * subsequence, but length of dpdp array results in length of Longest Increasing
         * Subsequence.
         */
        public int lengthOfLISBS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }

    public static void main(String[] args){
    }
}