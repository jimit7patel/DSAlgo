package com.epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

    public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
            Integer k) {
        // Implement this placeholder.
        BstNode<Integer> result = null;
        while (tree != null) {
            /*if (tree.data == k) {    // this is not needed.
                tree = tree.right;
                break;
            }*/
            if (tree.data > k) {
                result = tree;
                tree = tree.left;
            } else
                tree = tree.right;
        }
        /*while (tree != null) {   //this is not needed as it is repeated.
            result = tree;
            tree = tree.left;
        }*/

        return result;
    }


    @EpiTest(testfile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
            Integer k) {
        BstNode<Integer> result = findFirstGreaterThanK(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
