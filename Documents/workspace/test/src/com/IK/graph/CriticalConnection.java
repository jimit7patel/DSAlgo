package com.IK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnection {
    /*
     * Complete the function findCriticalConnections The function takes integers
     * noOfServers, noOfConnections and 2D integer array as parameters. Returns a 2D
     * integer array.
     */
    private static List<Integer> adj[];
    private static int timestamp = 0;
    private static List<List<Integer>> res;

    public static List<List<Integer>> findCriticalConnections(int noOfServers, int noOfConnections, List<List<Integer>> connections) {
        // Write your code here

        createAdjList(noOfServers, noOfConnections, connections);
        boolean visited[] = new boolean[noOfServers];
        int[] arrival = new int[noOfServers];
        int[] lowest_time = new int[noOfServers];
        res = new ArrayList<>();
        int[] parent = new int[noOfServers];
        Arrays.fill(parent, -1);
        for (int i = 0; i < noOfServers; i++) {
            if (!visited[i]) {
                dfs(visited, arrival, lowest_time, i, parent);
            }
        }
        return res.isEmpty() ? Arrays.asList(Arrays.asList(-1, -1)) : res;
    }

    public static void dfs(boolean[] visited, int[] arrival, int[] lowest_time, int u, int[] parent) {

        visited[u] = true;
        arrival[u] = lowest_time[u] = timestamp++;
        for (int v : adj[u]) {
            // if not visited then find the lowest arrival time from the rooted tree back to
            // ancestors of u
            if (!visited[v]) {
                parent[v] = u;
                dfs(visited, arrival, lowest_time, v, parent);
                lowest_time[u] = Math.min(lowest_time[u], lowest_time[v]); // set the lowest time for u

                // check if there is a back edge to ancestors of u
                if (lowest_time[v] > arrival[u]) {
                    // back edge is below u, so you can not reach to u if broken, hence this is
                    // critical edge
                    res.add(Arrays.asList(u, v));
                }
            } else if (parent[u] != v) {
                // if it is not parent, then it is a back edge from u to the ancestors.
                // Hence, update the lowest_time of u
                lowest_time[u] = Math.min(lowest_time[u], arrival[v]);
            }
        }

    }

    public static void createAdjList(int noOfServers, int noOfConnections, List<List<Integer>> connections) {
        adj = new ArrayList[noOfServers];
        for (int i = 0; i < noOfServers; i++) {
            adj[i] = new ArrayList<>();
        }
        for (List<Integer> c : connections) {
            int a = c.get(0);
            int b = c.get(1);
            adj[a].add(b);
            adj[b].add(a);
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        findCriticalConnections(4, 4, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(2, 0), Arrays.asList(1, 3)));
    }

}
