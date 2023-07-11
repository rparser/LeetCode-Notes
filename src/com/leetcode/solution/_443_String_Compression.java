package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.List;

/**
 * Time Complexity: O(N), where N is the length of chars.
 * Space Complexity: O(1), the space used by read, write, and anchor.
 * 遍历数组元素，如果发现和当前元素一样，就增加计数；
 * 否则就修改字符串中的元素，并且在个数大于1的时候同时写入计数。
 * 值得注意的是在最后还需要检查一下计数是不是大于1，如果大于1则还要额外再写入计数。
 */

public class _443_String_Compression {
    // O(N), O(1)原地算法
    public int compress(char[] chars) {
        // 如果输入字符串
//        String str;
//        char[] chars = str.toCharArray();
//        str = new String(chars);
        int n = chars.length;
        int cur = 0; //当前处理字符的索引，最后是压缩串的长度
        // left and right是相同字母窗口的长度
        for (int left = 0; left < n; ) {
            int right = left;
            // 和右侧对比，相等++ 找相同字符的连续个数
            while (right < n - 1 && chars[right] == chars[right + 1])
                right++;

            chars[cur] = chars[left]; //记录当前字符
            //处理了一个字母
            cur++;
            // 如果长度>1
            if (left != right) {
                String times = (right - left + 1) + ""; //当前字符的连续重复次数
                int tLen = times.length();
                //将字符的重复次数写入原串中，用以压缩字符串, cur++代表又处理一个字符
                for (int k = 0; k < tLen; k++)
                    chars[cur++] = times.charAt(k);
            }
            left = right + 1;
        }
        return cur;
    }

    //original above
    // 以下为一个字母a也要写成a1

    public int compressA1(char[] chars) {
        List<Character> result = new ArrayList<>();
        int n = chars.length;
        int cur = 0; // 当前处理字符的索引，最后是压缩串的长度
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n - 1 && chars[j] == chars[j + 1]) // 找相同字符的连续个数
                j++;
            result.add(chars[i]);
            cur++;// 记录当前字符
            String times = (j - i + 1) + "";// 当前字符的连续重复次数
            int tLen = times.length();
            for (int k = 0; k < tLen; k++) {//将字符的重复次数写入原串中，用以压缩字符串
                result.add(times.charAt(k));
                cur++;
            }
            i = j + 1;
        }
        System.out.println(result.toString());
        return cur;
    }


    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._443_StringCompression");
    }

    @Test
    public void testSolution() {
        char[] input = {'a', 'b', 'b', 'c', 'c', 'c'};
        Assert.assertEquals(6, compress(input));
    }
}
