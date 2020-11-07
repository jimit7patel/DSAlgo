package com.epi;

/*
 * Target Practice 05 - Math
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

class Problems5 {

    /*
     * Problem: Trailing Zeros
     *
     * Prompt: Given a positive integer, return the number of trailing zeros are
     * present on the factorial of that number.
     *
     * Input: Integer >= 0 (n) Output: Integer
     *
     * Example: n = 10
     *
     * trailingZeros(n) = 2
     *
     * n = 27
     *
     * trailingZeros(n) = 6
     *
     * Explanation: 10! is 3628800, and so has 2 trailing zeros 27! is
     * 1088886945041835216068000000, and so has 6 trailing zeros
     *
     * Note: A straightforward way of solving this problem involves just taking the
     * factorial of your input and dividing by 10 until you run out of trailing
     * zeros. But what is the major problem with this?
     *
     * A good way to start solving this problem is by trying a lot of examples.
     */

    // Time Complexity:
    // Auxiliary Space Complexity:

    public static int trailingZeros(int n) {
        int total = 0;
        for (int i = 5; i < n; i = i * 5) {
            total = total + n / i;
        }
        return total;
    }

    /*
     * Problem: Group Anagrams
     *
     * Prompt: Given an array of words, return an array of arrays of words that
     * groups all anagrams together.
     *
     * Input: words [String] Output: [[String]]
     *
     * Example: words = ["eat", "tea", "tan", "bat", "ate", "tab", "ant", "tan",
     * "and"]
     *
     * groupAnagrams(words) =
     *
     * [ ["ate", "eat", "tea"], ["tan", "nat", "ant"], ["bat", "tab"], ["and"] ]
     *
     *
     *
     * Note: All inputs will be lowercase letters The order of groupings does not
     * matter The order of the words in the groupings does not matter The words many
     * not all be the same length
     *
     * 1) sorted 2) build key using char count 3) use prime number to letter mapping
     * and multiple each letter in word to form a key
     *
     */

    // Time Complexity:
    // Auxiliary Space Complexity:

    public static ArrayList<ArrayList<String>> groupAnagrams(String[] words) {

        int[] count = new int[26];
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Arrays.stream(words).forEach(w -> {
            Arrays.fill(count, 0);
            w.chars().forEach(j -> count[(char) j - 'a']++); // chars coverts to int stream of asci
            StringBuilder sb = new StringBuilder();
            IntStream.range(0, 26).forEach(i -> {
                sb.append("#");
                sb.append(count[i]);
            });
            map.computeIfAbsent(sb.toString(), s -> new ArrayList<>()).add(w);
        });
        return new ArrayList<>(map.values());
    }

    public static ArrayList<ArrayList<String>> groupAnagramsAnother(String[] words) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> anagramMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int anagramNum = computeAnagramNumber(word);
            if (!anagramMap.containsKey(anagramNum)) {
                anagramMap.put(anagramNum, new ArrayList<>(Arrays.asList(word)));
            } else {
                anagramMap.get(anagramNum).add(word);
            }
        }

        for (ArrayList<String> chunks : anagramMap.values()) {
            result.add(chunks);
        }

        return result;
    }

    private static HashMap<Character, Integer> chars = assignCharsToPrimes();

    private static HashMap<Character, Integer> assignCharsToPrimes() {
        int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
        char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        HashMap<Character, Integer> zip = new HashMap<>();

        for (int i = 0; i < primes.length; i++) {
            zip.put(chars[i], primes[i]);
        }

        return zip;
    }

    private static int computeAnagramNumber(String str) {
        int result = 1;
        for (int i = 0; i < str.length(); i++) {
            result *= chars.get(str.charAt(i));
        }
        return result;
    }

}

////////////////////////////////////////////////////////////
/////////////// DO NOT TOUCH TEST BELOW!!! ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main5 {
    // an interface to perform tests
    public interface Test {
        public boolean execute();
    }

    public static void main(String[] args) {

        int[] testCount = { 0, 0 };
        System.out.println("Trailing Zeros Tests");

        assertTest(testCount, "should work on first example input", new Test() {
            @Override
            public boolean execute() {
                return Problems5.trailingZeros(10) == 2;
            }
        });

        assertTest(testCount, "should work on second example input", new Test() {
            @Override
            public boolean execute() {
                return Problems5.trailingZeros(27) == 6;
            }
        });

        assertTest(testCount, "should work on zero", new Test() {
            @Override
            public boolean execute() {
                return Problems5.trailingZeros(0) == 0;
            }
        });

        assertTest(testCount, "should work on large input", new Test() {
            @Override
            public boolean execute() {
                return Problems5.trailingZeros(1037) == 257;
            }
        });

        assertTest(testCount, "should work on very large input", new Test() {
            @Override
            public boolean execute() {
                return Problems5.trailingZeros(79204102) == 19801020;
            }
        });

        // print the result of tests passed for a module
        System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

        // instantiate the testing of each module by resetting count and printing title
        // of module
        testCount[0] = 0;
        testCount[1] = 0;
        System.out.println("Group Anagrams Test");

        assertTest(testCount, "should work on first example input", new Test() {
            @Override
            public boolean execute() {
                ArrayList<ArrayList<String>> correctAnswer = new ArrayList<>();
                correctAnswer.add(new ArrayList<>(Arrays.asList("ate", "eat", "tea")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("tan", "nat", "ant")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("bat", "tab")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("and")));
                ArrayList<ArrayList<String>> test = Problems5.groupAnagrams(new String[] { "eat", "tea", "nat", "bat", "ate", "tab", "ant", "tan", "and" });
                return groupsMatching(correctAnswer, test);
            }
        });

        assertTest(testCount, "should work on empty input", new Test() {
            @Override
            public boolean execute() {
                ArrayList<ArrayList<String>> correctAnswer = new ArrayList<>();
                ArrayList<ArrayList<String>> test = Problems5.groupAnagrams(new String[] {});
                return groupsMatching(correctAnswer, test);
            }
        });

        assertTest(testCount, "should work on input with no anagrams", new Test() {
            @Override
            public boolean execute() {
                ArrayList<ArrayList<String>> correctAnswer = new ArrayList<>();
                correctAnswer.add(new ArrayList<>(Arrays.asList("hello")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("world")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("foo")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("bar")));
                correctAnswer.add(new ArrayList<>(Arrays.asList("baz")));
                ArrayList<ArrayList<String>> test = Problems5.groupAnagrams(new String[] { "hello", "world", "foo", "bar", "baz" });
                return groupsMatching(correctAnswer, test);
            }
        });

        // print the result of tests passed for a module
        System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

    }

    // function for checking if arrays contain same elements
    // (do not need to be in the same order)
    private static boolean arraysMatching(ArrayList<String> arr1, ArrayList<String> arr2) {
        if (arr1.size() != arr2.size()) {
            return false;
        } else {
            Collections.sort(arr1);
            Collections.sort(arr2);

            for (int i = 0; i < arr1.size(); i++) {
                if (arr1.get(i) != arr2.get(i)) {
                    return false;
                }
            }

            return true;
        }
    }

    // function for checking if two groups of arraylist of strings have
    // the same elements

    private static boolean groupsMatching(ArrayList<ArrayList<String>> correctAnswer, ArrayList<ArrayList<String>> test) {
        for (int i = 0; i < correctAnswer.size(); i++) {
            boolean containsGroups = false;
            for (int j = 0; j < test.size(); j++) {
                if (arraysMatching(correctAnswer.get(i), test.get(j))) {
                    containsGroups = true;
                    break;
                }
            }
            if (!containsGroups) {
                return false;
            }
        }
        for (int i = 0; i < test.size(); i++) {
            boolean containsGroups = false;
            for (int j = 0; j < correctAnswer.size(); j++) {
                if (arraysMatching(test.get(i), correctAnswer.get(j))) {
                    containsGroups = true;
                    break;
                }
            }
            if (!containsGroups) {
                return false;
            }
        }
        return true;
    }

    // do not edit below, this is to wrap the test and check for exceptions
    private static void assertTest(int[] count, String name, Test test) {
        String pass = "false";
        count[1]++;

        try {
            if (test.execute()) {
                pass = " true";
                count[0]++;
            }
        } catch (Exception e) {
        }
        String result = "  " + (count[1] + ")   ").substring(0, 5) + pass + " : " + name;
        System.out.println(result);
    }

}
