package com.epi;
/*

@slug
search-BST-first-K

@summary
Write a program that takes as input a BST and
a value, and returns the node whose key equals the
input value and appears first in an inorder traversal of the BST.
For example, when applied to the BST in Figure~\vref{bst-first-occurrence-fig},
your program should return Node $B$ for $108$, Node $G$ for $285$, and null for
$143$.
Your initial attempt can use recursion, but your final solution cannot.


*/

import java.util.ArrayList;
import java.util.List;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class FindKLargestBST {
  // @include
  public static List<BSTNode<Integer>> findKLargestBST(BSTNode<Integer> tree,
                                                       Integer k) {
    List<BSTNode<Integer>> result = new ArrayList<>();
    findKLargestBSThelper(tree,result,k);
    return result;
  }
  public static void findKLargestBSThelper(BSTNode<Integer> tree,List<BSTNode<Integer>> result,Integer k ){

	  if(tree!=null && result.size()<k){
		  findKLargestBSThelper(tree.right,result,k);
		  if(result.size()<k){
			  result.add(tree);
		  }
		  findKLargestBSThelper(tree.left,result,k);
	  }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 7
    BSTNode<Integer> tree = new BSTNode<>(3);
    
    tree.left = new BSTNode<>(2);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(7);
    List<BSTNode<Integer>> result = findKLargestBST(tree,3);
    for (int i = 0; i < result.size(); i++) {
		System.out.println(result.get(i).data);
	}

    	
  }
}