package com.IK.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ShortedDistanceGridKeysAndLock {

    /*
     * Complete the function below.
     */
    static final int[][] MOVES = new int[][] { { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 } };

    static int[][] find_shortest_path(String[] grid) {
        char[][] charGrid = convertCharacterArray(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (charGrid[i][j] == '@')
                    return BFS(i, j, charGrid);
            }
        }

        return new int[][] {};
    }

    static int[][] BFS(int i, int j, char[][] charGrid) {

        Deque<Point> q = new LinkedList<>();
        int keyCount = ('j' - 'a') + 1;
        int maxKey = 1 << keyCount;

        boolean[][][] visited = new boolean[charGrid.length][charGrid[0].length][maxKey];
        Point start = new Point(i, j, 0, null);
        q.addLast(start);
        visited[i][j][0] = true;
        while (!q.isEmpty()) {
            Point p = q.removeFirst();
            for (int[] m : MOVES) {
                int nextX = m[0] + p.x;
                int nextY = m[1] + p.y;
                if (!ifValid(nextX, nextY, charGrid.length, charGrid[0].length)) {
                    continue;
                }
                char c = charGrid[nextX][nextY];
                if (c == '#')
                    continue;

                Point n = new Point(nextX, nextY, p.keys, p);

                if (c == '+') {
                    return buildAnswer(n);
                }

                if (Character.isLowerCase(c)) {
                    int k = c - 'a';
                    n.keys = p.keys | (1 << k);
                }
                if (visited[nextX][nextY][n.keys]) {
                    continue;
                }
                if (Character.isUpperCase(c)) {
                    int k = c - 'A';
                    if ((n.keys & 1 << k) == 0) {
                        continue;
                    }
                }
                visited[nextX][nextY][n.keys] = true;
                q.addLast(n);
            }
        }
        return new int[][] {};
    }

    static int[][] buildAnswer(Point p) {
        List<int[]> ans = new ArrayList<>();
        while (p != null) {
            int[] t = new int[] { p.x, p.y };
            ans.add(t);
            p = p.parent;
        }
        Collections.reverse(ans);
        return ans.toArray(new int[ans.size()][2]);
    }

    static boolean ifValid(int x, int y, int rows, int cols) {
        if (x >= 0 && y >= 0 && x < rows && y < cols)
            return true;
        else
            return false;
    }

    static class Point {
        int x, y, keys;
        Point parent;

        Point(int x, int y, int keys, Point parent) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.parent = parent;
        }

    }

    static char[][] convertCharacterArray(String[] grid) {
        char[][] charGrid = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                charGrid[i][j] = grid[i].charAt(j);
            }
        }
        return charGrid;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        print(find_shortest_path(new String[] { "...B",
                ".b#.",
        "@#+." }));

    }

    static void print(int[][] x) {
        for (int[] is : x) {
            System.out.println(is[0] + "..." + is[1]);
        }
    }

}
