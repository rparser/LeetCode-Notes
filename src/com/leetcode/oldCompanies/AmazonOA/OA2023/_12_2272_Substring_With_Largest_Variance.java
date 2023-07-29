package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Arrays;

public class _12_2272_Substring_With_Largest_Variance {
    public int largestVariance(String s) {
        var ans = 0;
        var diff = new int[26][26]; // 存储字符之间的差异次数
        var diffWithB = new int[26][26]; // 存储字符与字符b之间的差异次数
        for (var i = 0; i < 26; i++)
            Arrays.fill(diffWithB[i], -s.length()); // 初始化diffWithB数组为-s.length()

        for (var k = 0; k < s.length(); k++) {
            var ch = s.charAt(k) - 'a'; // 将字符转换为对应的0-25的数字
            for (var i = 0; i < 26; ++i) {
                if (i == ch)
                    continue;

                ++diff[ch][i]; // a=ch, b=i，增加a和b之间的差异次数
                ++diffWithB[ch][i]; // 增加字符ch和字符b之间的差异次数
                diffWithB[i][ch] = --diff[i][ch]; // a=i, b=ch，更新字符i和字符ch之间的差异次数
                diff[i][ch] = Math.max(diff[i][ch], 0); // 保证差异次数不会为负数
                ans = Math.max(ans, Math.max(diffWithB[ch][i], diffWithB[i][ch])); // 更新最大差异次数
            }
        }
        return ans;
    }
}
