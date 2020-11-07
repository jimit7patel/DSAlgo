package lc.topliked.medium;
/*
 * https://leetcode.com/problems/convert-bst-to-greater-tree/ this is simple for
 * O(n) and space O(n) Interesting is Reverse Morris In-order Traversal. The
 * idea is to find first greater node than the current node. Then create a
 * temporary link from that node.left to current node. Then move to right node.
 * Then, repeat the same if right node is there. Otherwise, get the left node
 * and if the node is repeated then, remove the link
 *
 *
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class ConvertBSTToGreaterTree {
    private int sum = 0;

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
    public TreeNode convertBSTMorris(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            /*
             * If there is no right subtree, then we can visit this node and continue
             * traversing left.
             */
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            /*
             * If there is a right subtree, then there is at least one node that has a
             * greater value than the current one. therefore, we must traverse that subtree
             * first.
             */
            else {
                TreeNode succ = getSuccessor(node);
                /*
                 * If the left subtree is null, then we have never been here before.
                 */
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                /*
                 * If there is a left subtree, it is a link that we created on a previous pass,
                 * so we should unlink it and visit this node.
                 */
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
    public static int helper(TreeNode root, int prev) {
        if (root == null)
            return prev;
        if (root.right != null)
            prev = helper(root.right, prev);
        root.val = root.val + prev;
        prev = root.val;
        if (root.left != null) {
            prev = helper(root.left, prev);
        }
        return prev;

    }
}
