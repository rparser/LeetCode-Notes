package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间复杂度：O(N)。我们只遍历了一次输入元素。
 * 空间复杂度：O(N)，输出答案所使用的空间。
 */

class _057_Insert_Interval {
    //O(N) O(N)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int index = 0, n = intervals.length;
        // 找到新区间插入位置，最后一个右边界小于新区间左边界的旧区间的后面(保证无重叠)
        // 所有插入位置之前的都加进去res(不影响的)
        while (index < n && newInterval[0] > intervals[index][1]) {
            res.add(intervals[index]);
            index++;
        }
        // index此时为插入位置 0.....index-1无重叠
        //temp记录合并后新区间的左右边界值
        int[] temp = {newInterval[0], newInterval[1]};
        // index是原本的位置 处理插入位置之后的重叠
        while (index < n && newInterval[1] >= intervals[index][0]) {
            temp[0] = Math.min(temp[0], intervals[index][0]); //新区间开始于两个0的最小值
            temp[1] = Math.max(temp[1], intervals[index][1]); //新区间开始于两个1的最大值
            index++;
        }
        //将合并后的新区间放入结果集
        res.add(temp);
        //将剩余的放入结果集（无重叠）
        while (index < n) {
            res.add(intervals[index]);
            index++;
        }

        return res.toArray(new int[0][0]);
    }
}

