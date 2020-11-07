package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ABSqrt2 {
    /*
     * the problem can be solved using minheap. The only thing is we also need to
     * make sure that we do not store duplicates in queue; thats why treeset is
     * used. In order to make sure we do not store duplicate we need to call equal
     * method of SQRT class which we should implement.
     */

    @EpiTest(testfile = "a_b_sqrt2.tsv")

    public static List<Double> generateFirstKABSqrt2(int k) {

        TreeSet<SQRT> sorted = new TreeSet<>();
        // TreeSet<Number> usingjava8 = new TreeSet<>((a,b)->Double.compare(a.val,
        // b.val));
        ArrayList<Double> result = new ArrayList<>();

        sorted.add(new SQRT(0, 0));
        while (result.size() < k) {

            SQRT t = sorted.pollFirst();
            result.add(t.val);
            sorted.add(new SQRT(t.a + 1, t.b));
            sorted.add(new SQRT(t.a, t.b + 1));
        }
        return result;

    }

    /*
     * private static class Number {
     *
     * int a; int b; Double val;
     *
     * Number(int a , int b){ this.a = a; this.b = b; val = a + b * Math.sqrt(2); }
     *
     *
     * }
     */
    private static class SQRT implements Comparable<SQRT> {

        int a;
        int b;
        Double val;

        SQRT(int a , int b){
            this.a = a;
            this.b = b;
            val = a + b * Math.sqrt(2);
        }

        @Override
        public int compareTo(SQRT o) {
            return Double.compare(val, o.val);
        }

    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
