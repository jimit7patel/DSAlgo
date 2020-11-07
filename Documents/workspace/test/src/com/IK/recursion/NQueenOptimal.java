package com.IK.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueenOptimal {
    static List<String[]> finalRes = new ArrayList<>();
    static String[][] find_all_arrangements(int n) {
        int[] board = new int[n]; // representing board as int array of row and value is the column of queen
        for (int i = 0; i < board.length; i++) {
            board[i] = i;
        }
        helper(board, 0);
        return finalRes.toArray(new String[0][0]);

    }

    static void helper(int[] board, int i) {
        if (i == board.length) {
            addToList(board);
            return;
        }

        for (int j = i; j < board.length; j++) {
            if (ifValid(board, i, board[j])) {
                swap(board, i, j);
                helper(board, i + 1);
                swap(board, j, i);
            }
        }
    }

    static void swap(int[] board, int i, int j) {
        int t = board[i];
        board[i] = board[j];
        board[j] = t;

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
    static boolean ifValid(int[] board, int i, int j) {
        for (int row = 0; row < i; row++) {
            // if (board[row] == j) {
            // return false;
            // }
            if (Math.abs(i - row) == Math.abs(j - board[row])) {
                return false;
            }
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

    static void addToList(int[] board) {
        String[] tm = new String[board.length];

        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                if (board[i] == j) {
                    sb.append("q");
                } else {
                    sb.append("-");
                }
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
