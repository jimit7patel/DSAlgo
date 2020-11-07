package com.IK.tree;

import java.util.HashMap;

/*
 * In this only difference is count all possibilities from any node to any node.
 * Idea is same but you have to run main function for left and right node. There
 * is another idea but is a bit complex
 */
public class PathSum3 {
    int res = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return res;
        map.put(0, 1);
        helper(root, 0, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return res;
    }

    /*
     * this is optimum solution but a bit complex
     *
     */

    public void optimalHelper(TreeNode c, int s, int target) {
        s += c.val;
        res += map.getOrDefault(s - target, 0);
        map.put(s, map.getOrDefault(s, 0) + 1);

        if (c.left != null) {
            optimalHelper(c.left, s, target);
        }
        if (c.right != null) {
            optimalHelper(c.right, s, target);
        }
        map.put(s, map.getOrDefault(s, 0) - 1);
        return;

    }

    public void helper(TreeNode c, int s, int target) {
        if (s + c.val == target) {
            res++;
        }
        if (c.left != null) {
            helper(c.left, c.val + s, target);
        }
        if (c.right != null) {
            helper(c.right, c.val + s, target);
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
