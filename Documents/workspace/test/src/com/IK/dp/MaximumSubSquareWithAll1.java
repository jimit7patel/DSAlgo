package com.IK.dp;

import java.util.List;

public class MaximumSubSquareWithAll1 {
    public static int largest_sub_square_matrix(int n, int m, List<List<Integer>> mat) {
        // Write your code here
        // we are going to use DP solution
        // recurrence is f(i,j) = min(f(i-1,j),f(i-1,j-1),f(i,j-1)) + 1 if 1 else 0
        // base case
        int result = 0;
        for (int col = 0; col < m; col++) {
            result = result | mat.get(0).get(col);
        }
        for (int row = 0; row < n; row++) {
            result = result | mat.get(row).get(0);
        }
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (mat.get(row).get(col) == 1) {
                    int a = Math.min(Math.min(mat.get(row - 1).get(col), mat.get(row - 1).get(col - 1)), mat.get(row).get(col - 1)) + 1;
                    result = Math.max(result, a);
                    mat.get(row).set(col, a);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
