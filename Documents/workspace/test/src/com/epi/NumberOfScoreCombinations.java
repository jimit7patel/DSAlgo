package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfScoreCombinations {
    @EpiTest(testfile = "number_of_score_combinations.tsv")

    public static int
    numCombinationsForFinalScore(int finalScore,
            List<Integer> individualPlayScores) {
        // Implement this placeholder.
        int table[] = new int[finalScore+1];
        Collections.sort(individualPlayScores);
        Arrays.fill(table, 0);
        table[0]=1;
        //below is one way of implementation
        for (int j = 0; j < individualPlayScores.size(); j++) {

            for (int i = individualPlayScores.get(j); i <= finalScore; i = i + 1) {

                table[i] += table[i - individualPlayScores.get(j)];
            }
        }

        return table[finalScore];

    }


    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
