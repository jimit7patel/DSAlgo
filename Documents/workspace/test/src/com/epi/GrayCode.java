package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class GrayCode {
    
    /*
     * x ^ y 1111
     *       0011
     *    =  1100
     *  -1   0001
     *  =    1010
     * */
    public static List<Integer> grayCode(int numBits) {
        // Implement this placeholder.
        List<Integer> result = new ArrayList<>(Arrays.asList(0));
        helper(numBits, result, new HashSet<>(Arrays.asList(0)));
        return result;

    }

    private static boolean helper(int n, List<Integer> result, Set<Integer> visited) {

        if (result.size() == 1 << n) {
            return differsByOneBit(result.get(result.size() - 1), result.get(0));
        }

        int prev = result.get(result.size() - 1);
        for (int i = 0; i < n; i++) {

            int next = prev ^ (1 << i);

            if (!visited.contains(next)) {
                result.add(next);
                visited.add(next);

                if (helper(n, result, visited))
                    return true;

                result.remove(Integer.valueOf(next));
                visited.remove(Integer.valueOf(next));
            }

        }
        return false;

    }

    private static boolean differsByOneBit(int x, int y) {
        int bitDifference = x ^ y;
        return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
    }

    @EpiTest(testfile = "gray_code.tsv")
    public static void grayCodeWrapper(TimedExecutor executor, int numBits)
            throws Exception {
        List<Integer> result = executor.run(() -> grayCode(numBits));

        int expectedSize = (1 << numBits);
        if (result.size() != expectedSize) {
            throw new TestFailure("Length mismatch: expected " +
                    String.valueOf(expectedSize) + ", got " +
                    String.valueOf(result.size()));
        }
        for (int i = 1; i < result.size(); i++)
            if (!differsByOneBit(result.get(i - 1), result.get(i))) {
                if (result.get(i - 1).equals(result.get(i))) {
                    throw new TestFailure("Two adjacent entries are equal");
                } else {
                    throw new TestFailure(
                            "Two adjacent entries differ by more than 1 bit");
                }
            }

        Set<Integer> uniq = new HashSet<>(result);
        if (uniq.size() != result.size()) {
            throw new TestFailure("Not all entries are distinct: found " +
                    String.valueOf(result.size() - uniq.size()) +
                    " duplicates");
        }
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
