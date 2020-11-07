package com.need.revision;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class InsertionDeletionBST {
  // @include
  public static class BinarySearchTree {
    private BSTNode<Integer> root = null;

    // @exclude
    public boolean empty() { return root == null; }

    public void clear() { root = null; }

   
    public boolean insert(Integer key){
    	if(root==null){
    		root = new BSTNode<Integer>(key,null,null);
    		return true;
    	}
    	BSTNode<Integer> parent=root;
    	BSTNode<Integer> tr=root;
    	boolean l=false,r=false;
    	while(tr!=null){
    		parent =tr;
    		if(key<tr.data){
    			tr = tr.left;
    			l=true;
    			r=false;
    		}
    		else if(key==tr.data){
    			return false;
    		}
    		else{
    			tr = tr.right;
    			r=true;
    			l=false;
    		}
    	}
    	if(l){
    		parent.left = new BSTNode<Integer>(key,null,null);
    	}
    	else if(r){
    		parent.right = new BSTNode<Integer>(key,null,null);
    	}
    	return true;
    }
    /*
    // @include
    public boolean insert(Integer key) {
      if (root == null) {
        root = new BSTNode<Integer>(key, null, null);
      } else {
    	  BSTNode<Integer> curr = root;
    	  BSTNode<Integer> parent = curr;
        while (curr != null) {
          parent = curr;
          int cmp = Integer.compare(key, curr.data);
          if (cmp == 0) {
            return false; // key already present, no duplicates to be added.
          } else if (cmp < 0) {
            curr = curr.left;
          } else { // cmp > 0.
            curr = curr.right;
          }
        }

        // Insert key according to key and parent.
        if (Integer.compare(key, parent.data) < 0) {
          parent.left = new BSTNode<Integer>(key, null, null);
        } else {
          parent.right = new BSTNode<Integer>(key, null, null);
        }
      }
      return true;
    }
*/
    
    
    public boolean delete(Integer key) {
      // Find the node with key.
    	BSTNode<Integer> curr = root, parent = null;
      while (curr != null && Integer.compare(curr.data, key) != 0) {
        parent = curr;
        curr = Integer.compare(key, curr.data) < 0 ? curr.left : curr.right;
      }

      if (curr == null) {
        // There's no node with key in this tree.
        return false;
      }

      BSTNode<Integer> keyNode = curr;
      if (keyNode.right != null) {
        // Find the minimum of the right subtree.
    	  BSTNode<Integer> rKeyNode = keyNode.right;
    	  BSTNode<Integer> rParent = keyNode;
        while (rKeyNode.left != null) {
          rParent = rKeyNode;
          rKeyNode = rKeyNode.left;
        }
        keyNode.data = rKeyNode.data;
        // Move links to erase the node.
        if (rParent.left == rKeyNode) {
          rParent.left = rKeyNode.right;
        } else { // rParent.left != rKeyNode.
          rParent.right = rKeyNode.right;
        }
        rKeyNode.right = null;
      } else {
        // Update root link if needed.
        if (root == keyNode) {
          root = keyNode.left;
          keyNode.left = null;
        } else {
          if (parent.left == keyNode) {
            parent.left = keyNode.left;
          } else {
            parent.right = keyNode.left;
          }
          keyNode.left = null;
        }
      }
      return true;
    }

    // @exclude
    public Integer getRootVal() { return root.data; }
    // @include
  }
  // @exclude

  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    assert(bst.empty());
    assert(bst.insert(7));
    assert(bst.insert(8));
    assert(bst.insert(9));
    assert(bst.insert(4));
    assert(bst.insert(3));
    assert(!bst.empty());
    assert(bst.insert(2));
    assert(bst.insert(5));
    assert(bst.delete(7));
    assert(bst.delete(9));
    // should output 8
    assert(bst.getRootVal() == 8);
    System.out.println(bst.getRootVal());
    assert(bst.delete(4));
    // should output 8
    assert(bst.getRootVal() == 8);
    System.out.println(bst.getRootVal());
    assert(bst.delete(8));
    // should output 5
    assert(bst.getRootVal() == 5);
    System.out.println(bst.getRootVal());
    assert(bst.delete(5));
    assert(bst.delete(3));
    // should output 2
    assert(bst.getRootVal() == 2);
    System.out.println(bst.getRootVal());
    assert(bst.delete(2));
    assert(!bst.delete(1));
    assert(bst.empty());
    assert(bst.insert(7));
    assert(bst.getRootVal() == 7);
    assert(bst.insert(9));
    assert(bst.getRootVal() == 7);
    assert(bst.delete(7));
    assert(bst.getRootVal() == 9);
  }
}