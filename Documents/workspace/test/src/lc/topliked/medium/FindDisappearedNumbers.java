package lc.topliked.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/*
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/ the
 * idea is to do first pass go to index of each value in array and mark it
 * negative. in the second pass if you have index which is positive, then its
 * missing https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
 *
 */
public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0)
            return res;
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (nums[index - 1] > 0)
                nums[index - 1] = -nums[index - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res.add(i + 1);
        }
        return res;
    }

    public List<Integer> findDisappearedNumbersNotEfficient(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();
        int min = 1;
        int max = nums.length;
        Set<Integer> res = new HashSet<>();
        IntStream.range(min, max + 1).forEach(i -> res.add(i));
        Arrays.stream(nums).boxed().forEach(i -> res.remove(i));
        return new ArrayList(res);
    }


}
