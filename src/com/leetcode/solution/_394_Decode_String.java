package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1.使用两个栈，countStack存子串的重复次数，resStack存中间结果
 * 2.读取完一个数字，把数量入countStack栈
 * 3.读取到[时，把n[前面的结果计算出来并入resStack栈
 * 4.读取到]时，取出最近一次n[res]前面的中间计算结果，也就是resStack的栈顶，再append n次res，n为countStack的栈顶，res为最近一次[]中间的子串
 * 5.其他情况，将字符追加到res末尾
 * <p>
 * 比如:  3[a]2[b3[d]c]
 * 下面是读完各字符时的情况：
 * *        读完:  3    [     a     ]     2    [    b      3       [      d       ]      c       ]
 * * countStack: (3)  (3)   (3)    ()   (2)  (2)  (2)   (2 3)   (2 3)   (2 3)   (2)    (2)      ()
 * *   strStack: ()   ("")  ("")   ()   ()  (aaa) (aaa) (aaa)  (aaa b) (aaa b)  (aaa) (aaa)     ()
 * *        res: ""    ""    a     aaa  aaa   ""   b      b       ""     d      bddd  bdddc  aaabdddcbdddc
 * 2    [    b 时 把cur aaa加入strStack再清空
 */

public class _394_Decode_String {
    // O(S), O(S)
    public String decodeString(String s) {
        // 存重复次数
        Deque<Integer> countStack = new LinkedList<>();
        // 存中间结果
        Deque<StringBuilder> strStack = new LinkedList<>();
        StringBuilder cur = new StringBuilder();

        int k = 0;
        for (char ch : s.toCharArray()) {
            // 如果是数字则更新k
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                //加入后countStack清空
                countStack.push(k);
                k = 0;
                //cur加入strStack后清空 - 要储存新的括号里的cur
                strStack.push(cur);
                cur = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder tmp = cur; // 当前的sb临时保存
                cur = strStack.pop();
                //打印k次
                for (int i = countStack.pop(); i > 0; i--) {
                    cur.append(tmp);
                }
            } else {
                // 是字母则加上
                cur.append(ch);
            }
        }
        return cur.toString();
    }

    public String decodeStringRecursive(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int k = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                k = k * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while (k > 0) {
                    res.append(tmp[1]);
                    k--;
                }
            } else if (s.charAt(i) == ']') {
                return new String[]{String.valueOf(i), res.toString()};
            } else {
                res.append(s.charAt(i));
            }
            i++;
        }
        return new String[]{res.toString()};
    }
}
