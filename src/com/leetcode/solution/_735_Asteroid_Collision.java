package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 由于碰撞抵消总是从相邻行星之间发生，我们可以使用「栈」来模拟该过程。
 * 从前往后处理所有的 asteroids[i]，使用栈存储当前未被抵消的行星，
 * 当栈顶元素方向往右，当前 asteroids[i] 方向往左时，会发生抵消操作，抵消过程根据规则进行即可。
 * O(N)~O(N)
 */
class _735_Asteroid_Collision {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int ast : asteroids) {//对asteroids遍历取值
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
