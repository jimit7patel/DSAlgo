package com.IK.recursion;

import java.util.ArrayList;
import java.util.List;

public class EvaluateExpression {

    /*
     * Complete the function below.
     */
    static List<String> out = new ArrayList<>();

    static String[] generate_all_expressions(String s, long target) {
        helper(s, 0, 0, "", 0, 0, target);
        return out.toArray(new String[0]);
    }

    static void helper(String s, long prevOp, long curOp, String cur, long cSum, int i, long target) {
        if (i == s.length()) {
            if (cSum == target) {
                out.add(cur);
            }
            return;
        }

        for (int idx = i; idx < s.length(); idx++) {
            String t = s.substring(i, idx + 1);
            curOp = Long.parseLong(t);
            if (cur.length() == 0) {
                helper(s, curOp, 0, t, curOp, idx + 1, target);
            } else {
                helper(s, curOp, 0, cur + "+" + t, cSum + curOp, idx + 1, target);

                helper(s, prevOp * curOp, 0, cur + "*" + t, cSum - prevOp + (curOp * prevOp), idx + 1, target);
            }

        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        generate_all_expressions("050505", 5);
    }

}
