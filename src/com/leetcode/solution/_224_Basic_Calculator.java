package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 时间复杂度
 * O(N)， 因为字符串只遍历了一次
 * Iterate through string. Use a variable to store the previous operation (+ / -). If it's -, that means I need to add (-1) * number. There are six cases:
 * Number: keep track of current number. Only perform an operation when I see + or -, because number can have more than one digit.
 * Space: do nothing.
 * +: add sign * number to result. Set sign to 1 and reset number.
 * -: add sign * number to result. Set sign to -1 and reset number.
 * (: Push previous result and sign onto stack. Reset result to start calculation inside parentheses.
 * ): Store result inside the parentheses into number. Pop the previous sign and result.
 * At the end, add sign * last number.
 */

public class _224_Basic_Calculator {
    // ()+-数字没有乘除
    //O(N), O(N)
    public int calculate(String s) {
        //保存之前的结果和正负
        Deque<Integer> stack = new ArrayDeque<>();
        // 当前同级别的总值
        int result = 0;
        int sign = 1; //当前正负
        int temp = 0; //当前数值

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) temp = temp * 10 + (c - '0');
                // 看到+号-号要把之前的临时cur * sign加进总值, cur清零等待符号后的计算
            else if (c == '+') {
                result += sign * temp;
                sign = 1;
                temp = 0;
            } else if (c == '-') {
                result += sign * temp;
                sign = -1;
                temp = 0;
            } else if (c == '(') {
                // 之前的result和sign放入stack储存，然后清零
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                //result计算好用temp保存，然后取出存的result和符号
                result += sign * temp;
                temp = result;
                // 结果后出
                sign = stack.pop();
                result = stack.pop();
            }
        }
        // 最后剩余的符号和数字
        result += sign * temp;
        return result;
    }

    // 不存数字
    public int calculateNonumber(String s) {
        LinkedList<Boolean> stack = new LinkedList<>();
        int result = 0, opr = 0; //result: 当前的结果值; opr: 当前的被加/被减数
        Character op = null; //当前要执行的运算符
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-') {
                if (op == null)
                    //遇到第一个运算符时，将result置为opr（即第一个运算符左边的数字）
                    result = opr;
                else
                    //result = result +/- opr;
                    result = cal(op, result, opr);

                //根据栈顶元素决定是否反转运算符
                op = swap(stack.peek() == null ? false : stack.peek(), c);
                opr = 0;
            } else if (c == '(')
                stack.push(op != null && op == '-');
            else if (c == ')')
                stack.pop();
            else if (c != ' ')
                opr = opr * 10 + c - '0';
        }

        if (op == null)
            //算式中没有运算符时，opr就是最终结果
            return opr;
        else
            //否则将result与opr（即算式中最右边的数字）执行一次运算
            return cal(op, result, opr);

    }

    char swap(boolean swap, char c) {
        if (swap)
            return c == '+' ? '-' : '+';
        else
            return c;
    }

    private int cal(char op, int opr1, int opr2) {
        switch (op) {
            case '+':
                return opr1 + opr2;
            case '-':
                return opr1 - opr2;
            default:
                return 0;
        }
    }
}
