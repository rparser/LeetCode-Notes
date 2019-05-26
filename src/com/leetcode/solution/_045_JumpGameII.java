package com.leetcode.solution;

/**
 * http://www.cnblogs.com/ganganloveu/p/3761715.html
 * 我是看了上面那个解析的
 * <p>
 * times就是跳了多少次
 * reached就是当前跳了times次时能到的最远范围
 * max是路过的位置之处再跳一次能到达的最远的位置
 * <p>
 * 总之就是用了贪心的策略，reached和times表示跳了times次后，某一段区间内最小的步伐到达数量
 * <p>
 * 而max则记录了路过的所有节点的，再跳一次能有多远，这个可以用来更新reached
 */

public class _045_JumpGameII {
    public int jump(int[] nums) {
        int times = 0; //跳了多少次
        int reached = 0; //当前跳了times次时能到的最远范围
        int max = 0; //路过的位置之处再跳一次能到达的最远的位置
        for (int i = 0; i < nums.length; i++) {
            if (i > reached) { //当前位置是否已可跳到
                times++; //如果还不可跳到，则times增加
                reached = max; //reached值更新为新的
            }
            max = Math.max(max, i + nums[i]); //更新max,最大值（当前最远值，可能达到最远值）
        }
        return times;
    }
}
