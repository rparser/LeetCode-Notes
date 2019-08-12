package com.leetcode.solution;

import java.util.*;

/**
 * 假设输入的S为 XXXAXXXXAXXAXXXXX，X表示其他任意字符，现在我们来计算蓝A对最后的输出贡献了多少，很显然在两个红A之间的子串中，
 * 只要是包括蓝A的子串都有蓝A的贡献，如果第一个红A到蓝A之间的字符数量是L，蓝A到第二个红A之间的字符数量是R，
 * 那么蓝A的贡献就是 L + R + L*R + 1 ，其中L表示蓝A与左边字符组成的子串数量，R为与右边的，L*R为同时与左右结合，1表示不与任何字符结合。
 * Time Complexity: O(N), where NN is the length of S.
 * Space Complexity: O(N).
 * We could reduce this to O(A) if we do not store all the indices, but compute the answer on the fly.
 */

public class _828_UniqueLetterString {
    public int uniqueLetterString(String S) {
        Map<Character, List<Integer>> index = new HashMap<>(); //字母和位置
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            index.computeIfAbsent(c, x -> new ArrayList<>()).add(i); //添加进map字母位置
        }

        long ans = 0;
        for (List<Integer> c : index.values()) {
            for (int i = 0; i < c.size(); i++) {
                long prev = i > 0 ? c.get(i - 1) : -1;
                long next = i < c.size() - 1 ? c.get(i + 1) : S.length();
                ans += (c.get(i) - prev) * (next - c.get(i)); //如上分析，(L+1) * (R+1)
            }
        }
        return (int) ans % 1_000_000_007;
    }
}
