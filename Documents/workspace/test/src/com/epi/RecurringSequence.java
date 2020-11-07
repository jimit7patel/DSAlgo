package com.epi;

import java.util.HashSet;

public class RecurringSequence {

    public static String findSequence(int num, int den, HashSet<Integer> repeats, String res) {
        int rem;

        if (num > den) {
            rem = num % den;
            repeats.add(rem);
            return findSequence(rem, den, repeats, res);
        }
        else {
            rem = (num * 10) % den;
            int ans = (num * 10) / den;
            res = res + Integer.toString(ans);
            if (!repeats.contains(rem)) {
                repeats.add(rem);
                return findSequence(rem, den, repeats, res);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println(findSequence(5, 22, new HashSet<>(), ""));
    }

}
