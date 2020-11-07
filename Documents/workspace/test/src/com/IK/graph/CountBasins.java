package com.IK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class CountBasins {
    List<Integer> result = new ArrayList<>();
    static int basin_index = 0;
    static List<Integer> sink = new ArrayList<>();
    public static List<Integer> find_basins(List<List<Integer>> matrix) {
        // Write your code here
        int n = matrix.size();
        int m = matrix.get(0).size();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], -1);
        }
        usingGraph(n, m, grid, matrix);
        greedy(matrix, grid);
        sink = countingSort(sink, n * m);
        // Collections.sort(sink);
        return sink;
    }

    public static List<Integer> countingSort(List<Integer> input, int n) {
        List<Integer> result = new ArrayList<>();
        int[] counting = new int[n];
        for (int i : input) {
            counting[i - 1]++;
        }

        for (int i = 0; i < n; i++) {
            if (counting[i] != 0) {
                for (int j = 0; j < counting[i]; j++) {
                    result.add(i + 1);
                }
            }

        }
        return result;

    }
    public static void usingGraph(int n, int m, int[][] grid,List<List<Integer>> matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == -1) {
                    dfs(i, j, matrix, grid);
                }
            }
        }
    }
    public static void dfs(int i, int j, List<List<Integer>> matrix, int[][] grid) {
        int min_x = i;
        int min_y = j;
        int[][] xy = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int n = 0; n < 4; n++) {

            int x = i + xy[n][0];
            int y = j + xy[n][1];
            if (x < grid.length && x >= 0 && y < grid[0].length && y >= 0) {
                if (matrix.get(x).get(y) < matrix.get(min_x).get(min_y)) {
                    min_x = x;
                    min_y = y;
                }
            }

        }

        if (min_x == i && min_y == j) {// this is sink
            sink.add(1);
            grid[i][j] = basin_index++;
            return;
        }
        if (grid[min_x][min_y] == -1) {
            dfs(min_x, min_y, matrix, grid);
        }
        grid[i][j] = grid[min_x][min_y];
        sink.set(grid[i][j], sink.get(grid[i][j]) + 1);

    }

    static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static void greedy(List<List<Integer>> matrix, int[][] grid) {
        TreeMap<Integer, List<Pair>> height = new TreeMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                List<Pair> l = height.get(matrix.get(i).get(j));
                if (!height.containsKey(matrix.get(i).get(j))) {
                    l = new ArrayList<>();
                }
                l.add(new Pair(i, j));
                height.put(matrix.get(i).get(j), l);

            }
        }
        int basin_index = 0;
        for (int k : height.keySet()) {
            for (Pair p : height.get(k)) {
                if (grid[p.i][p.j] == -1) {
                    // sink
                    sink.add(1);
                    grid[p.i][p.j] = basin_index++;
                }
                if (p.i + 1 < grid.length && grid[p.i + 1][p.j] == -1) {
                    grid[p.i + 1][p.j] = grid[p.i][p.j];
                    sink.set(grid[p.i + 1][p.j], sink.get(grid[p.i + 1][p.j]) + 1);
                }
                if (p.i - 1 >= 0 && grid[p.i - 1][p.j] == -1) {
                    grid[p.i - 1][p.j] = grid[p.i][p.j];
                    sink.set(grid[p.i - 1][p.j], sink.get(grid[p.i - 1][p.j]) + 1);
                }
                if (p.j + 1 < grid[0].length && grid[p.i][p.j + 1] == -1) {
                    grid[p.i][p.j + 1] = grid[p.i][p.j];
                    sink.set(grid[p.i][p.j + 1], sink.get(grid[p.i][p.j + 1]) + 1);
                }
                if (p.j - 1 >= 0 && grid[p.i][p.j - 1] == -1) {
                    grid[p.i][p.j - 1] = grid[p.i][p.j];
                    sink.set(grid[p.i][p.j - 1], sink.get(grid[p.i][p.j - 1]) + 1);
                }

            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        find_basins(Arrays.asList(Arrays.asList(1, 5, 2), Arrays.asList(2, 4, 7), Arrays.asList(3, 6, 9)));
    }

}
