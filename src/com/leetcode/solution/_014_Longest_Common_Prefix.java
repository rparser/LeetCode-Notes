package com.leetcode.solution;

/**
 * Time complexity : O(S) , where S is the sum of all characters in all strings.
 * Space complexity : O(1). We only used constant extra space.
 * Time complexity: (mk + k^2)
 */

public class _014_Longest_Common_Prefix {
    //O(mn), O(1)
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < strs[0].length(); ++i) {
            char c = strs[0].charAt(i);
            for (String s : strs)
                if (s.length() <= i || s.charAt(i) != c)
                    return ans.toString();

            ans.append(c);
        }
        return ans.toString();
    }
}
