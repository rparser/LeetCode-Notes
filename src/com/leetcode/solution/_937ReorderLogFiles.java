package com.leetcode.solution;

import java.util.*;

/**
 * 两个函数要掌握，String.split(regex, limit) limit是把String分割为几段
 * 用Character.isDigit返回是否为数字
 * 1. 把每个log分割成前后两部分，判断第二部分是数字段还是字母段
 * 2. 两个字母段按字典序排序，一致则按第一段排序
 * 3. 如有非字母段，则非字母段（数字段）靠后，两个均为数字段，则返回原顺序
 */

public class _937ReorderLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);   // String.split(regex, limit) limit是把String分割为几段
            String[] split2 = log2.split(" ", 2);   // 分割为两段
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));  //看第二段（正文段）是字母还是数字，用isDigit
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {   // 如果都是字母
                int cmp = split1[1].compareTo(split2[1]);   // 按照字母排序第二段（正文）
                if (cmp != 0) return cmp;   // 如果有正负则比较字典序lexicographical(lexico-graphical)
                return split1[0].compareTo(split2[0]); //如果第二段一样，则比较第一段identifier
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;  // 如果不都是字母（有数字）则：
            // 即如果d1是数字，则看d2是不是数字，d2如果是数字，则d1在d2前（顺序不变，0）
            // 如果d2不是数字，则d2在d1前（返回1，即d1>d2，所以升序排列d2靠前d1靠后）
            // 如果d1不是数字，则d2必是数字，此时返回-1，即d1<d2，d1靠前d2靠后
        });
        return logs;
    }
}
