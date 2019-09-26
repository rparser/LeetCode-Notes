package com.leetcode.solution;

import java.util.*;

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
 * *   resStack: ()   ("")  ("")   ()   ()  (aaa) (aaa) (aaa)  (aaa b) (aaa b)  (aaa) (aaa)     ()
 * *        res: ""    ""    a     aaa  aaa   ""   b      b       ""     d      bddd  bdddc  aaabdddcbdddc
 */

public class _394_DecodeString {
    public String decodeString(String s) {
        Deque<Integer> intStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();
    }
}
