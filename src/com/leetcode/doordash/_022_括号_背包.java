package com.leetcode.doordash;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * <p>
 * Time Complexity : O(4^n/sqrt(n)),
 * Catalan number 卡特兰数 + Each valid sequence has at most n steps during the backtracking procedure.
 * <p>
 * Space Complexity : O(n) as described above, and using O(n) space to store the sequence.
 */

public class _022_括号_背包 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int n) {
        if (close == n) { // 当已经有了n个close括号则跳出 - 因为已经满了
            result.add(current);
            return;
        }
        //open只要不到n就可以加open
        if (open < n)
            backtrack(result, current + "(", open + 1, close, n);
        //close必须小于open，才可以加close
        if (close < open)
            backtrack(result, current + ")", open, close + 1, n);
    }
}
