package com.leetcode.solution;

import java.util.*;

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
}
