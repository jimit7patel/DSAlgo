package com.epi;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/majority-element-ii/submissions/ find all
 * elements greater than n/3
 */
public class MajorityElement{
    // at max, there will be only two elements who are greater than n/3
    // the approach is basically to keep track of two counters, and increment if
    // same value otherwise decrement.
    // if you have an element greater than n/3, then you will have that as part of
    // counter and n1/n2.
    // 1,2,3,1,2,3,1...here, 1 and 2 increments c1 c2, when 3 comes it decrements to
    // 0, again increment and then decrements to 0 when 3 comes.
    // so, 1,2,3,1,2,3 all are n/3,and you dont have any element greater n/3.
    // However, if you add extra 1, then it means, you have a majority and it is
    // greater than n/3. Hence, c1 will be 1 and n1 will be pointing to 1

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        int c1 = 0;
        int c2 = 0;
        int n1 = Integer.MAX_VALUE;
        int n2 = Integer.MAX_VALUE;

        for (int i : nums) {
            if (i == n1) {
                c1++;
            } else if (i == n2) {
                c2++;
            } else if (c1 == 0) {
                c1 = 1;
                n1 = i;
            } else if (c2 == 0) {
                c2 = 1;
                n2 = i;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = 0;
        c2 = 0;
        for (int i : nums) {
            if (i == n1)
                c1++;
            else if (i == n2)
                c2++;
        }
        if (c1 > n / 3)
            result.add(n1);
        if (c2 > n / 3)
            result.add(n2);
        return result;

    }

    public static void main(String[] args){
        //new MostCommonSubstring().findWords(s1,s2);
        System.out.println(new MajorityElement().majorityElement(new int[] { 1, 2, 3, 1, 2, 3, 1 }));

    }
}