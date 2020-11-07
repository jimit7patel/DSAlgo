package com.epi;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TreePostorder {

    static List<Integer> result = new ArrayList<>();

    @EpiTest(testfile = "tree_postorder.tsv")

    // We use stack and previous node pointer to simulate postorder traversal.
    public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
        // Implement this placeholder.
        helper(tree);
        return result;
    }

    public static void helper(BinaryTreeNode<Integer> tree) {
        if (tree == null)
            return;
        helper(tree.left);

        helper(tree.right);
        result.add(tree.data);
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
