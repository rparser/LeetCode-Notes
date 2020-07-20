package com.leetcode.solution;

import java.util.*;

class _1249_Minimum_Remove_to_Make_Valid_Parentheses {
    //O(N) O(N) Asterisk
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(')
                stack.add(i); // (则加入stack的位置
            if (sb.charAt(i) == ')') { //)如果里面已有（则弹出最近的（，否则标记
                if (!stack.isEmpty())
                    stack.pop();
                //需要删除的变成pound
                else
                    sb.setCharAt(i, '#');
            }
        }
        //stack剩余的(open也变成pound
        while (!stack.isEmpty())
            sb.setCharAt(stack.pop(), '#'); //如果还有剩余的则标记位置
        //replace所有pound
        return sb.toString().replaceAll("#", "");
    }
}