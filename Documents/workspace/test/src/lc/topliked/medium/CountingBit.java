package lc.topliked.medium;

import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/counting-bits/submissions/
 *
 * An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.
 *
 *
 *
 */
public class CountingBit {
    public int[] countBits(int num) {

        int[] result = new int[num + 1];
        if (num == 0)
            return new int[] { 0 };
        if (num == 1)
            return new int[] { 0, 1 };
        result[0] = 0;
        result[1] = 1;
        int two = 2;
        for (int i = 2; i <= num; i++) {
            if ((i % two) == 0) {
                two = i;
                System.out.println(two);
            }
            result[i] = result[i - two] + 1;
        }
        return result;
    }

    public static int[] anotherWay(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Arrays.stream(anotherWay(2)).forEach(i -> System.out.println(i));
    }

}
