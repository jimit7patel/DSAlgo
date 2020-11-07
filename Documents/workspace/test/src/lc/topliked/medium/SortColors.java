package lc.topliked.medium;
/*
 * This is also a dutch flag problem.
 * algo is simple.
 * if 2 swap to end and end--;
 * if 0, then swap with start, start++;
 * if 1, then as it is
 * */
public class SortColors {
    public void sortColors(int[] nums) {

        int low = 0;
        int high = nums.length - 1;
        int cur = 0;

        while (cur <= high) {
            int t = nums[cur];
            if (t == 0) {
                swap(nums, low, cur);
                low++;
                cur++;
            } else if (t == 1) {
                cur++;
            } else {
                swap(nums, high, cur);
                high--;
            }

        }
    }

    public void swap(int[] nums, int i, int j) {

        int t = nums[i] ^ nums[j];
        nums[j] = t ^ nums[j];
        nums[i] = t ^ nums[i];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
