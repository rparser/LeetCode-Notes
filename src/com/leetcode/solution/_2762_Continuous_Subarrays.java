package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 1.创建一个哈希表, 用来记录数值出现的最近的坐标
 * 2.利用双指针进行滑动窗口检测
 * 3.从左向右遍历数组, 首先将当前数字nums[j]放入到哈希表当中
 * 创建一个链表, 用来记录需要移除的数字
 * 遍历哈希表, 如果当前数字的值和nums[j]值相差大于2, 那么以j为结尾的子数组一定不能包括这个数字
 * 那么左边界最少为map.get(current) + 1
 * 所以只要差值大于2, 那么i = Math.max(i, map.get(temp) + 1);
 * 4.将所有与当前差值大于2的数值记录从哈希表中移除
 * 5.此时[i, j]之间所有数字的差值都不会超过2
 * 那么以坐标j结尾的有效的不间断子数组数量就为j - i + 1
 * <p>
 * *对于为什么保留当前数值 +-2 的疑问, 有得朋友会说, (x + 2) - (x - 2) 就等于4了, 但是不会出现这种情况
 * 因为哈希表中的数值差值一定不会大于2的.
 * 当第一个数插入时, 哈希表中是一个点
 * 当第二个数插入时, 哈希表中是一个点或者一条线, 线的长度最大也就为3.[X - 2, X]或者[X, X + 2]
 * 当第三个数插入时, 它的区间为[Y - 2, Y + 2],
 * 这两个区间的共同区域就是最后哈希表中可以保留数字的区域
 * 因为Previous的长度为3, 当前的长度即使为5, 那么共同区域的长度最大也就为3
 * 所以哈希表中的最大差值一定不会大于2.
 * 对此不明白的朋友可以进行画图验证
 * 时间复杂度O(N)
 * 空间复杂度O(C) Max(c) = 3
 */
public class _2762_Continuous_Subarrays {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        long result = 0;

        int i = 0;
        for (int j = 0; j < n; j++) {
            map.put(nums[j], j);
            List<Integer> list = new ArrayList<>();
            for (int temp : map.keySet()) {
                if (Math.abs(temp - nums[j]) > 2) {
                    i = Math.max(i, map.get(temp) + 1);
                    list.add(temp);
                }
            }
            for (int temp : list) {
                map.remove(temp);
            }
            result += j - i + 1;
        }
        return result;
    }
}
