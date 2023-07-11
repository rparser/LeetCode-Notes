package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

class _678_valid_parenthesis_string {
    //2-stack O(N), O(N)
    public boolean checkValidStringStack(String s) {
        Deque<Integer> left = new ArrayDeque<>(), star = new ArrayDeque<>(); // index stack
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                left.push(i);
            else if (c == '*')
                star.push(i);
            else {
                //先弹出left
                if (!left.isEmpty())
                    left.pop();
                    //或是弹出可代替左括号的star
                else if (!star.isEmpty())
                    star.pop();
                    //都没有了返回false
                else
                    return false;
            }
        }
        //此时看star代替右括号，但star必须在left后面出现，否则为false
        while (!left.isEmpty() && !star.isEmpty())
            if (left.pop() > star.pop())
                return false;
        //如果left空了则证明没问题
        return left.isEmpty();
    }

    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private boolean check(String s, int start, int count) {
        if (count < 0)
            return false;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                count++;
            else if (c == ')' && count-- == 0) {
                return false;
            } else if (c == '*')
                return check(s, i + 1, count + 1) ||  // 作为 (
                        check(s, i + 1, count - 1) || // 作为 )，抵消一个左括号
                        check(s, i + 1, count);       // 作为 空
        }
        return count == 0;
    }
}