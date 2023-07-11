package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

class _844_backspace_string_compare {
    //O(S+T), O(S+T)
    public boolean backspaceCompare(String S, String T) {
        return toRes(S).equals(toRes(T));
    }

    // 重新计算两个字符串
    private String toRes(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray())
            if (c != '#')
                stack.push(c);
                //如果是#，此时非空才pop
            else if (!stack.isEmpty())
                stack.pop();
        //String.valueOf按照先进后出
        return String.valueOf(stack);
    }

    //O(S+T), O(1)
    public boolean backspaceComparePointers(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            // 找到S和T的下一个字母
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else break;
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else break;
            }
            //如果两个字母不等
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // 如果不是同时为空
            if ((i < 0) != (j < 0))
                return false;
            i--;
            j--;
        }
        return true;
    }
}
