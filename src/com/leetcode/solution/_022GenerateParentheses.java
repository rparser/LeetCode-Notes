package com.leetcode.solution;

import java.util.*;

/**
 * 括号生成
 *
 * Time Complexity : O(4^n/sqrt(n)), Catalan number Each valid sequence has at most n steps during the backtracking procedure.
 *
 * Space Complexity : O(4^n/sqrt(n)) as described above, and using O(n) space to store the sequence.
 */

public class _022GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> res, String current, int open, int close, int n) {
        if (close == n) { // 当已经有了n个close括号则跳出
            res.add(current);
            return;
        }

        if (open < n) //open只要不到n就可以加
            backtrack(res, current + "(", open + 1, close, n);
        if (close < open) //close必须小于open才可以加
            backtrack(res, current + ")", open, close + 1, n);
    }
}
