package com.leetcode.solution;

import java.util.HashMap;

/**
 * 时间复杂度：O(n^2*log m)其中 n 为点的数量，m 为横纵坐标差的最大值。
 * greatest common divisor
 */
class _149_Max_Points_on_a_Line {
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int result = 0;
        //遍历每个点
        for (int i = 0; i < points.length; i++) {
            int curDup = 0; // current duplication
            int max = 0;//保存经过当前点的直线中，最多的点
            //slope 斜率作为 key，然后遍历平面上的其他点，相同的 key 意味着在同一条直线上。
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                // 求出分子分母
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                // 如果是同一个点
                if (x == 0 && y == 0) {
                    curDup++;
                    continue;
                }
                // 进行约分
                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;
                // 标记slope斜率
                String key = x + "@" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            //1 代表当前考虑的点i，curDup 代表和当前的点重复的点
            result = Math.max(result, max + curDup + 1);
        }
        return result;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}