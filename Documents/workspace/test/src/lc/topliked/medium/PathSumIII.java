package lc.topliked.medium;

import java.util.HashMap;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 * https://leetcode.com/problems/path-sum-iii/submissions/ brute force is O(n^2)
 * not very easy to implement. basically, from each node find continuous sum of
 * node, its left and right child. O(n) time and O(n) space, use
 * https://leetcode.com/problems/subarray-sum-equals-k/ or
 * /Users/jpatel203/Documents/workspace/test/src/lc/topliked/medium/SubarraySum.java
 * idea is to keep dict of continuous sum and try getting value of sum - target
 * from dict. sum Of(i,j) = sum(o,j) - sum(o,i) make sure to decrement dict
 * valueof current sum by 1 once returned to parent. in array it is not needed.
 */
class PathSumIII {
    int result;

    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);
        return optimized(root, 0, sum, hm);

    }

    public int pathSumNotOptimized(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);

    }

    public int helper(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return (root.val == sum ? 1 : 0) + helper(root.left, sum - root.val) + helper(root.right, sum - root.val);
    }

    public int optimized(TreeNode root, int sum, int target, HashMap<Integer, Integer> hm) {

        if (root == null)
            return 0;

        sum += root.val;

        int res = hm.getOrDefault(sum - target, 0);
        hm.put(sum, hm.getOrDefault(sum, 0) + 1);

        res += optimized(root.left, sum, target, hm) + optimized(root.right, sum, target, hm);
        hm.put(sum, hm.get(sum) - 1); // you have to decement sum count by 1 because it is being returned to parent
        // from here. So, when it goes to right child, it should not count value of its
        // parent's left child.
        return res;

    }
}
