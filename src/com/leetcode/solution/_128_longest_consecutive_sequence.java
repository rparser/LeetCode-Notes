package com.leetcode.solution;

import java.util.*;

class _128_longest_consecutive_sequence {
    // O(N), O(N) 头尾都设成最长长度， 一次遍历
    public int longestConsecutive(int[] nums) {
        //用一个 HashMap ，存储以当前 key 为边界(前后)的连续序列的长度。
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for (int num : nums) {
            //已经考虑过的数字就跳过，必须跳过，不然会出错
            //比如 [1 2 1]
            //最后的 1 如果不跳过，因为之前的 2 的最长长度已经更新成 2 了，所以会出错
            if (map.containsKey(num))
                continue;
            // 因为必须要连上，所以只用考察左右
            //找到以左边数字结尾的最长序列，默认为 0
            int left = map.getOrDefault(num - 1, 0);
            //找到以右边数开头的最长序列，默认为 0
            int right = map.getOrDefault(num + 1, 0);
            int sum = left + 1 + right;
            max = Math.max(max, sum);

            //将当前数字放到 map 中，防止重复考虑数字，value 可以随便给一个值
            map.put(num, -100);
            // 比如当前值为10，left = 3，意味着789都存在，所以要给7更新为4
            //更新左边界长度
            map.put(num - left, sum);
            //更新右边界长度
            map.put(num + right, sum);
        }
        return max;
    }
    // 两次遍历法
    public int longestConsecutiveSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        // 先都加入set
        for (int num : nums)
            set.add(num);

        int max = 0;

        for (int num : set) {
            // 如果不包含前一个值，证明连不上，当前cur就是当前num
            if (!set.contains(num - 1)) {
                int cur = num;
                int cur_max = 1;
                // 如果包含后一个值就一直+1
                while (set.contains(cur + 1)) {
                    cur += 1;
                    cur_max += 1;
                }
                //更新max
                max = Math.max(max, cur_max);
            }
        }
        return max;
    }
}