package com.epi;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/course-schedule/submissions/ There are a total
 * of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * Example 1:
 * 
 * Input: 2, [[1,0]] Output: true Explanation: There are a total of 2 courses to
 * take. To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * 
 * Input: 2, [[1,0],[0,1]] Output: false Explanation: There are a total of 2
 * courses to take. To take course 1 you should have finished course 0, and to
 * take course 0 you should also have finished course 1. So it is impossible.
 * Note:
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented. You may
 * assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {
    //below is BFS using topological sort

    public boolean canFinish(int numCourses, int[][] prerequisites){
        if(numCourses ==1 || prerequisites.length == 0 || prerequisites[0].length == 0)
            return true;

        int[] indegree = new int[numCourses];
        int coursesRemaining = numCourses;

        for(int i=0; i< prerequisites.length; i++){
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i< indegree.length; i++){
            if(indegree[i]==0){
                queue.add(i);
                coursesRemaining--;
            }
        }

        while(!queue.isEmpty()){
            int k = queue.remove();
            for(int i=0; i< prerequisites.length; i++){
                if(prerequisites[i][1]==k){
                    indegree[prerequisites[i][0]]--;
                    if(indegree[prerequisites[i][0]] == 0){
                        coursesRemaining--;
                        queue.add(prerequisites[i][0]);
                    }
                }

            }

        }
        return coursesRemaining == 0;
    }



    //Below is DFS approach which detects cycle
    /*public boolean canFinish(int numCourses, int[][] prerequisites) {

        Set<Integer> visted = new HashSet<Integer>();
        Map<Integer,List<Integer>> pair = new HashMap<>();
        boolean[] done = new boolean[numCourses];

        int count = 0;
        for(int i=0; i< prerequisites.length; i++){
            pair.computeIfAbsent(prerequisites[i][1],k -> new ArrayList<Integer>())
                .add(prerequisites[i][0]);
        }

        for(int row=0; row <prerequisites.length; row++){
            if(done[prerequisites[row][0]])
                continue;
            if(!helper(prerequisites[row][0],pair,visted,done)){
                return false;
            }
        }
        return true;
    }

    public boolean helper(int key, Map<Integer,List<Integer>> pair, Set<Integer> visted, boolean[] done){
        if(done[key])
            return true;
        if(visted.contains(key))
            return false;

        if(!pair.containsKey(key)){
            return true;
        }
        visted.add(key);
        for(int a: pair.get(key)){
            if(!helper(a,pair,visted,done))
                return false;
        }
        visted.remove(key);
        done[key]=true;
        return true;
    }*/
    public static void main(String[] args) {

    }
}
