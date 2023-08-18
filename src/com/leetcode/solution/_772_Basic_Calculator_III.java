package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 保存之前的sign
 * 道理1. 加减号在乘除号面前是没有地位的，遇到了需要先去栈里待着，押后处理。
 * 如果栈里已经有了更高优先级的计算，还需要先将之前高优先级的符号请出来先行计算后方可入栈，保证符号栈的“单调性”
 * 道理2. 左括号是没有地位的，无论什么情况都需要先去栈里待着，直到右括号出现。
 * 此时按顺序往回清算，直到遇到了最近的左括号并将其释放。右括号的作用与表达式结束后整体清算相同
 */

class _772_Basic_Calculator_III {
    private int index = 0;

    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return cal(ch);
    }

    private int cal(char[] ch) {
        Deque<Integer> stack = new LinkedList<>();
        int result = 0;
        int temp = 0;
        char sign = '+'; // 保存之前的sign

        for (; index < ch.length; index++) {
            char c = ch[index];
            if (Character.isDigit(c)) { // 如果是数字更新temp
                temp = temp * 10 + (c - '0');
            }
            if (c == '(') { // 如果是前括号
                index++;// index指针指到下一个字符
                temp = cal(ch);
            }
            // 当遇到了新的运算符，就要对上一个运算符sign和累计的数字temp作运算
            // 空格直接无视，i继续前进
            // 遇到字符串末尾，肯定是要结算的
            if (!Character.isDigit(c) || index == ch.length - 1) {
                int pre;
                switch (sign) { // 使用之前保存的sign
                    case '+' -> stack.push(temp);
                    case '-' -> stack.push(-temp);
                    case '*' -> {
                        pre = stack.pop();
                        stack.push(pre * temp);
                    }
                    case '/' -> {
                        pre = stack.pop();
                        stack.push(pre / temp);
                    }
                }
                sign = c; // 更新sign给下次用
                temp = 0; //计数归位
            }
            if (c == ')') { // 如果是后括号
                break;
            } //阶段，后面开始计算局部结果，返回
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
