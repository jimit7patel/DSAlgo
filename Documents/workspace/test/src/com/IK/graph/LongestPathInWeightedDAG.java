package com.IK.graph;

import java.util.ArrayList;
import java.util.List;

public class LongestPathInWeightedDAG {
    static List<Integer> topo = new ArrayList<>();

    static class Node {
        int i;
        int w;

        Node(int i, int w) {
            this.i = i;
            this.w = w;
        }
    }

    static int[] find_longest_path(int dag_nodes, int[] dag_from, int[] dag_to, int[] dag_weight, int from_node, int to_node) {
        ArrayList<Node>[] adj = new ArrayList[dag_nodes + 1];
        boolean[] visited = new boolean[dag_nodes + 1];

        for (int i = 1; i <= dag_nodes; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < dag_from.length; i++) {
            int s = dag_from[i];
            int d = dag_to[i];
            int w = dag_weight[i];
            adj[s].add(new Node(d, w));
        }

        buildTopoSort(from_node, adj, visited);
        long[] lengthArray = new long[dag_nodes + 1];
        int[] parent = new int[dag_nodes + 1];
        // Collections.reverse(topo);
        for (int i = 0; i < topo.size(); i++) {
            int from = topo.get(i);
            for (Node to : adj[from]) {
                if (lengthArray[to.i] < to.w + lengthArray[from]) {
                    lengthArray[to.i] = to.w + lengthArray[from];
                    parent[to.i] = from;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        path.add(to_node);
        while (parent[to_node] != 0) {
            path.add(0, parent[to_node]);
            to_node = parent[to_node];
        }
        return path.stream().mapToInt(Integer::intValue).toArray();

    }

    static void buildTopoSort(int c, ArrayList<Node>[] adj, boolean[] visited) {
        visited[c] = true;
        for (Node n : adj[c]) {
            if (!visited[n.i]) {
                buildTopoSort(n.i, adj, visited);
            }
        }
        topo.add(0, c);

    }
    public static void main(String[] args) {
        find_longest_path(3, new int[] { 3, 2 }, new int[] { 2, 1 }, new int[] { 2000000000, 2000000000 }, 3, 1);
        // TODO Auto-generated method stub

    }

}
