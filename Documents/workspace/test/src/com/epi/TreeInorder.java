package com.epi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;


public class TreeInorder {

    static List<Integer> result = new ArrayList<>();

    // this may have wrong test data and wrong results.
    @EpiTest(testfile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {


        // Implement this placeholder.
        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        BinaryTreeNode<Integer> cur = new BinaryTreeNode<>();

        cur = tree;
        while (!queue.isEmpty() || cur != null) {

            if (cur != null) {
                queue.addFirst(cur);
                cur = cur.left;
            } else {

                cur = queue.removeFirst();
                result.add(cur.data);
                cur = cur.right;
            }
        }

        return result;
    }

    //Implement without extra space. Tree has pointer to parent node.

    public static void spaceEfficient(BinaryTree<Integer> tree) {
        BinaryTree<Integer> cur = new BinaryTree<>();
        BinaryTree<Integer> prev = new BinaryTree<>();
        BinaryTree<Integer> next = new BinaryTree<>();

        while(cur!=null) {
            if(cur.parent==prev) { //this is normal top to down approach
                if(cur.left!=null)
                    next=cur.left;
                else {
                    result.add(cur.data);
                    next = cur.right!=null?cur.right:cur.parent;
                }

            }else { // this is bottom to top
                if(cur.left==prev) {
                    result.add(cur.data);
                    next = cur.right!=null?cur.right:cur.parent;
                }
                else {// this case is when cur.right==prev, cur is already stored.
                    //Hence, ingore and travese next
                    next = cur.parent;
                }
            }
            prev=cur;
            cur=next;
        }
    }

    public static void helper(BinaryTreeNode<Integer> tree) {
        if (tree == null)
            return;
        helper(tree.left);
        result.add(tree.data);
        helper(tree.right);
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}