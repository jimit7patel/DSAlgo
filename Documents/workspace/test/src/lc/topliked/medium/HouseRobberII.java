package lc.topliked.medium;

public class HouseRobberII {

    /*
     * A house robber has a map of houses and the amount of gold in each home. The
     * robber knows that if two adjacent homes are robbed, then the neighborhood
     * security system will sound. Determine the total amount of gold the robber can
     * get without setting off the alarm.
     *
     * Input: Array of Nonnegative Integers. Output: Integer
     *
     * Input: [1, 2, 3] => Output: 4 Input: [1, 2, 4, 1, 5, 12, 5] => Output: 17
     *
     * Explanation: Knowing you can't rob from two adjacent houses The maximum gold
     * you can steal in each case:
     *
     * Example 1 1 + 3 = 4
     *
     * Example 2 1 + 4 + 12 = 17
     *
     * There is some frustration when people publish their perfect fine-grained algorithms without sharing any information abut how they were derived. This is an attempt to change the situation. There is not much more explanation but it's rather an example of higher level improvements. Converting a solution to the next step shouldn't be as hard as attempting to come up with perfect algorithm at first attempt.

    This particular problem and most of others can be approached using the following sequence:

    Find recursive relation
    Recursive (top-down)
    Recursive + memo (top-down)
    Iterative + memo (bottom-up)
    Iterative + N variables (bottom-up)
    Step 1. Figure out recursive relation.
    A robber has 2 options: a) rob current house i; b) don't rob current house.
    If an option "a" is selected it means she can't rob previous i-1 house but can safely proceed to the one before previous i-2 and gets all cumulative loot that follows.
    If an option "b" is selected the robber gets all the possible loot from robbery of i-1 and all the following buildings.
    So it boils down to calculating what is more profitable:

    robbery of current house + loot from houses before the previous
    loot from the previous house robbery and any loot captured before that
    rob(i) = Math.max( rob(i - 2) + currentHouseValue, rob(i - 1) )

    Step 2. Recursive (top-down)
    Converting the recurrent relation from Step 1 shound't be very hard.

    public int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }
    private int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
    }
    This algorithm will process the same i multiple times and it needs improvement. Time complexity: [to fill]

    Step 3. Recursive + memo (top-down).

    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return rob(nums, nums.length - 1);
    }

    private int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
        memo[i] = result;
        return result;
    }
    Much better, this should run in O(n) time. Space complexity is O(n) as well, because of the recursion stack, let's try to get rid of it.

    Step 4. Iterative + memo (bottom-up)

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            memo[i+1] = Math.max(memo[i], memo[i-1] + val);
        }
        return memo[nums.length];
    }
    Step 5. Iterative + 2 variables (bottom-up)
    We can notice that in the previous step we use only memo[i] and memo[i-1], so going just 2 steps back. We can hold them in 2 variables instead. This optimization is met in Fibonacci sequence creation and some other problems [to paste links].

    /* the order is: prev2, prev1, num
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }
     *
     */
    public int rob(int[] nums) {
        return iterative(nums);

    }

    public static int helper(int[] nums, int i, int[] cache) {
        if (i < 0)
            return 0;
        if (cache[i] > 0)
            return cache[i];
        int a = Math.max(nums[i] + helper(nums, i - 2, cache), helper(nums, i - 1, cache));
        cache[i] = a;
        return a;
    }

    // if you have a circular houses, then you call iterative for including 1st and
    // excluding last, or excluding 1st and including last.
    public static int iterative(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int prev2 = nums[0];
        int prev1 = Math.max(nums[1], prev2);
        if (nums.length == 2)
            return prev1;
        int max = Integer.MIN_VALUE;
        for (int i = 2; i < nums.length; i++) {
            max = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = max;
        }
        return max;

    }
}
