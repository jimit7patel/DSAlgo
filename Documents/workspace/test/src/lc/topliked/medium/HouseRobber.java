package lc.topliked.medium;

/*
 * https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-
 * tackling-of-the-problem its DP solution solved using hashmap. The below
 * solution using canRob flag can be replaced by node.left.left and
 * node.left.right by skipping a level. However, it is still a solution with
 * overlapping subproblem and optimized using map. However, those subproblems
 * cab be avoided if we store those in array. 1st is without robbed, 2nd is with
 * robbed. This is like a backtracking in tree
 */
import java.util.HashMap;

public class HouseRobber {

    public int rob(TreeNode root) {
        HashMap<Composite, Integer> hm = new HashMap<>();

        return helper(root, true, hm);

    }

    public static int[] backtrackingInTreeUsingRecursion(TreeNode node) {
        if (node == null)
            return new int[2];
        int[] left = backtrackingInTreeUsingRecursion(node.left);
        int[] right = backtrackingInTreeUsingRecursion(node.right);

        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = node.val + left[0] + right[0];
        return res;
    }
    public class Composite{
        TreeNode x;
        boolean canRob;
        Composite(TreeNode x, boolean canRob){
            this.x = x;
            this.canRob = canRob;
        }
        @Override
        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            Composite cmp = (Composite) obj;
            return (cmp.x == x && cmp.canRob == canRob);
        }
        @Override
        public int hashCode()
        {
            return x.val;
        }
    }
    public int helper(TreeNode node, boolean canRob, HashMap<Composite,Integer> hm){
        if(node == null)
            return 0;
        Composite c = new Composite(node,canRob);
        if(hm.containsKey(c))
            return hm.get(c);
        int count = 0;
        if(canRob){
            int a = node.val+helper(node.left,false,hm)+helper(node.right,false,hm);
            int b = helper(node.left,true,hm)+helper(node.right,true,hm);
            count = Math.max(a,b);

        }else{
            count = helper(node.left,true,hm)+helper(node.right,true,hm);
        }
        hm.put(c,count);
        return count;
    }
}
