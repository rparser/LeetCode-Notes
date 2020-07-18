package com.leetcode.solution;

import java.util.*;

class _282_Expression_Add_Operators {
    //  O(N * 4^N) , O(N)
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backTracking(num, target, result, new StringBuilder(), 0, 0, 0);
        return result;
    }
    //num input, 目标值， 结果list, 当前equation，start当前起始位置，eval当前值, pre之前的值
    // pre用来配合乘法比如 1 + 2 * 5 算到 *5时， eval - pre + pre * cur
    private void backTracking(String num, int target, List<String> result
            , StringBuilder path, int start, long eval, long pre) {
        if (start == num.length()) {
            if (target == eval)
                result.add(path.toString());

            return;
        }

        for (int i = start; i < num.length(); i++) {
            // 数字不能以 0 开头
            if (num.charAt(start) == '0' && i > start)
                break;
            // 这次计算的值
            long cur = Long.parseLong(num.substring(start, i + 1));
            // 保存当前长度
            int len = path.length();
            //如果从start是0，则只能加
            if (start == 0) {
                backTracking(num, target, result, path.append(cur), i + 1, cur, cur);
                path.setLength(len);
            } else {
                // 加当前值
                backTracking(num, target, result, path.append("+").append(cur), i + 1, eval + cur, cur);
                //恢复长度
                path.setLength(len);
                // 减当前值
                backTracking(num, target, result, path.append("-").append(cur), i + 1, eval - cur, -cur);
                path.setLength(len);
                // 乘当前值
                backTracking(num, target, result, path.append("*").append(cur), i + 1, eval - pre + pre * cur,
                        pre * cur);
                path.setLength(len);
            }
        }
    }
}