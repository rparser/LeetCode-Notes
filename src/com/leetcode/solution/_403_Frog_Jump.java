package com.leetcode.solution;

import java.util.HashSet;
import java.util.Set;

class _403_Frog_Jump {
    public boolean canCross(int[] stones) {
        if (stones[1] > 1) {//第一跳只能跳一格，如果stones[1]不为1，肯定为false
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            if (i > 3 && stones[i] >= 2 * stones[i - 1]) {//如果下一个石头位置序号为当前序号的两倍，肯定不符合
                return false;
            }
            set.add(stones[i]);
        }
        return check(stones[stones.length - 1], set, 1, 1);
    }

    private boolean check(int last, Set<Integer> set, int index, int step) {
        if (index == last) {
            return true;
        }

        if (set.contains(index + step + 1)) {
            if (check(last, set, index + step + 1, step + 1)) {
                return true;
            }
        }
        if (set.contains(index + step)) {
            if (check(last, set, index + step, step)) {
                return true;
            }
        }

        if (step - 1 > 0) {
            if (set.contains(index + step - 1)) {
                return check(last, set, index + step - 1, step - 1);
            }
        }
        return false;
    }
}
