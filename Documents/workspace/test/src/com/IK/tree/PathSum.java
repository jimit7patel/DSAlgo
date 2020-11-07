package com.IK.tree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class PathSum {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return res;
        helper(root, new ArrayList<>(), sum);
        return res;
    }

    public void helper(TreeNode c, List<Integer> l, int s) {
        if (c.left == null && c.right == null) {// leaf node
            if (c.val == s) {
                l.add(c.val);
                res.add(new ArrayList<>(l));
            }
            return;
        }
        l.add(c.val);
        if (c.left != null) {
            helper(c.left, l, s - c.val);
        }
        if (c.right != null) {
            helper(c.right, l, s - c.val);
        }
        l.remove(l.size() - 1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
