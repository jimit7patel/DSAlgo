package com.IK.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KnightTour {


    static long numPhoneNumbers(int startdigit, int phonenumberlength) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // 0
        ArrayList<Integer> t = new ArrayList<>();
        t.add(4);
        t.add(6);
        map.put(0, t);

        // 1
        List<Integer> t1 = new ArrayList<>();
        t1.add(6);
        t1.add(8);
        map.put(1,t1);

        // 2
        List<Integer> t2 = new ArrayList<>();
        t2.add(7);
        t2.add(9);
        map.put(2,t2);
        // 3
        List<Integer> t3 = new ArrayList<>();
        t3.add(4);
        t3.add(8);
        map.put(3,t3);
        // 4
        List<Integer> t4 = new ArrayList<>();
        t4.add(3);
        t4.add(9);
        t4.add(0);
        map.put(4,t4);

        // 5
        List<Integer> t5 = new ArrayList<>();
        map.put(5, t5);
        //listOfNext.add(new ArrayList<Integer>());

        // 6
        List<Integer> t6 = new ArrayList<>();
        t6.add(1);
        t6.add(7);
        t6.add(0);
        map.put(6,t6);

        // 7
        List<Integer> t7 = new ArrayList<>();
        t7.add(2);
        t7.add(6);
        map.put(7,t7);
        // 8

        List<Integer> t8 = new ArrayList<>();
        t8.add(1);
        t8.add(3);
        map.put(8,t8);

        // 9
        List<Integer> t9 = new ArrayList<>();
        t9.add(4);
        t9.add(2);
        map.put(9,t9);

        long dp[][] = new long[phonenumberlength][10];
        dp[0][startdigit] = 1;

        for (int i = 1; i < phonenumberlength; i++) {
            for (int n = 0; n < 10; n++) {
                for (int a : map.get(n)) {
                    dp[i][n] += dp[i - 1][a];
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 10; i++) {
            count += dp[phonenumberlength - 1][i];
        }

        return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        numPhoneNumbers(1, 2);
    }

}
