package lc.topliked.medium;

/*
 * https://leetcode.com/problems/product-of-array-except-self/submissions/ O(n)
 * solution. one from start. The another from end and use values calculated from
 * first loop.
 * For, 1,2,3,4
 * 1        1    1*2 1*2*3
 * 4*3*2    4*3   4    1
 * 
 */
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        int prev = 1;
        for (int i = 0; i < nums.length; i++) {
            output[i] = prev;
            prev *= nums[i];

        }
        prev = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= prev;
            prev *= nums[i];
        }
        return output;
    }
}
