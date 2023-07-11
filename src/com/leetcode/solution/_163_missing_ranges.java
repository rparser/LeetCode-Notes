package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class _163_missing_ranges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        // 记录左边界指针
        long left = lower;
        for (int num : nums) {
            //如果只有一个数
            if (left + 1 == num)
                res.add(String.valueOf(left));
                // 如果有多个数
            else if (left + 1 < num)
                res.add(left + "->" + (num - 1));

            left = (long) num + 1;
        }
        // 最后与upper比较
        if (left == upper)
            res.add(String.valueOf(left));
        else if (left < upper)
            res.add(left + "->" + upper);

        return res;
    }
}
