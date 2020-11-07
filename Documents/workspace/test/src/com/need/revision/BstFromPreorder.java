package com.need.revision;

import java.util.List;

import com.epi.BstNode;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
/*        10
 *      3      12
 *    1   5  11   13
 *     2
 *    pre order -> 10,3,1,2,5,12,11,13
 *           10
 *        3     12
 *      1   5  11  13
 *       2
 * find index to divide left tree and right tree.
 * Then build recursively by calling helper for left and right.
 * */
public class BstFromPreorder {
    @EpiTest(testfile = "bst_from_preorder.tsv")

    public static BstNode<Integer>
    rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        // Implement this placeholder.

        return helper(preorderSequence, 0, preorderSequence.size() - 1);

    }


    private static BstNode<Integer> helper(List<Integer> preorderSequence, int l, int r) {
        if (l > r)
            return null;

        int m = findIndex(preorderSequence, l + 1, r, preorderSequence.get(l));
        return new BstNode<>(preorderSequence.get(l), helper(preorderSequence, l + 1, m - 1), helper(preorderSequence, m, r));

    }

    private static int findIndex(List<Integer> preorderSequence, int l, int r, int val) {

        for (int i = l; i <= r; i++) {
            if (preorderSequence.get(i) >= val)
                return i;
        }
        return r + 1;

    }
    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
