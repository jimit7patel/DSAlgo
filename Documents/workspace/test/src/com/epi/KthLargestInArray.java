package com.epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class KthLargestInArray {
    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
    // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
    // the same can be developed using min heap and quick sort can be developed with
    // swapping so you dont need extra space.


    public static int getKth(int k, int[] nums, int start, int end) {

        int pivot = nums[end];

        int left = start;
        int right = end;

        while (true) {

            while (nums[left] < pivot && left < right) {
                left++;
            }

            while (nums[right] >= pivot && right > left) {
                right--;
            }

            if (left == right) {
                break;
            }

            swap(nums, left, right);
        }

        swap(nums, left, end);

        if (k == left) {
            return pivot;
        } else if (k < left) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

    @EpiTest(testfile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> A) {
        if (k < 1 || A == null) {
            return 0;
        }
        int[] nums = A.stream().mapToInt(i -> i).toArray();

        return getKth(A.size() - k, nums, 0, A.size() - 1);
    }

    public static int findKthLargest1(int k, List<Integer> A) {
        // Implement this placeholder.
        int start = 0;
        int end = A.size() - 1;
        int pivot = 0;
        int m = 0;
        while (start <= end) {
            m = start + (end - start) / 2;
            pivot = A.get(m);
            int i = start;
            int j = end;
            int l = start;
            while (l <= end) {
                if (A.get(l) >= pivot) {
                    A.set(j, A.get(l));
                    j--;
                }
                if (A.get(l) < pivot) {
                    A.set(i, A.get(l));
                    i++;
                }
                l++;
            }
            if ((A.size() - i) == k)
                break;
            if ((A.size() - i) > k) {
                start = i;
            } else {
                end = i;
            }

        }
        return pivot;
    }

    public static void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
    public static void main(String[] args) {

        System.exit(GenericTest.runFromAnnotations(args, new Object() {
        }.getClass().getEnclosingClass()).ordinal());

        // System.out.println(findKthLargest(7, Arrays.asList(1, 20, 3, 10, 5, 6, 2)));
    }
}
