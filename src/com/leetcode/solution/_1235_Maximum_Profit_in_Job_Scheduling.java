package com.leetcode.solution;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 存在一个升序结果数组max，本数组每个数都是小于等于i时的任务最大收益
 * i的最大收益为下面两种可能之中的更大值
 * 1, i不值得加入任务，i-1比i更好，所以采用max[i-1]
 * 2，i值得加入任务，所以是i和的能加入i任务的最大的x（这里用二分法）,所以采用max[l] + profit[i]
 */

public class _1235_Maximum_Profit_in_Job_Scheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length; // 任务数量
        Integer[] endTimeAscendingIds = new Integer[n]; //储存下标
        for (int i = 0; i < n; i++) {
            endTimeAscendingIds[i] = i;
        } // 制作ids数组 从0到n-1
        Arrays.sort(endTimeAscendingIds, Comparator.comparingInt(id -> endTime[id])); // 按endTime照时间，对ids数组下标进行排序

        int[] max = new int[n + 1]; //max[i](i > 0时)表示所有endTime排名小于等于i的任务的最大收益。max[0]作为一个辅助值为0。
        max[1] = profit[endTimeAscendingIds[0]]; //注意下标 - 只加入第一个完成的任务，是备注的情况2，因为情况1不存在

        for (int i = 2; i <= n; i++) {
            int id = endTimeAscendingIds[i - 1]; // 注意下标
            int s = startTime[id]; //endTime排名为i的任务的开始时间

            //二分查找endTime小于等于s的任务的数目（也就是endTime大于s的最小值的下标）
            int l = 0, r = i - 2; //注意下标 - 实际上i-1也可以,但i-1已经被情况1考虑到了
            while (l <= r) {
                int m = (l + r) / 2;
                if (endTime[endTimeAscendingIds[m]] > s) { //如果这个m值对应的任务的结束时间，大于了需要查询的i的开始时间，也就是有了重叠Overlap
                    r = m - 1; // 要从左半区找 - 二分查找 - 因为返回l所以先放invalid的验证
                } else { // 如果没有Overlap
                    l = m + 1; // 从右半区找
                }
            }
            //此时，l就是endTime小于等于s的兼职的数目，注意l可以是0，max[0]恰好是0，省了一次if判断（但代价是每次求id时i都要i-1）
            max[i] = Math.max(max[i - 1], max[l] + profit[id]);
        }
        return max[n]; // 最后一个值
    }
}
