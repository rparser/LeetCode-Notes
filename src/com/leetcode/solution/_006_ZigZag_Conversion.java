package com.leetcode.solution;

import java.util.*;

/**
 * Time Complexity: O(n), where n==len(s). Each index is visited once.
 * Space Complexity: O(n). For the cpp implementation, O(1) if return string is not considered extra space.
 */

public class _006_ZigZag_Conversion {
    //O(N), O(N)
    public String convert2(String s, int numRows) {
        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());
        //i代表行，direction代表方向，正数向下，负数向上
        int i = 0, direction = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            //在第一行或最后一行，flag转向
            if (i == 0 || i == numRows - 1)
                direction = -direction;
            //i按照direction变化
            i += direction;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows)
            result.append(row);
        return result.toString();
    }

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) return s;
        StringBuilder res = new StringBuilder();
        /*
         * a   e
         * b d f
         * c   g
         * 从a到d为一个zig，size: 一个zig的大小
         */
        int size = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++)
            // 外层循环i表示第几行
            // 内层序号j表示第几个zig（从0开始）
            for (int j = i; j < s.length(); j += size) {
                res.append(s.charAt(j));
                // zig的第一行和最后一行中间的行
                // 每一个zig有两个元素在同一行，如：b和d在同一行
                // 同一行中的两个元素之间的距离是：size-2*i(size一个zig的大小，i当前是第几行，从0开始）
                // 同一个zig中的第一个元素在s中的位置是j，第二个元素在s中的位置是：j+size-2*i
                if (i > 0 && i < numRows - 1) {
                    int mid = j + size - 2 * i;
                    if (mid < s.length())
                        res.append(s.charAt(mid));
                }
            }

        return res.toString();
    }
}
