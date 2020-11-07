package com.epi;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsValidSudoku {
    @EpiTest(testfile = "is_valid_sudoku.tsv")

    // Check if a partially filled matrix has any conflicts.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        // Implement this placeholder.
        return helper(partialAssignment);
    }

    private static boolean helper(List<List<Integer>> partialAssignment) {

        // Set<Set<Integer>> checker = new HashSet<>();

        for(int row=0; row< partialAssignment.size(); row++) {
            Set<Integer> subSet = new HashSet<>();

            for(int col=0; col< partialAssignment.get(0).size(); col++) {
                if (partialAssignment.get(row).get(col) != 0 && !subSet.add(partialAssignment.get(row).get(col))) {
                    return false;
                }

            }

        }

        for(int col=0; col< partialAssignment.size(); col++) {
            Set<Integer> subSet = new HashSet<>();

            for(int row=0; row< partialAssignment.get(0).size(); row++) {
                if (partialAssignment.get(row).get(col) != 0 && !subSet.add(partialAssignment.get(row).get(col))) {
                    return false;
                }

            }

        }
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                Set<Integer> subSet = new HashSet<>();
                for(int row =0; row < 3; row++) {

                    for(int col =0; col < 3; col++) {

                        if (partialAssignment.get(row + j * 3).get(col + 3 * k) != 0 && !subSet.add(partialAssignment.get(row + j * 3).get(col + 3 * k)))
                            return false;

                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
