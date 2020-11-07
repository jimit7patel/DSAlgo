package lc.topliked.medium;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/first-missing-positive/ you can solve by
 * sorting O(n log n) However, you can solve it in O(n). Idea is put each
 * element in its correct index. Then the one which is out of order, thats first
 * missing positive
 *
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int index = nums[i];
            if (nums[i] == i + 1 || index <= 0 || index > nums.length || nums[i] == nums[index - 1])
                i++;
            else
                swap(nums, i, index - 1);
        }
        i = 0;
        while (i < nums.length && nums[i] == i + 1) {
            i++;
        }
        return i + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public int firstMissingPositiveNotEfficient(int[] nums) {
        if (nums.length == 0)
            return 1;
        Arrays.sort(nums);
        int prev = 0;
        if (nums[0] > 1)
            return 1;
        for (int i : nums) {
            if (i > 0) {
                if (i != prev + 1 && i != prev) {
                    return prev + 1;
                } else {
                    prev = i;
                }
            }
        }
        return prev + 1;
    }
}
