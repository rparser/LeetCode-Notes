package com.leetcode.solution;

public class _647_Palindromic_Substrings {
    // manacher's algorithm
    public int countSubstringsManacher(String S) {
        char[] A = new char[2 * S.length() + 3];
        A[0] = '@';
        A[1] = '#';
        A[A.length - 1] = '$';
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

    // O(n^2), O(1)
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            //奇数长度 abcdcba
            count += extractPalindrome(s, i, i);
            //偶数长度 abccba
            count += extractPalindrome(s, i, i + 1);
        }
        return count;
    }

    private int extractPalindrome(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
            count++;
        }
        return count;
    }
}
