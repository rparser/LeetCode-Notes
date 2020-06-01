package com.leetcode.solution;

import java.util.*;

/**
 * Time Complexity: O(n*n!) Note that there are n! permutations and it requires O(n) time to print a a permutation.
 * 思路： Key is to keep a used[i] array, mark the element already added. So we skip such ele in dfs
 * (can also be done through list.contains(i))
 */

public class _046_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        // 如果已经完成了
        if (tempList.size() == nums.length) result.add(new ArrayList<>(tempList));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
