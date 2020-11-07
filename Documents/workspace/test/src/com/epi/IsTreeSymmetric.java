package com.epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
    @EpiTest(testfile = "is_tree_symmetric.tsv")

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        // Implement this placeholder.
        if (tree == null)
            return true;
        return isSymmetricHelper(tree.left, tree.right);

    }

    public static boolean isSymmetricHelper(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> tree1) {
        if(tree == null && tree1 ==  null)
            return true;
        if(tree != null && tree1 != null) {
            if (tree.data == tree1.data) {
                if (isSymmetricHelper(tree.left, tree1.right) && isSymmetricHelper(tree.right, tree1.left))
                    return true;
                else
                    return false;
            } else
                return false;
        }
        else return false;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
