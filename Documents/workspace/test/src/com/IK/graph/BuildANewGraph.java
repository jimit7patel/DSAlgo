package com.IK.graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BuildANewGraph {
    static class Node {
        Integer val;
        Vector<Node> neighbours = new Vector<>(0);

        Node(Integer _val) {
            val = _val;
            neighbours.clear();
        }
    };
    static HashMap<Integer, Node> hm = new HashMap<>();

    static Node build_other_graph(Node node) {
        // dfs(node);
        bfs(node);
        return hm.get(1);
    }

    static void dfs(Node cur) {
        hm.put(cur.val, new Node(cur.val));
        int n = cur.neighbours.size();
        for (int i = 0; i < n; i++) {
            Node next = cur.neighbours.get(i);
            if (!hm.containsKey(next.val)) {
                dfs(next);
            }
            hm.get(next.val).neighbours.add(hm.get(cur.val));

        }
    }

    static void bfs(Node c) {
        Deque<Node> q = new LinkedList<>();
        q.addLast(c);
        hm.put(c.val, new Node(c.val));
        while (!q.isEmpty()) {
            Node cur = q.removeFirst();
            int n = cur.neighbours.size();
            for (int i = 0; i < n; i++) {
                Node next = cur.neighbours.get(i);
                if (!hm.containsKey(next.val)) {
                    hm.put(next.val, new Node(next.val));
                    q.addLast(next);
                }
                hm.get(next.val).neighbours.add(hm.get(cur.val));

            }
        }
    }
}
