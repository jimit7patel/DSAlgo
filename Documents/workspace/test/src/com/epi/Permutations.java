package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public class Permutations {
    @EpiTest(testfile = "permutations.tsv")

    public static List<List<Integer>> permutations(List<Integer> A) {
        // Implement this placeholder.
        List<List<Integer>> result = new ArrayList<>();
        helper(new ArrayList<>(A), 0, A, result);
        return result;
    }

    private static void helper(List<Integer> current, int i, List<Integer> A, List<List<Integer>> result) {
        if (i == A.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int k = i; k < A.size(); k++) {
            Collections.swap(current, i, k);
            helper(current, i + 1, A, result);
        }

    }
    
    //generic approach for Subsets, Permutations, Combination Sum, Palindrome Partioning
    private void helperGeneric(List<Integer> current, int[] nums, List<List<Integer>> result, boolean[] used){
        if(current.size()==nums.length){result.add(new ArrayList<>(current));return;}
        for(int i=0; i<nums.length; i++){
            if(!used[i]){
                current.add(nums[i]);
                used[i] = true;
                helperGeneric(current,nums,result,used);
                current.remove(current.size()-1);
                used[i] = false;
            }
            
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
