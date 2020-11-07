package com.IK.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EqualSubSetSumPartition {
    List<List<Integer>> r = new ArrayList<>();
    public static List<Boolean> equalSubSetSumPartition(List<Integer> s) {
        // Write your code here
        int negative_nos_sum = 0;
        int positive_nos_sum = 0;
        List<Boolean> result = new ArrayList<>();
        int n = s.size();

        // Calculate min and max sum
        for (int i = 0; i < n; i++) {
            if (s.get(i) < 0) {
                negative_nos_sum += s.get(i);
            } else {
                positive_nos_sum += s.get(i);
            }
        }

        if ((negative_nos_sum + positive_nos_sum) % 2 != 0)
            return result;
        int target = (negative_nos_sum + positive_nos_sum) / 2;
        //DP approach
        //The problem is complex because of negative number, otherwise it would have been easy
        DefaultHashMap<Integer,Boolean> dp1[] = new DefaultHashMap[n] ;
        for(int i=0; i<n; i++) {
            dp1[i] = new DefaultHashMap<>(false);
        }
        dp1[0].put(s.get(0), true);
        for(int i=1; i<n; i++) {
            for(int j=negative_nos_sum; j<=positive_nos_sum; j++) {
                if(s.get(i)==j) {
                    dp1[i].put(j, true);
                }else {
                    dp1[i].put(j, dp1[i-1].get(j));
                    if(j-s.get(i)>=negative_nos_sum) {
                        dp1[i].put(j, dp1[i].get(j) || dp1[i-1].get(j-s.get(i)));
                    }
                }
            }
        }
        
        //building result
        if(!dp1[n-1].get(target)) {
            return result;
        }
        result = new ArrayList<>(Arrays.asList(new Boolean[n]));
        Collections.fill(result, Boolean.FALSE);
        int cnt = 0;
        for(int i=n-1; i>=0; i--) {
            if(i>0) {
                if(dp1[i].get(target) && !dp1[i-1].get(target)) {
                    target = target - s.get(i);
                    result.set(i,Boolean.TRUE);
                    cnt++;
                    if(target==0) {
                        break;
                    }
                }
            }else {
                result.set(i, true);
                cnt++;
            }
            
        }
        
        //
        return cnt==n?new ArrayList<>():result;
        
        ///below is recursive approach.
        /*Set<Integer> set = new HashSet<>();
        Set<Integer> dp = new HashSet<>();
       

        if (helper(s, 0, target, set, 0, dp)) {
            for (int i = 0; i < n; i++) {
                if (set.contains(i)) {
                    result.add(true);
                } else {
                    result.add(false);
                }
            }
        }

        return result;
        */
    }

    // basically dp will only be useful to detect false. When true, it returns to
    // parent. It is only when
    // false, it will explore different cases. Thats why we can directly return
    // without worrying about result
    // set because the return is false so it wont be in result set.
    // I still do not understand why without i, it works as that is also one
    // information should be mentioned in
    // repeated problems.
    public static boolean helper(List<Integer> s, int c, int sum, Set<Integer> set, int i, Set<Integer> dp) {
        if (c == sum && set.size() > 0 && set.size() < s.size()) {
            return true;
        }
        if(i==s.size()) {
            return false;
        }
        if (dp.contains(c)) {
            return false;
        }

        set.add(i); // include
        boolean isEqual = helper(s, c + s.get(i), sum, set, i + 1, dp);
        if(!isEqual) {
            set.remove(i);// exclude
            isEqual = helper(s, c, sum, set, i + 1, dp);
        }
        dp.add(c);
        return isEqual;

    }
    public static class DefaultHashMap<K,V> extends HashMap<K,V>{
        protected V defaultValue;
        public DefaultHashMap(V defaultValue){
            this.defaultValue = defaultValue;
        }
        @Override
        public V get(Object k) {
            return containsKey(k) ? super.get(k) : defaultValue;
        }
    }
    public static void main(String[] args) {
        equalSubSetSumPartition(Arrays.asList(10, -3, 7, 2, 1, 3));
        // TODO Auto-generated method stub

    }

}
