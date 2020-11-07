package com.need.revision;

import com.epi.BstNode;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorInBst {

    // Input nodes are nonempty and the key at s is less than or equal to that at
    // b.
    public static BstNode<Integer>
    findLCA(BstNode<Integer> tree, BstNode<Integer> s, BstNode<Integer> b) {
        // Implement this placeholder.
        BstNode<Integer> tmp = null;
        if (s.data > b.data) {
            tmp = s;
            s = b;
            b = tmp;
        }

        while (tree.data < s.data || tree.data > b.data) {
            // Keep searching since p is outside of [s, b].
            while (tree.data < s.data) {
                tree = tree.right; // LCA must be in p's right child.
            }
            while (tree.data > b.data) {
                tree = tree.left; // LCA must be in p's left child.
            }
        }
        // Now, s.data >= p.data && p.data <= b.data.
        return tree;
    }

    @EpiTest(testfile = "lowest_common_ancestor_in_bst.tsv")
    public static int lcaWrapper(TimedExecutor executor, BstNode<Integer> tree,
            Integer key0, Integer key1) throws Exception {
        BstNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BstNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BstNode<Integer> result = executor.run(() -> findLCA(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can't be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
