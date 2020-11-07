package lc.topliked.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class EvaluateDivision {
    /*
     * https://leetcode.com/problems/evaluate-division/submissions/
     * use graph
     *  a -> b
     * -v b -> a 1/v
     *
     *
     *
     */

    Map<String, Map<String, Double>> graph = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        buildGraph(equations, values);
        return returnQueries(queries);

    }

    public void buildGraph(List<List<String>> equations, double[] values) {
        int i = 0;
        for (List<String> e : equations) {
            String s = e.get(0);
            String d = e.get(1);
            double v = values[i++];

            graph.computeIfAbsent(s, k -> new HashMap<>()).put(d, v);
            graph.computeIfAbsent(d, k -> new HashMap<>()).put(s, 1 / v);
        }

    }

    public double[] returnQueries(List<List<String>> queries) {
        double[] result = new double[queries.size()];
        int i = 0;
        for (List<String> q : queries) {
            String s = q.get(0);
            String d = q.get(1);
            result[i++] = dfs(s, d, 1, new HashSet<>());
        }
        return result;
    }

    public double dfs(String s, String d, double v, Set<String> visited) {
        if (visited.contains(s))
            return -1;
        if (graph.containsKey(s)) {
            if (s.equals(d)) {
                return 1;
            }
            double ans = -1;
            visited.add(s);
            for (Map.Entry<String, Double> it : graph.get(s).entrySet()) {
                if (d.equals(it.getKey())) {
                    return v * it.getValue();
                } else {
                    ans = dfs(it.getKey(), d, v * it.getValue(), visited);
                    if (ans != -1)
                        return ans;
                }
            }
            visited.remove(s);

        }
        return -1;

    }

    public static void main(String[] args) {

        EvaluateDivision sol = new EvaluateDivision();
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = { 2.0, 3.0 };
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        double[] res = sol.calcEquation(equations, values, queries);
        Arrays.stream(res).forEach(i -> {
            System.out.println(i);
        });
    }
}
