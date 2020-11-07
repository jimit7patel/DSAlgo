package com.IK.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SnakeAndLadder {

    static int minNumberOfRolls(int n, List<Integer> moves) {

        Deque<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.addLast(0);
        visited[0] = true;
        int[] dist = new int[n];
        while (!q.isEmpty()) {
            int s = q.removeFirst();
            if (s == n - 1) {
                return dist[s];
            }
            for (int i = 1; i <= 6; i++) {
                int next = s + i;
                if (next < n) {
                    if (moves.get(next) != -1) {
                        next = moves.get(next);
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        dist[next] = dist[s] + 1;
                        q.addLast(next);
                    }

                } else {
                    break;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        minNumberOfRolls(20, Arrays.asList(-1, 18, -1, -1, -1, -1, -1, -1, 2, -1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1));
    }

}
