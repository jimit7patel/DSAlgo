package com.IK.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetWithDuplicateInput {
    static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<>());
        return result;
    }

    public static void helper(int[] nums, int i, List<Integer> cur) {
        if (i == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        int c = 1;
        int b = nums[i];

        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            c++;
            i++;
        }
        for (int cc = 0; cc <= c; cc++) {
            int t = cc;
            while (cc > 0) {
                cur.add(b);
                cc--;
            }
            helper(nums, i + 1, cur);// include recursion and it is already added
            while (t > 0) {
                cur.remove(cur.size() - 1);
                t--;
            }

        }

        /*
         * cur.add(b);// include one element
         *
         * while(i+1 < nums.length && nums[i]==nums[i+1]){ c++; i++; }
         *
         * int cc = c; while(c>1){//multiple include like (2,2), (2,2,2) (2,2,2,2)
         * cur.add(b); helper(nums,i+1,cur); c--; } while(cc>1){
         * cur.remove(cur.size()-1); cc--; }
         *
         * helper(nums,i+1,cur);//include recursion and it is already added
         * cur.remove(cur.size()-1); helper(nums,i+1,cur);//exclude
         */
        return;
    }
    public static void main(String[] args) {
        print(subsetsWithDup(new int[] { 1, 2, 2 }));
        // TODO Auto-generated method stub

    }

    public static void print(List<List<Integer>> res) {
        for (List<Integer> list : res) {
            // for (Integer integer : list) {
            System.out.print(list);
            // }
        }
    }

}
