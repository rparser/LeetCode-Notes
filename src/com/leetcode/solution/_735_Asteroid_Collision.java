package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

class _735_Asteroid_Collision {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int ast : asteroids) {//对sateroids遍历取值
            collision:
            {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    //当栈不为空，栈顶大于0，当前遍历值小于0 才会发生爆炸
                    if (stack.peek() < -ast) { //栈顶值小于遍历值绝对值
                        stack.pop(); //栈顶值拉出
                        continue; //再进行判断 和当前栈顶再比一次
                    } else if (stack.peek() == -ast) {//绝对值相等
                        stack.pop();//栈顶值拉出
                    }
                    break collision;  //栈顶值大于遍历值绝对值 跳出collision 然后进行下一个ast遍历
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        //栈的值赋给ans
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }
}
