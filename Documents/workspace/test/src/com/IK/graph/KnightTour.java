package com.IK.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class KnightTour {

    /*
     * Complete the function below.
     */
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {
        // Write your code here.

        Deque<Point> q = new LinkedList<>();
        Point p = new Point(start_row, start_col);
        int[][] visited = new int[rows][cols];
        visited[start_row][start_col] = 1;
        q.addLast(p);
        while (!q.isEmpty()) {
            Point t = q.removeFirst();
            for (Point n : findNeighbours(t, rows, cols, visited)) {
                visited[n.x][n.y] = visited[t.x][t.y] + 1;
                if (n.x == end_row && n.y == end_col) {
                    return visited[n.x][n.y] - 1;
                }
                q.addLast(n);
            }
        }
        return -1;
    }

    static List<Point> findNeighbours(Point p, int rows, int cols, int[][] visited) {
        int x = p.x;
        int y = p.y;
        List<Point> ne = new ArrayList<>();

        if (ifValidPosition(x + 2, y + 1, rows, cols) && visited[x + 2][y + 1] == 0) {
            ne.add(new Point(x + 2, y + 1));
        }
        if (ifValidPosition(x + 2, y - 1, rows, cols) && visited[x + 2][y - 1] == 0) {
            ne.add(new Point(x + 2, y - 1));
        }
        if (ifValidPosition(x - 2, y + 1, rows, cols) && visited[x - 2][y + 1] == 0) {
            ne.add(new Point(x - 2, y + 1));
        }
        if (ifValidPosition(x - 2, y - 1, rows, cols) && visited[x - 2][y - 1] == 0) {
            ne.add(new Point(x - 2, y - 1));
        }
        if (ifValidPosition(x + 1, y + 2, rows, cols) && visited[x + 1][y + 2] == 0) {
            ne.add(new Point(x + 1, y + 2));
        }
        if (ifValidPosition(x + 1, y - 2, rows, cols) && visited[x + 1][y - 2] == 0) {
            ne.add(new Point(x + 1, y - 2));
        }
        if (ifValidPosition(x - 1, y + 2, rows, cols) && visited[x - 1][y + 2] == 0) {
            ne.add(new Point(x - 1, y + 2));
        }
        if (ifValidPosition(x - 1, y - 2, rows, cols) && visited[x - 1][y - 2] == 0) {
            ne.add(new Point(x - 1, y - 2));
        }
        return ne;
    }

    static boolean ifValidPosition(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        find_minimum_number_of_moves(5, 5, 1, 1, 4, 4);
    }

}
