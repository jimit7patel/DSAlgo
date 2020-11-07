package lc.topliked.medium;

/*
 * https://leetcode.com/problems/palindromic-substrings/solution/
 *
 * the solution is take each center and then find palindromic from it. O(N^2)
 * there is manacher's O(N) algorithm which I dont want to memorize it.
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 *
 */
public class PalindromicSubstr {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i, k = i;
            while (k >= 0 && j < s.length() && s.charAt(k) == s.charAt(j)) {
                k--;
                j++;
                count++;
            }
            j = i + 1;
            k = i;
            while (k >= 0 && j < s.length() && s.charAt(k) == s.charAt(j)) {
                k--;
                j++;
                count++;
            }
        }
        return count;
    }

    public int usingManacher(String S) {
        char[] A = new char[2 * S.length() + 3];
        A[0] = '@';
        A[1] = '#';
        // A[A.length - 1] = '$';
        int t = 2;
        for (char c : S.toCharArray()) {
            A[t++] = c;
            A[t++] = '#';
        }
        int[] Z = new int[A.length];
        int center = 0, right = 0;
        for (int i = 1; i < Z.length - 1; ++i) {
            if (i < right)
                Z[i] = Math.min(right - i, Z[2 * center - i]);
            while (A[i + Z[i] + 1] == A[i - Z[i] - 1])
                Z[i]++;
            if (i + Z[i] > right) {
                center = i;
                right = i + Z[i];
            }
        }
        int ans = 0;
        for (int v : Z)
            ans += (v + 1) / 2;
        return ans;
    }

}
