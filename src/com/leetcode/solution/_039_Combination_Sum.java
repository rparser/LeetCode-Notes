package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _039_Combination_Sum {
    // can reuse (without duplicates)
    // O(n*n!), O(n)
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain == 0) {
            list.add(new ArrayList<>(tempList)); // 因为tempList会变化，所以必须要新建new ArrayList<>(tempList)
        } else if (remain > 0) {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backtrack(list, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1); // 减去最后一个
            }
        }
    }
}