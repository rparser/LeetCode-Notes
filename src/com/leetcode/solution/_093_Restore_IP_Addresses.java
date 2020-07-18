package com.leetcode.solution;

import java.util.*;

/**
 * backtracking, 找出第一部分合法的IP, 剩余部分变成相似子问题。
 */

public class _093_Restore_IP_Addresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12)
            return res;

        dfs(s, "", res, 1);
        return res;
    }

    public void dfs(String s, String temp, List<String> res, int count) {
        if (count == 4 && isValid(s)) {
            res.add(temp + s);
            return;
        }
        // Math.min(4, s.length())后面几位少于4的情况比如，0000
        for (int i = 1; i < Math.min(4, s.length()); i++) {
            String cur = s.substring(0, i);

            if (isValid(cur))
                dfs(s.substring(i), temp + cur + ".", res, count + 1); // i 是beginIndex
        }
    }

    private boolean isValid(String s) {
        // 前导0，如果第一个字符是0，只能一个为0
        if (s.charAt(0) == '0')
            return s.equals("0");

        int num = Integer.parseInt(s);
        return 0 < num && num < 256;
    }
}
