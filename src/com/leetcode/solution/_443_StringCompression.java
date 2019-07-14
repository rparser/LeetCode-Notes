package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Time Complexity: O(N), where NN is the length of chars.
 * Space Complexity: O(1), the space used by read, write, and anchor.
 * 遍历数组元素，如果发现和当前元素一样，就增加计数；
 * 否则就修改字符串中的元素，并且在个数大于1的时候同时写入计数。
 * 值得注意的是在最后还需要检查一下计数是不是大于1，如果大于1则还要额外再写入计数。
 */

public class _443_StringCompression {
    public int compress(char[] chars) {
//        String str;
//        char[] chars = str.toCharArray();
//        str = new String(chars);
        int n = chars.length;
        int cur = 0; //当前处理字符的索引，最后是压缩串的长度
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n - 1 && chars[j] == chars[j + 1]) j++; // 找相同字符的连续个数
            chars[cur++] = chars[i]; //记录当前字符
            if (i != j) {
                String times = (j - i + 1) + ""; //当前字符的连续重复次数
                int tLen = times.length();
                for (int k = 0; k < tLen; k++) { //将字符的重复次数写入原串中，用以压缩字符串
                    chars[cur++] = times.charAt(k);
                }
            }
            i = j + 1;
        }
        return cur;
    }

    //original above
    // 以下为一个字母a也要写成a1

//    public int compress(char[] chars) {
//        List<Character> result = new ArrayList<>();
//        int n = chars.length;
//        int cur = 0; // 当前处理字符的索引，最后是压缩串的长度
//        for (int i = 0; i < n; ) {
//            int j = i;
////            System.out.println("old i="+i+"   old j="+j);
////            System.out.println("chars[j] = "+chars[j]+"    chars[j+1] = " +chars[j+1]);
//            while (j < n - 1 && chars[j] == chars[j + 1]) // 找相同字符的连续个数
//                j++;
//            System.out.println("cur=" + cur + "   i=" + i + "   j=" + j);
//            result.add(chars[i]);
//            cur++;// 记录当前字符
////            if(i != j) {
//            String times = (j - i + 1) + "";// 当前字符的连续重复次数
//            int tLen = times.length();
////                System.out.println("times  "+times);
//            for (int k = 0; k < tLen; k++) {//将字符的重复次数写入原串中，用以压缩字符串
//                result.add(times.charAt(k));
//                cur++;
//
//            }
//            i = j + 1;
////            System.out.println("i="+i+"   j="+j);
//        }
//        System.out.println(result.toString());
//        return cur;
//    }


    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._443_StringCompression");
    }

    @Test
    public void testSolution() {
        char[] input = {'a', 'b', 'b', 'c', 'c', 'c'};
        Assert.assertEquals(6, compress(input));
    }
}
