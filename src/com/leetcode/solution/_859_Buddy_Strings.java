package com.leetcode.solution;

/**
 * 需要正好有2次不等 且 字符统计一样 - 词频相同
 * 或
 * 有0次不等 且 s中有出线数量>=2的字符 - 可以相同字符交换
 */

public class _859_Buddy_Strings {
    public static boolean buddyStrings(String s, String goal) {
        //若s和goal的长度不相等直接返回false
        if (s.length() != goal.length())
            return false;
        //初始化两个数组 用来存放s 和 goal的字母
        int[] count1 = new int[26], count2 = new int[26];
        int x, y, sum = 0;
        for (int i = 0; i < s.length(); i++) {
            x = s.charAt(i) - 'a';
            y = goal.charAt(i) - 'a';
            count1[x]++;//对每一个字母计数
            count2[y]++;
            if (x != y)
                sum++;//记录位置不同的字母个数
            if (sum > 2)
                return false;
        }
        boolean hasCharMoreThan2 = false;
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i])
                return false; //判断s和goal的其中每个字母的数量是否一样 若不一样直接返回false
            if (count1[i] > 1)
                hasCharMoreThan2 = true; //若有字母数量超过1则把hasCharMoreThan2置为true
        }

        //能走到这一步说明s和goal的长度相同，字母相同
        //只有两种能返回true：
        // 第一种：s和goal有两个位置不同的
        // 第二种：没有位置不同的但是需要有字母的数量大于1 例如："ab" "ab" return false  "abb" "abb" returen true
        return sum == 2 || (sum == 0 && hasCharMoreThan2);
    }
}
