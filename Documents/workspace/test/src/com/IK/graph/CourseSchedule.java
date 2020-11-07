package com.IK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CourseSchedule {

    static int timestamp = 0;
    static List<Integer> out = new ArrayList<>();

    static List<Integer> course_schedule(int n, List<List<Integer>> prerequisites) {

        boolean visited[] = new boolean[n];
        List<Integer> adj[] = new ArrayList[n];
        int indegree[] = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int dest[] = new int[n];
        for (List<Integer> l : prerequisites) {
            int src = l.get(0);
            int des = l.get(1);
            adj[des].add(src);
            indegree[src] += 1;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(adj, i, visited, dest)) {
                    return Arrays.asList(-1);
                }
            }
        }
        Collections.reverse(out);
        // return out;
        return findSchedule(adj, indegree);
    }

    static boolean dfs(List<Integer> adj[], int n, boolean visited[], int dest[]) {
        visited[n] = true;
        timestamp++;
        for (int nei : adj[n]) {
            if (!visited[nei]) {
                if (dfs(adj, nei, visited, dest)) {
                    return true;
                }
            } else {
                if (dest[nei] == 0) {
                    return true;
                    // cycle
                }
            }
        }
        dest[n] = timestamp++;
        out.add(n);
        return false;
    }

    // calculate indegrees
    static List<Integer> findSchedule(List<Integer> adj[], int[] indegree) {
        List<Integer> res = new ArrayList<>();
        List<Integer> no_zero_indegree = new ArrayList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                no_zero_indegree.add(i);
            }
        }

        while (!no_zero_indegree.isEmpty()) {
            int j = no_zero_indegree.remove(0);
            res.add(j);
            for (int i : adj[j]) {
                indegree[i] -= 1;
                if (indegree[i] == 0) {
                    no_zero_indegree.add(i);
                }
            }
        }

        return res.size() == adj.length ? res : Arrays.asList(-1);

    }



public static void main(String[] args) {
    // TODO Auto-generated method stub
    course_schedule(2, Arrays.asList(Arrays.asList(1, 0)));
}

}
