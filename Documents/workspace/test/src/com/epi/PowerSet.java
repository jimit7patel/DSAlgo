package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public class PowerSet {
    @EpiTest(testfile = "power_set.tsv")

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {

        // Implement this placeholder.
        // return oneWayHelper(inputSet, 0, inputSet.size());
        List<List<Integer>> result = new ArrayList<>();
        anotherHelper(inputSet, 0, inputSet.size(), new ArrayList<>(), result);
        return result;
    }

    public static void anotherHelper(List<Integer> inputSet, int i, int size, List<Integer> cur, List<List<Integer>> result) {
        if (i == size) {
            result.add(new ArrayList<>(cur));
            return;
        }
        anotherHelper(inputSet, i + 1, size, cur, result);
        cur.add(inputSet.get(i));
        anotherHelper(inputSet, i + 1, size, cur, result);
        cur.remove(cur.size() - 1);

    }
    public static List<List<Integer>> oneWayHelper(List<Integer> inputSet, int i, int size) {
        List<List<Integer>> tmpResult;
        if (i < size) {

            tmpResult = oneWayHelper(inputSet, i + 1, size);
            List<List<Integer>> result = new ArrayList<>();
            for (List<Integer> a : tmpResult) {
                result.add(new ArrayList<>(a));
            }

            for (List<Integer> a : tmpResult) {
                a.add(inputSet.get(i));
                result.add(new ArrayList<>(a));
            }

            return result;
        }
        else {
            tmpResult = new ArrayList<>();
            tmpResult.add(new ArrayList<Integer>());
            return tmpResult;
        }
    }

    @EpiTestComparator
    public static BiPredicate < List<List<Integer>>,
    List < List<Integer>>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        for (List<Integer> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<Integer> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
