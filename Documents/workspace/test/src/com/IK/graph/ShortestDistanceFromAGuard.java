package com.IK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ShortestDistanceFromAGuard {

    /*
     * Complete the 'find_shortest_distance_from_a_guard' function below.
     *
     * The function accepts the 2D CHARACTER ARRAY. Returns 2D INTEGER ARRAY.
     */
    static class Node {
        int i;
        int j;
        int d;

        Node(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }

    static List<List<Integer>> dist = new ArrayList<>();

    public static List<List<Integer>> find_shortest_distance_from_a_guard(List<List<Character>> grid) {
        // Write your code here

        int row = grid.size() - 1;
        int col = grid.get(0).size() - 1;
        Deque<Node> q = new LinkedList<>();

        for (int i = 0; i <= row; i++) {
            dist.add(new ArrayList<Integer>());
            for (int j = 0; j <= col; j++) {
                if (grid.get(i).get(j) == 'G') {
                    q.addLast(new Node(i, j, 0));
                }
                dist.get(i).add(-1);
            }
        }

        int[][] xy = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        boolean[][] visited = new boolean[row + 1][col + 1];

        while (!q.isEmpty()) {
            Node n = q.removeFirst();
            dist.get(n.i).set(n.j, n.d);
            for (int i = 0; i < 4; i++) {
                int x = n.i + xy[i][0];
                int y = n.j + xy[i][1];

                if (ifValid(x, y, row, col) && !visited[x][y] && grid.get(x).get(y) == 'O') {
                    visited[x][y] = true;
                    q.addLast(new Node(x, y, n.d + 1));
                }
            }
        }

        return dist;

    }

    public static boolean ifValid(int x, int y, int n, int m) {
        if (x < 0 || x > n || y < 0 || y > m) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        find_shortest_distance_from_a_guard(Arrays.asList(Arrays.asList('O', 'O', 'O', 'O', 'G'), Arrays.asList('O', 'W', 'W', 'O', 'O'), Arrays.asList('O', 'O', 'O', 'W', 'O'),
                Arrays.asList('G', 'W', 'W', 'W', 'O'), Arrays.asList('O', 'O', 'O', 'O', 'G')));

    }

}
