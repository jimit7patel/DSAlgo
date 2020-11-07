package com.epi;
import java.util.HashSet;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorWithParent {
    // @include
    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
            BinaryTree<Integer> node1) {
        int depth0 = getDepth(node0), depth1 = getDepth(node1);
        // Makes node0 as the deeper node in order to simplify the code.
        if (depth1 > depth0) {
            BinaryTree<Integer> temp = node0;
            node0 = node1;
            node1 = temp;
        }
        // Ascends from the deeper node.
        int depthDiff = Math.abs(depth0 - depth1);
        while (depthDiff-- > 0) {
            node0 = node0.parent;
        }

        // Now ascends both nodes until we reach the LCA.
        while (node0 != node1) {
            node0 = node0.parent;
            node1 = node1.parent;
        }
        return node0;
    }

    private static int getDepth(BinaryTree<Integer> node) {
        int depth = 0;
        while (node.parent != null) {
            ++depth;
            node = node.parent;
        }
        return depth;
    }

    private static BinaryTree<Integer> findLCA(BinaryTree<Integer> node0,
            BinaryTree<Integer> node1) {

        HashSet<BinaryTree<Integer>> set = new HashSet<>();

        while(node0!=null || node1!= null) {
            if (node0 != null) {
                if (!set.add(node0))
                    return node0;
                node0=node0.parent;

            }
            if(node1!=null) {

                if(!set.add(node1))
                    return node1;
                node1=node1.parent;
            }
        }
        return null;

    }
    // @exclude
    @EpiTest(testfile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
            Integer key0, Integer key1) throws Exception {
        BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));
        BinaryTree<Integer> result1 = executor.run(() -> findLCA(node0, node1));

        if (result == null || result1 == null) {
            throw new TestFailure("Result can not be null");
        }

        return result1.data;
    }

    public static void main(String[] args) {
        // 3
        // 2 5
        // 1 4 6
        /*
         * BinaryTree<Integer> root = new BinaryTree<>(3, null, null, null); root.left =
         * new BinaryTree<>(2, null, null, root); root.left.left = new BinaryTree<>(1,
         * null, null, root.left); root.right = new BinaryTree<>(5, null, null, root);
         * root.right.left = new BinaryTree<>(4, null, null, root.right);
         * root.right.right = new BinaryTree<>(6, null, null, root.right);
         *
         * // should output 3 assert(LCA(root.left, root.right).data.equals(3));
         * System.out.println(LCA(root.left, root.right).data); // should output 5
         * assert(LCA(root.right.left, root.right.right).data.equals(5));
         * System.out.println(LCA(root.right.left, root.right.right).data); // should
         * output 3 assert(LCA(root.left, root.right.left).data.equals(3));
         * System.out.println(LCA(root.left, root.right.left).data); // should output 2
         * assert(LCA(root.left, root.left.left).data.equals(2));
         * System.out.println(LCA(root.left, root.left.left).data);
         */

        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }

}