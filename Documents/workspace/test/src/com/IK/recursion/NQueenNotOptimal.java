package com.IK.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueenNotOptimal {
    static List<String[]> finalRes = new ArrayList<>();
    static String[][] find_all_arrangements(int n) {
        String[][] res = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = "-";
            }
        }
        print(res);
        helper(res, 0);
        return finalRes.toArray(new String[0][0]);

    }

    static void helper(String[][] res, int i) {
        if (i == res.length) {
            addToList(res);
            return;
        }
        for (int j = 0; j < res.length; j++) {
            if (ifValid(res, i, j)) {
                res[i][j] = "q";
                helper(res, i + 1);
                res[i][j] = "-";
            }
        }
    }


    /*
     * this function can be optimized by this way.
     * maintain coumn[] that indicates column already placed.
     * boolean a[row+column] of(2n-1) this means that from (i,j) the next diagnoal will be (i-1)(j+1)
     * so sum is same value for slash diagnoal
     * for (i-1)(j-1) boolean b[row + n - columns - 1] row + (n-col-1) is same for backslash.
     * */

    /*
     * another way is maintain list of placed col[]
     * then for row = r, check each i till r, abs(col[i] - col[r]) if 0, or eq to (r-i)
     * */
    static boolean ifValid(String[][] res, int i, int j) {
        for (int row = 0; row < i; row++) {
            if (res[row][j] == "q") {
                return false;
            }
        }
        int row = i;
        int col = j;

        while (row >= 0 && col >= 0) {
            if (res[row][col] == "q") {
                return false;
            }
            row--;
            col--;
        }
        row = i;
        col = j;
        while (row >= 0 && col < res.length) {
            if (res[row][col] == "q") {
                return false;
            }
            row--;
            col++;
        }
        return true;
    }

    static void print(String[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();
        }
    }

    static void addToList(String[][] res) {
        String[] tm = new String[res.length];
        for (int i = 0; i < res.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < res[i].length; j++) {
                sb.append(res[i][j]);
            }
            tm[i] = sb.toString();
        }
        finalRes.add(tm);

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        find_all_arrangements(4);

    }

}
