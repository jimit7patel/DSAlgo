package lc.topliked.medium;

import java.util.HashMap;

/*
 * https://leetcode.com/problems/subarray-sum-equals-k/solution/ brute borce is
 * simple.O(n^2) use O(n) space for hashmap for storing continous sum at each
 * index. If you have current sum - k, in hashmap, then you have solution
 *
 */

class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        /* below is brute force
     for(int i=0; i< nums.length; i++){
         int count = 0;
         for(int j=i; j<nums.length; j++){

             count +=nums[j];
             if(count==k)
                 result++;

         }

     }*/
        return optimized(nums,k);

    }

    public int optimized(int[] nums, int k){
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(0,1);
        int result = 0;
        int count = 0;
        for(int i=0; i< nums.length; i++){

            count+=nums[i];
            result+=hm.getOrDefault(count-k,0);
            hm.put(count,hm.getOrDefault(count,0)+1);

        }
        return result;

    }
}