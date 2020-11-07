package com.epi;

import epi.test_framework.EpiTest;

public class SquareRoot {
    @EpiTest(testfile = "real_square_root.tsv")

    public static double squareRoot(double x) {
        // Implement this placeholder.
        if (x == 1)
            return 1;
        int start = 0;
        int end = (int) x;
        int n = 0;

        while (start <= end) {
            n = start + (end - start) / 2;
            if (n * n == x)
                break;
            if (n * n < x) {
                start = n + 1;
            } else
                end = n - 1;
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(squareRoot(900));
    }
}
