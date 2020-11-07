/*
 * Given an encoded string, return it's decoded string. The encoding rule is:
 * k[encoded_string], where the encoded_string inside the square brackets is
 * being repeated exactly k times. Note that k is guaranteed to be a positive
 * integer. You may assume that the input string is always valid; No extra white
 * spaces, square brackets are well-formed, etc. Furthermore, you may assume
 * that the original data does not contain any digits and that digits are only
 * for those repeat numbers, k. For example, there won't be input like 3a or
 * 2[4]. Examples: s = "3[a]2[bc]", return "aaabcbc". s = "3[a2[c]]", return
 * "accaccacc". s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

package com.epi;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

    private static String decodeString(String s) {

        Deque<Integer> count = new ArrayDeque<>();
        Deque<String> result = new ArrayDeque<>();

        int i = 0;
        String finalResult = "";
        while (i < s.length()) {

            if (s.charAt(i) >= '1' && s.charAt(i) <= '9') { // when number
                int start = i;
                while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
                    i++;
                count.offer(Integer.parseInt(s.substring(start, i + 1)));
            } else if (s.charAt(i) == '[') { // when [
            } else if (s.charAt(i) == ']') { // to do
                String t = result.removeLast();

                int cnt = count.removeLast();
                if (s.charAt(i - 1) == ']') {
                    t = t + finalResult;
                }

                String output = "";
                for (int k = 0; k < cnt; k++) {
                    output = output + t;
                }
                if (s.charAt(i - 1) == ']') {
                    finalResult = output;
                } else {
                    finalResult = finalResult + output;
                }

            } else if (count.isEmpty()) {
                finalResult = finalResult + s.charAt(i);
            } else {

                if (count.size() == result.size())
                    result.offer(result.removeLast() + s.charAt(i));
                else
                    result.offer(String.valueOf(s.charAt(i)));
            }
            i++;

        }

        return finalResult;
    }

    private static String anotherDecodeString(String s) {

        Deque<Integer> count = new ArrayDeque<>();
        Deque<String> result = new ArrayDeque<>();

        int i = 0;
        while (i < s.length()) {

            if (s.charAt(i) >= '1' && s.charAt(i) <= '9') { // when number
                int start = i;
                while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
                    i++;
                count.push(Integer.parseInt(s.substring(start, i + 1)));
            } else if (s.charAt(i) == '[') { // when [
                result.push("");
            } else if (s.charAt(i) == ']') { // to do
                String t = result.pop();
                int cnt = count.pop();
                String output = "";
                for (int k = 0; k < cnt; k++) {
                    output = output + t;
                }
                if (!result.isEmpty())
                    result.push(result.pop() + output);
                else
                    result.push(output);

            } else {
                result.push(result.pop()+s.charAt(i));
            }
            i++;

        }

        return result.pop();
    }

    public static void main(String[] args) {

        String input[] = { "3[a2[c]]",
                "3[a]2[bc]",
        "2[abc]3[cd]ef" };

        String output[] = { "accaccacc", "aaabcbc", "abcabccdcdcdef" };

        for (int i = 0; i < 3; i++) {
            String actualoutput = decodeString(input[i]);
            if (actualoutput.equals(output[i]))
                System.out.println(actualoutput);
            else
                System.err.println("error in decoding");
        }
        for (int i = 0; i < 3; i++) {
            String actualoutput = anotherDecodeString(input[i]);
            if (actualoutput.equals(output[i]))
                System.out.println(actualoutput);
            else
                System.err.println("error in decoding");
        }


    }

}
