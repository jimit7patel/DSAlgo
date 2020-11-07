package com.epi;

import java.util.HashMap;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TreeFromPreorderInorder {
    @EpiTest(testfile = "tree_from_preorder_inorder.tsv")

    public static BinaryTreeNode<Integer>
    binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
        HashMap<Integer,Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inorderMap.put(inorder.get(i), i);
        }
        return helper(preorder, 0, preorder.size() - 1, 0, inorder.size() - 1, inorderMap);

    }

    private static BinaryTreeNode<Integer> helper(List<Integer> preorder, int i, int size, int j, int size2, HashMap<Integer, Integer> inorderMap) {

        if (i > size || j > size2)
            return null;

        int index = inorderMap.get(preorder.get(i));
        int l = index - j;
        return new BinaryTreeNode<>(preorder.get(i),
                helper(preorder, i + 1, i + l, j, index - 1, inorderMap),
                helper(preorder, i + l + 1, size, index + 1, size2, inorderMap));

    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
