package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TreeLevelOrder {
    @EpiTest(testfile = "tree_level_order.tsv")

    public static List<List<Integer>>
    binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        // Implement this placeholder.
        
        List<List<Integer>> result = new ArrayList<>();
        if (tree == null) {
            return result;
        }
        
        //Below is java 8 way of doing it using map and lambda
        /*
        List<BinaryTreeNode<Integer>> currDepthNodes = Arrays.asList(tree);
        while (!currDepthNodes.isEmpty()) {
            result.add(currDepthNodes.stream()    //creates stream from list
                    .map(curr -> curr.data) //iterates through each level of tree in each while iteration
                                            // and maps it to data
                    .collect(Collectors.toList())); // collects the cur.data and then creates list 
                                                    //and adds in result.
            currDepthNodes = currDepthNodes.stream()
                    .map(curr -> Arrays.asList(curr.left, curr.right)) //gets left and right nodes for each node in currDepthNodes
                    .flatMap(List::stream) //creates stream out of lists.
                    .filter(child -> child != null) // filters nulls
                    .collect(Collectors.toList()); // collects in to list and add to currDepthNodes.
        }
        return result;*/
        
        
        /*
        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int s = queue.size();
            while (s > 0) {
                BinaryTreeNode<Integer> cur = queue.pop();
                level.add(cur.data);
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);
                s--;
            }
            result.add(level);
        }*/
        
        
        //Below is fastest since it does not use extra queue. and saves time in queue traversal.
        dfs(result,tree,0);
        return result;
    }
    
    private static void dfs (List<List<Integer>> result,BinaryTreeNode<Integer> node,int lvl){
        if(node==null)
            return;
        if(result.size()==lvl) {
            List<Integer> t = new ArrayList<Integer>();
            t.add(node.data);
            result.add(t);
        }
        else {
            List<Integer> t = result.get(lvl);
            t.add(node.data);
            result.set(lvl,t);
        }
        dfs(result,node.left,lvl+1);
        dfs(result,node.right,lvl+1);
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
