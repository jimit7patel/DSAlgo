package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
/*
 * https://github.com/OutcoSF/outcode-41-java-jimit7patel/blob/master/warm_up_solutions/01_combinations.md
 * 
 * */
public class Permutations2 {

    public static List<List<Integer>> permutations(int k, int n) {

        List<Integer> A = new ArrayList<>();
        IntStream.range(1, n + 1).forEach(i -> A.add(i));
        //return helper(2, A);
        List<List<Integer>> res = new ArrayList<>();
         helper(2,new ArrayList<>(),A,res);
         return res;
    }
    
    public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (n <= 0 || n < k)
          return result;

        ArrayList<Integer> item = new ArrayList<Integer>();
        dfs(n, k, 1, item, result); // because it need to begin from 1

        return result;
      }

      private static void dfs(int n, int k, int start, ArrayList<Integer> item,
          ArrayList<ArrayList<Integer>> result) {


        if (item.size() == k) {
          result.add(new ArrayList<Integer>(item));
          return;
        }

        if(start > n) {
          return;
        }

          dfs(n, k, start + 1, item, result);
          item.add(start);
          dfs(n, k, start + 1, item, result);
          item.remove(item.size() - 1);


      }
    private static List<List<Integer>> helper(int k, List<Integer> N) {

        if (k <= 0 || k > N.size()) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        if (k == 1 || k == N.size()) {
            for (int i : N) {
                res.add(new ArrayList(Arrays.asList(i)));
            }
            return res;
        }
        for (int i = 0; i < N.size(); i++) {
            List<List<Integer>> tmp = helper(k - 1, new ArrayList(N.subList(i + 1, N.size())));
            for (List<Integer> t : tmp) {
                t.add(N.get(i));
            }
            res.addAll(tmp);
        }
        return res;

    }
    
    private static void helper(int k, List<Integer> A, List<Integer> N,List<List<Integer>> res) {
        if (k == 1 || k == N.size()) {
            for (int i : N) {
                List<Integer> tmp = new ArrayList(A);
                tmp.add(i);
                res.add(tmp);
            }
            return;
        }
        for (int i = 0; i < N.size(); i++) {
            List<Integer> tmp = new ArrayList(A);
            tmp.add(N.get(i));
            helper(k - 1, tmp, new ArrayList(N.subList(i + 1, N.size())), res);
        }
        return;

    }   


    public static void main(String[] args) {
        System.out.println(Permutations2.permutations(2, 4));
    }
}
