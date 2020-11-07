package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

public class KLargestValuesInBst {
    @EpiTest(testfile = "k_largest_values_in_bst.tsv")

    public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
        // Implement this placeholder.
        List<Integer> localResult = new ArrayList<>();
        helper(tree, k, localResult);
        return localResult;
    }

    private static void helper(BstNode<Integer> tree, int k, List<Integer> localResult) {
        if (tree == null)
            return;
        helper(tree.right, k, localResult);
        if (localResult.size() < k) {
            localResult.add(tree.data);
            if (localResult.size() < k) {
                helper(tree.left, k, localResult);
            }
        }

    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp =
    (expected, result) -> {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    };

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
